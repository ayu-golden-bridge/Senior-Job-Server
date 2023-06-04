package com.seniorjob.seniorjobserver.controller;

import com.seniorjob.seniorjobserver.domain.entity.UserEntity;
import com.seniorjob.seniorjobserver.dto.UserDto;
import com.seniorjob.seniorjobserver.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 구직자/사업주 회원생성API
    // POST /api/users
    @PostMapping
    public ResponseEntity<UserEntity> createUser(@RequestBody UserDto userDto) {
        UserEntity userEntity = userService.createUser(userDto);
        return ResponseEntity.ok(userEntity);
    }
}
