package com.api.user.service;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.api.user.request.UserModificationRequest;

import org.hibernate.QueryTimeoutException;

public interface UserServiceI {
    void createNewUser(UserModificationRequest request) throws EntityExistsException, QueryTimeoutException;

    void updateUserInformation(UserModificationRequest request) throws EntityNotFoundException, QueryTimeoutException;

    void createNewAdmin(UserModificationRequest request) throws EntityExistsException, QueryTimeoutException;

    void deleteUser(String userName);

    void deleteAdmin(String userName);

    void activateUser(String userName, String tokenKey);
}
