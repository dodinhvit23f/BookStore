package com.api.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TypedQuery;

import com.api.entity.BookContent;
import com.base.BaseDataAccess;
import com.common.Utility;

public class PageDAO extends BaseDataAccess<BookContent> implements PageDAOI {

    @Override
    public BookContent findByName(String name) throws NoResultException, QueryTimeoutException {
        return null;
    }

    @Override
    public BookContent findPageById(int id) throws EntityNotFoundException, QueryTimeoutException {
        return this.manager.find(BookContent.class, id);
    }

    @Override
    public int count(int bookId) throws QueryTimeoutException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(B) ");
        sql.append("FROM BookContent B ");
        sql.append("JOIN Book ON Book.id = :bookId");

        return (int) this.manager.createQuery(sql.toString(), Integer.class)
                .setParameter("bookId", bookId).getSingleResult();
    }

    @Override
    public BookContent findPageContentById(int id) throws EntityNotFoundException, QueryTimeoutException {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT B.content ");
        sql.append("FROM BookContent B ");
        sql.append("WHERE B.id = :id");
        TypedQuery<BookContent> typeQ = this.manager.createQuery(sql.toString(), BookContent.class).setParameter("id", id);

        if(Utility.isEmpty(typeQ)){
            throw new EntityNotFoundException();
        }

        BookContent page = typeQ.getSingleResult();

        return page;
    }

    @Override
    public void insertNewPage(BookContent page)
            throws EntityExistsException, QueryTimeoutException {
        this.insert(page);
    }

    @Override
    public void updatePageById(BookContent page)
            throws EntityNotFoundException, QueryTimeoutException {
        this.update(page);
    }

    @Override
    public void deletePageById(int pageId) throws EntityNotFoundException, QueryTimeoutException {
        BookContent pageUpdated = this.findPageById(pageId);
        pageUpdated.setIsDeleted(true);
        this.update(pageUpdated);
    }

}