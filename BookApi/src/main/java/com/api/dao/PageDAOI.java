package com.api.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.EntityNotFoundException;

import com.api.entity.BookContent;

public interface PageDAOI {

    BookContent findPageById(int id) throws EntityNotFoundException, QueryTimeoutException;

    int count(int bookId) throws EntityExistsException, QueryTimeoutException;

    void insertNewPage(BookContent page)
            throws EntityExistsException, QueryTimeoutException;

    void updatePageById(BookContent page)
            throws EntityExistsException, QueryTimeoutException;

    void deletePageById(int pageId) throws EntityExistsException, QueryTimeoutException;

    BookContent findPageContentById(int id) throws EntityNotFoundException, QueryTimeoutException;
}
