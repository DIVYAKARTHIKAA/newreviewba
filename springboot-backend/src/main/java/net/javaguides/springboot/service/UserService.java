package net.javaguides.springboot.service;

import java.util.List;

import net.javaguides.springboot.dto.request.UserRequest;
import net.javaguides.springboot.dto.response.UserResponse;



public interface UserService {

    List<UserResponse> getAllUsers();

    UserResponse getUser(Long uid);

    UserResponse updateUser(UserRequest request, Long uid);

  

	

}
