package com.api.book.dto;

import java.io.Serializable;

public class BookDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private int bookId;

	private String bookName;

	private Long price;

	private String author;

	private String description;
	
	private int discount;

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

	public int getId() {
		return bookId;
	}

	public void setId(int id) {
		this.bookId = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	private String[] bookImage;

	public String[] getBookImage() {
		return bookImage;
	}

	public void setBookImage(String[] bookImage) {
		this.bookImage = bookImage;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int disscount) {
		this.discount = disscount;
	}
}
