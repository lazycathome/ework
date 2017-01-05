package com.bloomp.push;

import java.util.List;

public interface PushService {

	int push(Push push);
	
	int push(List<Push> pushs);
	
	int push(List<String> accountList, String content);
}
