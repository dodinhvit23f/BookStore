package com.api.book.responses;

import java.util.List;

import com.api.book.dto.PageModificationDTO;
import com.common.PagationResponse;

public class PagePaginationResponse extends PagationResponse{
    List<PageModificationDTO> pages;

    public List<PageModificationDTO> getPage(){
        return this.pages;
    }

    public void setPages(List<PageModificationDTO> pages){
        this.pages = pages;
    }
}
