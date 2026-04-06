package com.financeapp.FinanceApp.service;

import com.financeapp.FinanceApp.dto.request.CreateUserRequest;
import com.financeapp.FinanceApp.dto.response.UserResponse;
import com.financeapp.FinanceApp.entity.User;
import com.financeapp.FinanceApp.entity.UserStatus;
import com.financeapp.FinanceApp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserResponse createUser(CreateUserRequest request) {

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .role(request.getRole())
                .status(UserStatus.ACTIVE)
                .createdAt(LocalDateTime.now())
                .build();

        User saved = userRepository.save(user);

        return mapToResponse(saved);
    }

    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private UserResponse mapToResponse(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }
}