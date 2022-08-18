package com.api.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.EntityNotFoundException;

import com.api.entity.BookContent;

public interface PageDAOI {

    BookContent findPageById(int id) throws EntityNotFoundException, QueryTimeoutException;

    int count(int bookId) throws QueryTimeoutException;

    void insertNewPage(BookContent page)
            throws EntityExistsException, QueryTimeoutException;

    void updatePageById(BookContent page)
            throws EntityNotFoundException, QueryTimeoutException;

    void deletePageById(int pageId) throws EntityNotFoundException, QueryTimeoutException;

    BookContent findPageContentById(int id) throws EntityNotFoundException, QueryTimeoutException;
}
