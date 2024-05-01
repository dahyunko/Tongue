package com.example.demo.service;

import com.example.demo.model.mapper.UserMapper;
import com.example.demo.model.users.CustomUserDetails;
import com.example.demo.model.users.UserDto;
import com.example.demo.model.users.UserRole;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserMapper userMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserMapper userMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userMapper = userMapper;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void join(UserDto userDto) throws Exception {
        log.info("join service");
        try{
            UserDto user = new UserDto(
                    userDto.getUserId(),
                    userDto.getUserName(),
                    bCryptPasswordEncoder.encode(userDto.getUserPassword()),
                    userDto.getUserAddress(),
                    userDto.getUserBirth(),
                    UserRole.ADMIN
            );
            userMapper.join(user);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public int checkIfExistId(String userId) throws Exception {
        return userMapper.checkIfExistId(userId);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            UserDto user = userMapper.findByUsername(username);
            if(user != null){
                return new CustomUserDetails(user);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return null;
    }
}
