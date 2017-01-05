package cn.bigdb.smartscreen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFilter {
	
	Class<?> mixin() default Object.class;  
	Class<?> target() default Object.class;  
}
