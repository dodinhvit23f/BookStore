package com.api.book.request;

import javax.validation.constraints.Min;

public class BookModificationRequest extends BookAdditionRequest {

	private static final long serialVersionUID = 1L;

	
	@Min(value = 1, message = "{ERB010}")
	private int bookId;

	private String[] deletedImageFiles;

	public String[] getDeletedImageFiles() {
		return deletedImageFiles;
	}

	public void setDeletedImageFiles(String[] imageFiles) {
		this.deletedImageFiles = imageFiles;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int id) {
		this.bookId = id;
	}
}
