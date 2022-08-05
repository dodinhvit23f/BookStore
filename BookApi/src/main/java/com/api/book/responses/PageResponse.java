package com.api.book.responses;

import com.common.PagationResponse;

public class PageResponse extends PagationResponse {
    private String bookContent;

    public PageResponse(){
        this.setSize(1);
    }
    
    public String getBookContent(){
        return this.bookContent;
    }

    public void setBookContent( String bookContent){
        this.bookContent = bookContent;
    }
}
