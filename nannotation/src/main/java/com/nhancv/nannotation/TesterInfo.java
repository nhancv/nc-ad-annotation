package com.nhancv.nannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by nhancao on 3/20/17.
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) //on class level
public @interface TesterInfo {

    Priority priority() default Priority.MEDIUM;

    String[] tags() default "";

    String createdBy() default "nhancao";

    String lastModified() default "3/20/17";

    enum Priority {
        LOW, MEDIUM, HIGH
    }

}
