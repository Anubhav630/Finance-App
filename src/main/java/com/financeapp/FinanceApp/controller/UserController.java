package com.financeapp.FinanceApp.controller;

import com.financeapp.FinanceApp.dto.request.CreateUserRequest;
import com.financeapp.FinanceApp.dto.response.UserResponse;
import com.financeapp.FinanceApp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public UserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping
    public List<UserResponse> getUsers() {
        return userService.getAllUsers();
    }
}