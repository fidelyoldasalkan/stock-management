package com.fid.demo.service;

import com.fid.demo.controller.request.AddUserRequest;
import com.fid.demo.entity.User;
import com.fid.demo.repository.BaseRepository;

public interface IUserService extends IBaseService<User> {

    void validateExistUser(AddUserRequest request);

    void signUp(AddUserRequest request);

    Integer inquireUserByUsername(String username);
}
