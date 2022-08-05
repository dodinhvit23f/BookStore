package com.api.book.services;

import javax.persistence.EntityNotFoundException;
import javax.persistence.QueryTimeoutException;
import java.sql.Timestamp;

import com.api.book.request.PageModificationRequest;
import com.api.book.responses.PageResponse;
import com.api.dao.PageDAOI;
import com.api.entity.BookContent;
import com.common.Utility;

import org.springframework.beans.factory.annotation.Autowired;

public class PageService implements PageServiceI {
    @Autowired
    private PageDAOI dao;

    @Override
    public void insertNewPage(PageModificationRequest request) throws QueryTimeoutException {
        Timestamp insertTime = new java.sql.Timestamp(System.currentTimeMillis());

        BookContent page = new BookContent();

        page.setContent(request.getBookContent());
        page.setInsertedBy(1);
        page.setInsertedDate(insertTime);
        page.setIsFree(request.isFree());
        page.getBook().setId(request.getBookId());

        dao.insertNewPage(page);
    }

    @Override
    public void updatePage(PageModificationRequest request) throws EntityNotFoundException, QueryTimeoutException {

        BookContent pageUpdated = this.dao.findPageById(request.getPageId());

        if (Utility.isNull(pageUpdated)) {
            throw new EntityNotFoundException();
        }

        Timestamp updateTime = new Timestamp(System.currentTimeMillis());

        if (!Utility.isEmpty(request.getBookContent()) && !pageUpdated.getContent().equals(request.getBookContent())) {
            pageUpdated.setContent(request.getBookContent());
        }

        if (!Utility.isNull(request.isFree()) && pageUpdated.isFree() != request.isFree()) {
            pageUpdated.setIsFree(request.isFree());
        }

        pageUpdated.setUpdateBy(1);
        pageUpdated.setUpdateDate(updateTime);

        dao.updatePageById(pageUpdated);
    }

    @Override
    public void delteSort(int pageId) throws QueryTimeoutException {
        this.dao.deletePageById(pageId);
    }

    @Override
    public PageResponse getPageContentByBookId(int bookId, int pageNo) throws QueryTimeoutException {
        PageResponse response = new PageResponse();
        response.setBookContent(dao.findPageContentById(pageNo).getContent());
        response.setCurrentPage(pageNo);
        response.setTotalPage(dao.count(bookId));
        
        return response;
    }

}
