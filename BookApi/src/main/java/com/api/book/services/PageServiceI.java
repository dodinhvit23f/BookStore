package com.api.book.services;

import javax.persistence.EntityNotFoundException;
import javax.persistence.QueryTimeoutException;
import javax.validation.Valid;

import com.api.book.request.PageModificationRequest;
import com.api.book.responses.PageResponse;

public interface PageServiceI {
    void insertNewPage(@Valid PageModificationRequest request) throws QueryTimeoutException;

    void updatePage(PageModificationRequest request) throws EntityNotFoundException, QueryTimeoutException;;

    void delteSort(int pageId) throws QueryTimeoutException;

    PageResponse getPageContentByBookId(int pageId, int pageNo) throws QueryTimeoutException;
}
