package com.rating.foodtruckapp.service;

import com.rating.foodtruckapp.controller.dto.UserRegistrationDto;
import com.rating.foodtruckapp.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByEmail(String email);

    User save(UserRegistrationDto registration);

}
