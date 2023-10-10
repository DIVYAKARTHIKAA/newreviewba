package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

import lombok.RequiredArgsConstructor;
import net.javaguides.springboot.dto.request.UserRequest;
import net.javaguides.springboot.dto.response.UserResponse;
import net.javaguides.springboot.model.User;
import net.javaguides.springboot.model.enume.Role;
import net.javaguides.springboot.repository.UserRepository;
import net.javaguides.springboot.service.UserService;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    

    @Override
    public List<UserResponse> getAllUsers() {
        List<User> userList = userRepository.findAll();
        return userList.stream()
                .filter(user -> !user.getRole().equals(Role.ADMIN))
                .map(this::mapUserToUserResponse)
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(Long uid) {
        User user = userRepository.findByUid(uid);
        return mapUserToUserResponse(user);
    }

    @Override
    public UserResponse updateUser(UserRequest request, Long uid) {
        User user = userRepository.findByUid(uid);
        User newUser = new User();
        if (user != null) {
            newUser = User.builder()
                    .name(request.getName())
                    .email(request.getEmail())
                    .password(request.getPassword())
                    .role(request.getRole())
                    .isEnabled(request.getIsEnabled())
                    .build();
            userRepository.save(newUser);
        }
        return mapUserToUserResponse(newUser);
    }

    

    private UserResponse mapUserToUserResponse(User user) {
        return UserResponse.builder()
                .uid(user.getUid())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .isEnabled(user.getIsEnabled())
                .build();
    }

	
}


