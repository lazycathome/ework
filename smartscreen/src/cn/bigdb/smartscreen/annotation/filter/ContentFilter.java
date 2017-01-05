package cn.bigdb.smartscreen.annotation.filter;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"equipContents"})
public interface ContentFilter {

}
