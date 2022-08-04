package com.api.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.QueryTimeoutException;

import com.api.entity.BookContent;

public interface PageDAOI {
    
    BookContent findPageByBookId(int bookId) throws EntityExistsException, QueryTimeoutException;

    List<BookContent> findPagesByBookId(int bookId) throws EntityExistsException, QueryTimeoutException;

    void insertNewPage(String content, String title, int userId) throws EntityExistsException, QueryTimeoutException;

    void updatePageById(String content, String title, int BookId, int userId) throws EntityExistsException, QueryTimeoutException;

    void deletePageById(int BookId)throws EntityExistsException, QueryTimeoutException;
}
