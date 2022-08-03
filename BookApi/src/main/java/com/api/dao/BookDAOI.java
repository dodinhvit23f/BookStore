package com.api.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;

import com.api.entity.Book;
import com.base.BaseDataAccessI;
import com.common.PaginationRequest;


public interface BookDAOI extends BaseDataAccessI<Book> {
	Book findBookByNameAndAuthorName(String bookName, String authorName)
			throws NoResultException, QueryTimeoutException;

	Book findBookForModify(int bookId) throws NoResultException, QueryTimeoutException;

	List<Book> getBooks(PaginationRequest pagination, boolean isAdminstrator);

	int count(boolean isAdminstrator);

}
