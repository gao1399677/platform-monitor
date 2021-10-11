package com.monitor.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.util.Map;

@Slf4j
public class BeanUtil {

    public static Map<String, String> BeanToMap(Object bean) {
        try {
            Map<String, String> describe = BeanUtils.describe(bean);
            return describe;
        } catch (Exception e) {
            log.error("[BeanUtil] error", e);
            return null;
        }
    }

}
