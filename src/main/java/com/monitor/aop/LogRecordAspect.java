package com.monitor.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.monitor.annotation.IgnoreLog;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * java类简单作用描述
 *
 * @Description: java类作用描述
 * @UpdateRemark: The modified content
 * @Version: 1.0
 */
@Aspect
@Slf4j
@Configuration
public class LogRecordAspect {

    @Pointcut("execution(* com.monitor.controller.*Controller.*(..))")
    public void executeService() {
    }

    @Around("executeService()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        IgnoreLog annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(IgnoreLog.class);
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes sra = (ServletRequestAttributes) ra;
        HttpServletRequest request = sra.getRequest();
        String url = request.getRequestURL().toString();
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String queryString = request.getQueryString();
        String bodyString = getBodyJson(pjp);
        log.info("请求参数pre, url: {}, method: {}, uri: {}, params: {}, body:{}",
                url, method, uri, queryString, bodyString);
        Long preTime = System.currentTimeMillis();
        Object result = pjp.proceed(); //调用目标方法
        Long responseTime = System.currentTimeMillis() - preTime;
        String resultString = JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect, SerializerFeature.WriteMapNullValue);

        if (annotation != null) {
            if (annotation.fieldName().length != 0) {
                JSONObject jsonObject = JSONObject.parseObject(resultString);
                for (String name : annotation.fieldName()) {
                    jsonObject.put(name, "ignoredField");
                }
                resultString = jsonObject.toString();
            } else {
                resultString = "ignoredResult";
            }
        }
        log.info("请求参数end, time:{} url: {},result:{}", responseTime, url, resultString);
        return result;
    }

    private String getBodyJson(ProceedingJoinPoint pjp) {
        Object[] args = pjp.getArgs();
        Stream<?> stream = ArrayUtils.isEmpty(args) ? Stream.empty() : Arrays.asList(args).stream();
        List<Object> logArgs = stream
                .filter(arg -> (!(arg instanceof HttpServletRequest) && !(arg instanceof HttpServletResponse) && !(arg instanceof MultipartFile)))
                .collect(Collectors.toList());
        //过滤后序列化无异常
        return JSON.toJSONString(logArgs);
    }
}
