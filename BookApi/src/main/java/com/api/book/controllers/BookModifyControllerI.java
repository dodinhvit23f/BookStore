package com.api.book.controllers;

import java.io.FileNotFoundException;

import javax.persistence.EntityExistsException;
import javax.persistence.QueryTimeoutException;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.api.book.request.BookAdditionRequest;
import com.api.book.request.BookContentModificationRequest;
import com.api.book.request.BookModificationRequest;
import com.api.book.responses.BookPaginationResponse;
import com.api.book.responses.BookResponse;
import com.base.BaseResponse;
import com.common.PaginationRequest;


public interface BookModifyControllerI {
	
	final String ADMIN_URL = "admin/books";
	final String ADMIN_RUD_BY_ID_URL = ADMIN_URL + "/id/{bookId}";
	final String ADMIN_CREATE_CONTENT_BY_ID_URL = ADMIN_RUD_BY_ID_URL+"/page";
	final String ADMIN_RUD_CONTENT_BY_ID_URL = ADMIN_CREATE_CONTENT_BY_ID_URL+"/id/{pageId}";
	
	final String IMAGE_DISPLAY = "images/{imageName}";
	final String Cross_URL = "*";
	
	@Transactional
	@RequestMapping(value = ADMIN_URL, method = RequestMethod.POST)
	@CrossOrigin(origins  = Cross_URL)
	BaseResponse addNewBook(@Valid BookAdditionRequest request)throws EntityExistsException, QueryTimeoutException, FileUploadException;

	@PutMapping(ADMIN_URL)
	@Transactional
	@CrossOrigin(origins = Cross_URL)
	BaseResponse updateBook(@Valid  BookModificationRequest request, HttpSession session)throws EntityExistsException, QueryTimeoutException, FileUploadException;

	@Transactional
	@DeleteMapping(ADMIN_RUD_BY_ID_URL)
	@CrossOrigin(origins = Cross_URL)
	BaseResponse deleteBook(@PathVariable("bookId") int bookId)throws EntityExistsException, QueryTimeoutException;
	
	@Transactional
	@GetMapping(ADMIN_RUD_BY_ID_URL)
	@CrossOrigin(origins = Cross_URL)
	BookResponse getBookForUpdate(@PathVariable("bookId") int bookId, HttpSession session)throws EntityExistsException, QueryTimeoutException;
	
	@GetMapping(ADMIN_URL)
	@CrossOrigin(origins = Cross_URL)
	BookPaginationResponse getBook(PaginationRequest pagination)throws EntityExistsException, QueryTimeoutException;
	
	@GetMapping(IMAGE_DISPLAY)
	@ResponseBody
	ResponseEntity<byte[]> getBookImage(@PathVariable("imageName") String pictureName) throws FileNotFoundException;
	
	@Transactional
	@RequestMapping(value = ADMIN_CREATE_CONTENT_BY_ID_URL, method = RequestMethod.POST)
	@CrossOrigin(origins  = Cross_URL)
	BaseResponse addNewBookChapter(@Valid BookContentModificationRequest request)throws EntityExistsException, QueryTimeoutException, FileUploadException;

	@Transactional
	@PutMapping(ADMIN_RUD_CONTENT_BY_ID_URL)
	@CrossOrigin(origins = Cross_URL)
	BaseResponse updateBookChapter(@Valid  BookContentModificationRequest request, HttpSession session)throws EntityExistsException, QueryTimeoutException, FileUploadException;

	@Transactional
	@DeleteMapping(ADMIN_RUD_CONTENT_BY_ID_URL)
	@CrossOrigin(origins = Cross_URL)
	BaseResponse updateBookChapter(@PathVariable("pageId") int pageId )throws EntityExistsException, QueryTimeoutException;
}