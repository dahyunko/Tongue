package com.example.demo.controller;

import com.example.demo.model.users.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hi")
    public ResponseEntity<?> mainP(){

        String id = SecurityContextHolder.getContext().getAuthentication().getName();

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iter = authorities.iterator();
        GrantedAuthority auth = iter.next();
        String role = auth.getAuthority();

        Map<String, String> maps = new HashMap<>();
        maps.put("id", id);
        maps.put("role", role);
        maps.put("message", "hello");

        return new ResponseEntity<Map>(maps,HttpStatus.OK);
    }

    @PostMapping("/join")
    public ResponseEntity<?> joinProcess(@RequestBody UserDto userDto) throws Exception {
        try{
            userService.join(userDto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }

    }

    @PostMapping("/checkUserId")
    public ResponseEntity<?> checkIfExistId(@RequestParam("userId") String userId) throws Exception {
        if(userService.checkIfExistId(userId) != 0){
            return new ResponseEntity<Void>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
