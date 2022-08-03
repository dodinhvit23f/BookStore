package com.api.book.responses;

import java.util.List;

import com.api.book.dto.BookModifycationDTO;
import com.common.PagationResponse;

public class BookPaginationResponse extends PagationResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<BookModifycationDTO> books;

	public List<BookModifycationDTO> getBooks() {
		return books;
	}

	public void setBooks(List<BookModifycationDTO> books) {
		this.books = books;
	}

}
