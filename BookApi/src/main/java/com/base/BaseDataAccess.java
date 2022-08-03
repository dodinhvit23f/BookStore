package com.base;

import java.io.Serializable;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.OptimisticLockException;

import org.springframework.beans.factory.annotation.Autowired;

public abstract class BaseDataAccess<ENTITY extends Serializable> implements BaseDataAccessI<ENTITY> {
	@Autowired
	protected EntityManager manager;

	public void insert(ENTITY entity) throws EntityExistsException {
		manager.persist(entity);
	}

	public void update(ENTITY entity) throws OptimisticLockException {
		manager.merge(entity);
	}

	public ENTITY findById(int id, Class<ENTITY> clazz) {
		return (ENTITY) manager.find(clazz, id);
	}

	public boolean isLocked(ENTITY entity) {
		return manager.getLockMode(entity) == LockModeType.OPTIMISTIC;
	}
}
