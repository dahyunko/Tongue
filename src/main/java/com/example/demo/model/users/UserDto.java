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
    private UserRole role;

    public UserDto(String userId, String userName, String userPassword, String userAddress, String userBirth, UserRole role) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userAddress = userAddress;
        this.userBirth = userBirth;
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserDto{" +
                "userId='" + userId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                ", userAddress='" + userAddress + '\'' +
                ", userBirth='" + userBirth + '\'' +
                ", role=" + role +
                '}';
    }
}
