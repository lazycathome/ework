package cn.bigdb.smartscreen.test.common;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;

import cn.bigdb.smartscreen.model.User;
import cn.bigdb.smartscreen.utils.Utils;

public class Json {

	@Test
	public void jackson(){
		ObjectMapper objectMapper = new ObjectMapper();
		User user = new User();
		user.setId("test");
		String result = null;
		try {
			result = objectMapper.writeValueAsString(user);
		} catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}
	
	@Test
	public void json2Object(){
		String str = "{\"id\":\"test\",\"username\":null,\"nickname\":null,\"password\":null,\"level\":null,\"roleId\":null,\"email\":null,\"phone\":null,\"createTime\":0,\"lastTime\":0,\"description\":null,\"memo\":null}";
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			User user = objectMapper.readValue(str, User.class);
			System.out.println(user.getId());
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	@Test
	public void list2Json(){
		List<String> list = new ArrayList<String>();
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		System.out.println(Utils.object2Json(list));
		
	}
	
}
