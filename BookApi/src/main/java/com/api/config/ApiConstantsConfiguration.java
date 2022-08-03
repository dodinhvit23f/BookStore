package com.api.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource(value = "classpath:config.properties")
public class ApiConstantsConfiguration{
	
	public static String IMAGE_PATH;
	public static  String Cross_URL = "";
	
	ApiConstantsConfiguration(){
	
	}
	
	@Value("${application.image.path}")
	public void setIamgePath(String imagePath) {
		IMAGE_PATH = imagePath;
	}
	
	@Value("${application.cors.policy}")
	public void setCrossUrl(String url) {
		Cross_URL = url;
	}
}
