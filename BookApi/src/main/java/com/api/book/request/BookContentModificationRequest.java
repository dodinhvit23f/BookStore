package com.api.book.request;

public class BookContentModificationRequest extends BookContentRequest {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bookId;

	private String chapterTitle;

	private String chapterContent;

	private boolean isFree;

	public String getChapterContent() {
		return chapterContent;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public void setChapterContent(String chapterContent) {
		this.chapterContent = chapterContent;
	}

	public boolean isFree() {
		return isFree;
	}

	public void setFree(boolean isFree) {
		this.isFree = isFree;
	}

	public String getChapterTitle() {
		return chapterTitle;
	}

	public void setChapterTitle(String chapterTitle) {
		this.chapterTitle = chapterTitle;
	}
}
