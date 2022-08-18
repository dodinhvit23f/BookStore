package com.api.dao;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;

public interface UserDAOI {
    void addNewUser(String userName, String password, String email) throws EntityExistsException, QueryTimeoutException;

    void addNewAdmin(String userName, String password, String email)
            throws EntityExistsException, QueryTimeoutException;

    void changePassword(String userName, String newPassword) throws NoResultException, QueryTimeoutException;

    void activeUser(String userName) throws NoResultException, QueryTimeoutException;

    void banUser(String userName) throws NoResultException, QueryTimeoutException;
}
