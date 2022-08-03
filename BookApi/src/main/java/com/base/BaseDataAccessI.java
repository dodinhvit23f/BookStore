package com.base;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.QueryTimeoutException;

public interface BaseDataAccessI<ENTITY> {
	
	void insert(ENTITY entity) throws EntityExistsException;

	void update(ENTITY entity) throws OptimisticLockException;

	ENTITY findByName(String name) throws NoResultException, QueryTimeoutException;
	
	ENTITY findById(int id, Class<ENTITY> clazz);
	
	boolean isLocked(ENTITY entity);
}
