package com.api.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;

import com.api.entity.BookContent;
import com.base.BaseDataAccess;

public class PageDAO extends BaseDataAccess<BookContent> implements PageDAOI {

    @Override
    public BookContent findByName(String name) throws NoResultException, QueryTimeoutException {

        return null;
    }

    @Override
    public BookContent findPageByBookId(int bookId) throws EntityExistsException, QueryTimeoutException {

        return this.manager.find(BookContent.class, bookId);
    }

    @Override
    public List<BookContent> findPagesByBookId(int bookId) throws EntityExistsException, QueryTimeoutException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT B.* ");
        sql.append("FROM BookContent B");
        sql.append("JOIN Book ON Book.id = {bookId}");
        
        return  this.manager.createQuery(" SELECT", BookContent.class).getResultList();
    }

    @Override
    public void insertNewPage(String content, String title, int userId)
            throws EntityExistsException, QueryTimeoutException {

    }

    @Override
    public void updatePageById(String content, String title, int BookId, int userId)
            throws EntityExistsException, QueryTimeoutException {

    }

    @Override
    public void deletePageById(int BookId) throws EntityExistsException, QueryTimeoutException {

    }

}