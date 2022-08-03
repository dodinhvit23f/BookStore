package com.api.book.services;

import java.io.FileNotFoundException;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.QueryTimeoutException;

import org.springframework.stereotype.Service;

import com.api.entity.Image;

@Service
public interface BookImageServiceI {
	
	void addBookImages(List<Image> images)throws EntityExistsException, QueryTimeoutException;
	
	byte[] displayImage(String pictureName) throws FileNotFoundException;

	void deleteBookImage(String imageName) throws EntityNotFoundException, QueryTimeoutException;

}
