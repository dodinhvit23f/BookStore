package com.api.dao;

import com.base.BaseDataAccess;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;
import javax.persistence.QueryTimeoutException;
import javax.persistence.TypedQuery;

import com.api.entity.User;
import com.api.entity.Role;
import com.common.Utility;

public class UserDAO extends BaseDataAccess<User> implements UserDAOI {
    private static String USER_ROLE = "User";
    private static String ADMINISTRATOR_ROLE = "Administrator";

    @Override
    public User findByName(String name) throws NoResultException, QueryTimeoutException {

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT U ");
        sql.append("FROM User U ");
        sql.append("WHERE U.userName = :userName ");

        TypedQuery<User> typeQuery = this.manager.createQuery(sql.toString(), User.class)
                .setParameter("userName", name);
        if (Utility.isEmpty(typeQuery)) {
            throw new NoResultException();
        }
        return typeQuery.getSingleResult();
    }

    private User createNewUser(String userName, String password, String email, String roleName) {

        Role role = this.findRoleByName(USER_ROLE);

        User user = new User();
        user.setUserName(userName);
        user.setPassword(password);
        user.setEmail(email);
        user.setIsDelete(false);
        user.setActived(false);
        user.setRole(role);

        this.insert(user);

        return user;
    }

    private Role findRoleByName(String roleName) {
        StringBuilder sql = new StringBuilder();

        sql.append("SELECT R ");
        sql.append("FROM Role R ");
        sql.append("WHERE R.name = :roleName ");

        TypedQuery<Role> typeQuery = this.manager.createQuery(sql.toString(), Role.class)
                .setParameter("roleName", roleName);
        if (Utility.isEmpty(typeQuery)) {
            throw new NoResultException();
        }

        return typeQuery.getSingleResult();
    }

    @Override
    public void addNewUser(String userName, String password, String email)
            throws EntityExistsException, QueryTimeoutException {
        this.createNewUser(userName, password, email, USER_ROLE);
    }

    @Override
    public void addNewAdmin(String userName, String password, String email)
            throws EntityExistsException, QueryTimeoutException {
        this.createNewUser(userName, password, email, ADMINISTRATOR_ROLE);
    }

    @Override
    public void changePassword(String userName, String newPassword) throws NoResultException, QueryTimeoutException {
        User user = this.findByName(userName);
        user.setPassword(newPassword);
        this.update(user);
    }

    @Override
    public void activeUser(String userName) throws NoResultException, QueryTimeoutException {
        User user = this.findByName(userName);
        user.setActived(true);
        this.update(user);
    }

    @Override
    public void banUser(String userName) throws NoResultException, QueryTimeoutException {
        User user = this.findByName(userName);
        user.setBaned(!user.isBaned());
        this.update(user);
    }

}
