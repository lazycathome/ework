package cn.bigdb.smartscreen.annotation.filter;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

@JsonIgnoreProperties(value={"equipContents", "heartbeats", "equipUpdates"})
public interface EquipInfoFilter {

}
