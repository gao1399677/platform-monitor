package com.monitor.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BiConsumer;

@Slf4j
public class ConvertUtil {

  public static <T,E> E  copyTo(T source , Class<E> destinationClass){
      try {
          if (source == null) {
              return null;
          }
          E e = destinationClass.newInstance();
          BeanUtils.copyProperties(source, e);
          return e;
      } catch (Exception e) {
          log.error("[copyTo] error:", e);
          return null;
      }
  }

    /**
     * 复制集合
     *
     * @param <E>
     * @param source
     * @param destinationClass
     * @return
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static <E> List<E> copyTo(List<?> source, Class<E> destinationClass) {
        if (source == null) {
            return null;
        }
        if (source.size() == 0) {
            return Collections.emptyList();
        }
        List<E> res = new ArrayList<E>(source.size());
        try {
            for (Object o : source) {
                E e = destinationClass.newInstance();
                BeanUtils.copyProperties(o, e);
                res.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <R, E> List<E> copyTo(List<?> source, Class<R> outClass, Class<E> innerClass, R r) {
        if (source.size() == 0) {
            return Collections.emptyList();
        }
        List<E> res = new ArrayList<E>(source.size());
        try {
            for (Object o : source) {
                Constructor<E> declaredConstructor = innerClass.getDeclaredConstructor(outClass);
                declaredConstructor.setAccessible(true);
                E e = declaredConstructor.newInstance(r);
                BeanUtils.copyProperties(o, e);
                res.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    public static <E> List<E> copyToCallMethod(List<?> source, Class<E> destinationClass, BiConsumer function) {
        if (source.size() == 0)
            return Collections.emptyList();
        List<E> res = new ArrayList<E>(source.size());
        try {
            for (Object o : source) {
                E e = destinationClass.getDeclaredConstructor().newInstance();
                BeanUtils.copyProperties(o, e);
                function.accept(o,e);
                res.add(e);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }


    public static String idHandle(String id) {
        if (StringUtils.isBlank(id)) {
            return id;
        } else if (id.length() <= 9) {
            return id.replaceAll("(?<=[\\w|\\W]{2})[\\w|\\W](?=[\\w|\\W]{2})", "*");
        }
        return id.replaceAll("(?<=[\\w|\\W]{6})[\\w|\\W](?=[\\w|\\W]{2})", "*");
    }

    public static String mobileEncrypt(String mobile) {
        if (StringUtils.isEmpty(mobile) || (mobile.length() != 11)) {
            return mobile;
        }
        return mobile.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
    }

}
