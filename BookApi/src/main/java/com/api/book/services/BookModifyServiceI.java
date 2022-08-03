package com.api.book.services;

import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.FileUploadException;

import com.api.book.dto.BookModifycationDTO;
import com.api.book.request.BookAdditionRequest;
import com.api.book.request.BookModificationRequest;
import com.api.book.responses.BookPaginationResponse;
import com.common.PaginationRequest;


public interface BookModifyServiceI {

	void addNewBook(BookAdditionRequest request) throws EntityExistsException, QueryTimeoutException, FileUploadException;

	void updateBook(BookModificationRequest request, HttpSession session) throws OptimisticLockException, QueryTimeoutException, FileUploadException;

	void deleteBook(int bookId) throws OptimisticLockException, QueryTimeoutException;
	
	BookModifycationDTO findBookForModify(int bookId, HttpSession session) throws OptimisticLockException, QueryTimeoutException;

	BookPaginationResponse paging(PaginationRequest pagination, boolean isAdmin);

}
