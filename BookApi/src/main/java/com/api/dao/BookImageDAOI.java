package com.api.dao;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;
import javax.persistence.QueryTimeoutException;


public interface BookImageDAOI<DAO> {
	void addImages(List<DAO> image) throws EntityExistsException, QueryTimeoutException;
	void deleteImage(String imageName) throws EntityNotFoundException, QueryTimeoutException;
}
