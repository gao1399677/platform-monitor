package com.monitor.annotation;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface IgnoreLog {

    String[] fieldName() default {};
}
