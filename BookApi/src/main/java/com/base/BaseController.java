package com.base;

import com.api.message.Message;
import com.common.MessageCode;


public class BaseController {

	protected BaseResponse successResponse() {
		return new BaseResponse(MessageCode.SUCCESS, Message.getMessageContent(MessageCode.SUCCESS));
	}
	
}
