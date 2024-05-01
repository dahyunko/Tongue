package com.example.demo.model.mapper;

import com.example.demo.model.users.UserDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    UserDto findByUsername(String username) throws Exception;
    void join(UserDto userDto) throws Exception;

    int checkIfExistId(String userId) throws Exception;
}
