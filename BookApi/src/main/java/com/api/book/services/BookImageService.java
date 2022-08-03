package com.api.book.services;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.QueryTimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.config.ApiConstantsConfiguration;
import com.api.dao.BookImageDAOI;
import com.api.entity.Image;
import com.common.Utility;

@Service
public class BookImageService implements BookImageServiceI{
	
	@Autowired
	private BookImageDAOI<Image> imageDAO;

	@Override
	public void deleteBookImage(String imageName) throws EntityExistsException, QueryTimeoutException {
		imageDAO.deleteImage(imageName);
	}

	@Override
	public void addBookImages(List<Image> images) throws EntityExistsException, QueryTimeoutException {
		imageDAO.addImages(images);
	}

	@Override
	public byte[] displayImage(String pictureName) throws FileNotFoundException {
		File imageFile = Path.of(ApiConstantsConfiguration.IMAGE_PATH, pictureName).toFile();
		
		if(!imageFile.exists()) {
			throw new FileNotFoundException();
		}
		
		FileInputStream inputStream = new FileInputStream(imageFile);
		
		byte[] bytes = new byte[0];
		
		try {
			bytes = inputStream.readAllBytes();
		} catch (IOException e) {
			
		} finally {
			Utility.close(inputStream);
		}	
		
		return bytes;
	}
	
}
