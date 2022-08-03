package com.api.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import com.common.Utility;

@Service
public class Message {
	
	private static MessageSource staticMessageResource;
	
	@Autowired
	public Message(MessageSource messageResource) {
		staticMessageResource = messageResource;
	}

	public static String getMessageContent(String code) {
		return staticMessageResource.getMessage(code, null, Utility.VN_LOCALE);
	}
}