package com.api.user.controller;

import com.api.user.request.UserModificationRequest;
import com.base.BaseController;
import com.base.BaseResponse;

import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController extends BaseController implements UserControllerI {

    @Override
    public BaseResponse createUser(UserModificationRequest request) {
        
        return null;
    }

    @Override
    public BaseResponse updateUser(UserModificationRequest request) {
      
        return null;
    }

    @Override
    public BaseResponse createNewUser(UserModificationRequest request) {
        
        return null;
    }

    @Override
    public BaseResponse deleteUser(String userName) {
        
        return null;
    }

    @Override
    public BaseResponse activateUser(String userName, String tokenKey) {
        
        return null;
    }
    
}
