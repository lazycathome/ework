package cn.bigdb.smartscreen.annotation.filter;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"contentResource"})
public interface ResourceInfoFilter {

}
