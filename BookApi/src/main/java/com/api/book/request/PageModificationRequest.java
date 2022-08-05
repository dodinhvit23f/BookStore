package com.api.book.request;

public class PageModificationRequest {

    private int pageId;
    private int bookId;
    private String bookContent;
    private boolean isFree;

    public int getPageId() {
        return this.pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookContent() {
        return bookContent;
    }

    public void setBookContent(String bookContent) {
        this.bookContent = bookContent;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean isFree) {
        this.isFree = isFree;
    }
}
