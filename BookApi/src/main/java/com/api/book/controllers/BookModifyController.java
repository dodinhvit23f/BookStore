package com.api.book.controllers;

import java.io.FileNotFoundException;

import javax.persistence.EntityExistsException;
import javax.persistence.OptimisticLockException;
import javax.persistence.QueryTimeoutException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.request.BookAdditionRequest;
import com.api.book.request.BookContentModificationRequest;
import com.api.book.request.BookModificationRequest;
import com.api.book.responses.BookPaginationResponse;
import com.api.book.responses.BookResponse;
import com.api.book.services.BookImageServiceI;
import com.api.book.services.BookModifyServiceI;
import com.api.message.Message;
import com.base.BaseController;
import com.base.BaseResponse;
import com.common.MessageCode;
import com.common.PaginationRequest;



@RestController
public class BookModifyController extends BaseController implements BookModifyControllerI {
	
	private BookResponse getBookResponse() {
		return new BookResponse(MessageCode.SUCCESS, Message.getMessageContent(MessageCode.SUCCESS));
	}
	
	@Autowired
	BookModifyServiceI bookservice;
	
	@Autowired
	BookImageServiceI imageservice;
	
	
	@Override
	public BaseResponse addNewBook( BookAdditionRequest request) throws EntityExistsException, QueryTimeoutException, FileUploadException{
		bookservice.addNewBook(request);
		return this.successResponse();
	}

	@Override
	public BaseResponse updateBook(BookModificationRequest request, HttpSession session) throws OptimisticLockException, QueryTimeoutException, FileUploadException {
		bookservice.updateBook(request, session);
		return this.successResponse();
	}

	@Override
	public BaseResponse deleteBook(int bookId) throws OptimisticLockException, QueryTimeoutException{
		bookservice.deleteBook(bookId);
		return this.successResponse();
	}

	@Override
	public BookResponse getBookForUpdate(int bookId, HttpSession session) throws OptimisticLockException, QueryTimeoutException{
		BookResponse response = getBookResponse();
		response.setBook(bookservice.findBookForModify(bookId, session));
		
		return response;
	}	
	
	@Override
	public BookPaginationResponse getBook(PaginationRequest pagination) throws OptimisticLockException, QueryTimeoutException{
		
		BookPaginationResponse response = bookservice.paging(pagination, true);
		
		return response;
	}

	@Override
	public ResponseEntity<byte[]> getBookImage(String pictureName) throws FileNotFoundException {		
		return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageservice.displayImage(pictureName));
	}

	@Override
	public BaseResponse addNewBookChapter(@Valid BookContentModificationRequest request)
			throws EntityExistsException, QueryTimeoutException, FileUploadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse updateBookChapter(@Valid BookContentModificationRequest request, HttpSession session)
			throws EntityExistsException, QueryTimeoutException, FileUploadException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BaseResponse updateBookChapter(int pageId) throws EntityExistsException, QueryTimeoutException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
