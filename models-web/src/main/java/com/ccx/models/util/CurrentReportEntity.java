package com.ccx.models.util;

import java.lang.annotation.*;
/**
 * Created by xzd on 2017/9/8.
 */
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CurrentReportEntity {
    String value() default "";
}
