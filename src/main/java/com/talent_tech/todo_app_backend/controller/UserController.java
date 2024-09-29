package com.talent_tech.todo_app_backend.controller;

import com.talent_tech.todo_app_backend.model.UserEntity;
import com.talent_tech.todo_app_backend.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.getAllUsers();
    }
    @PostMapping
    public ResponseEntity createUser(@RequestBody UserEntity userEntity){
        try{
            return ResponseEntity.ok(userService.createUser(userEntity));
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
