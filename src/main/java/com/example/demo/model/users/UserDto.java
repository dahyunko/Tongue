package com.example.demo.model.users;

import lombok.Getter;
import org.springframework.boot.autoconfigure.elasticsearch.ElasticsearchConnectionDetails;

@Getter
public class UserDto {
    private String userId;
    private String userName;
    private String userPassword;
    private String userAddress;
    private String userBirth;
}
