package cn.bigdb.smartscreen.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface JsonFilters {

	JsonFilter[] values();
}
