package com.api.user.controller;

import com.api.user.request.UserModificationRequest;
import com.base.BaseResponse;
import com.common.Utility;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface UserControllerI {
    final String CU_USER_URL = "user";
    final String CUD_ADMIN_USER_URL = "admin/users";

    @Transactional
    @PostMapping(CU_USER_URL)
    @CrossOrigin(Utility.Cross_URL)
    public BaseResponse createUser(UserModificationRequest request);

    @Transactional
    @PutMapping(CU_USER_URL)
    @CrossOrigin(Utility.Cross_URL)
    public BaseResponse updateUser(UserModificationRequest request);

    @Transactional
    @PostMapping(CUD_ADMIN_USER_URL)
    @CrossOrigin(Utility.Cross_URL)
    public BaseResponse createNewUser(UserModificationRequest request);

    @Transactional
    @DeleteMapping(CUD_ADMIN_USER_URL)
    @CrossOrigin(Utility.Cross_URL)
    public BaseResponse deleteUser(@RequestParam ("userName") String userName);

    @Transactional
    @GetMapping(CU_USER_URL)
    @CrossOrigin(Utility.Cross_URL)
    public BaseResponse activateUser(@RequestParam ("userName") String userName, @RequestParam ("token") String tokenKey);

}
