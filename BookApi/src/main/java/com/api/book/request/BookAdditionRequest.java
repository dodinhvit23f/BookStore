package com.api.book.request;

import org.springframework.web.multipart.MultipartFile;

public class BookAdditionRequest extends BookRequest{

	private static final long serialVersionUID = 6112456638321196047L;
	
	
	protected MultipartFile[] imageFiles;

	public MultipartFile[] getImageFiles() {
		return imageFiles;
	}

	public void setImageFiles(MultipartFile[] imageFiles) {
		this.imageFiles = imageFiles;
	}
}
