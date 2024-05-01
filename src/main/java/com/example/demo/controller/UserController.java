package com.example.demo.controller;

import com.example.demo.model.users.UserDto;
import com.example.demo.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/hi")
    public ResponseEntity<?> mainP(){
        return new ResponseEntity<Void>(HttpStatus.OK);
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
