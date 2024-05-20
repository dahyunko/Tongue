package com.example.demo.service;

import com.example.demo.model.users.MyPageDto;
import com.example.demo.model.users.UserDto;

public interface UserService {
    void join(UserDto userDto) throws Exception;

    int checkIfExistId(String userId) throws Exception;

    UserDto getUserInfo(String userId) throws Exception;
}
