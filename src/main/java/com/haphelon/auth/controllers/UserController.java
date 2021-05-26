package com.haphelon.auth.controllers;

import com.haphelon.auth.entities.User;
import com.haphelon.auth.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping("users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "user", method = RequestMethod.POST)
    public ResponseEntity<HashMap<String, Object>> createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public ResponseEntity<String> testBasicAuth() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World");
    }

    @RequestMapping(value = "test2", method = RequestMethod.GET)
    public ResponseEntity<String> testBasicAuth2() {
        return ResponseEntity.status(HttpStatus.OK).body("Hello World2");
    }
}
