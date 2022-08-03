package com.api.book.request;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class BookRequest implements Serializable {

	private static final long serialVersionUID = 6112456638321196047L;

	@NotEmpty(message = "{ERB001}")
	protected String bookName;

	@Positive(message = "{ERB008}")
	protected Long price;

	@NotEmpty(message = "{ERB002}")
	protected String author;

	@NotEmpty(message = "{ERB011}")
	protected String description;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public String getAuthorName() {
		return author;
	}

	public void setAuthorName(String author) {
		this.author = author;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
