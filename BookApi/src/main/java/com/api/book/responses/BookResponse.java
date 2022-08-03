package com.api.book.responses;

import com.api.book.dto.BookModifycationDTO;
import com.base.BaseResponse;

public class BookResponse extends BaseResponse{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public BookResponse() {
		super();
	}

	public BookResponse(String code, String message) {
		super(code, message);
	}

	private BookModifycationDTO book;

	public BookModifycationDTO getBook() {
		return book;
	}

	public void setBook(BookModifycationDTO book) {
		this.book = book;
	}
	
	
}
