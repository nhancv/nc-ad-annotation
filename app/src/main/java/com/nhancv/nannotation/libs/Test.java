package com.nhancv.nannotation.libs;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nhancao on 3/20/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD) //can use in method only.
public @interface Test {
    //should ignore this test?
    boolean enabled() default true;
}
