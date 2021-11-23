package com.rating.foodtruckapp.serviceimpl;


import com.rating.foodtruckapp.exception.InformationNotFoundException;
import com.rating.foodtruckapp.model.User;
import com.rating.foodtruckapp.repository.UserRepository;
import com.rating.foodtruckapp.service.AdminService;
import com.rating.foodtruckapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserServiceImpl userServiceImpl;


    @Override
    public User listUser(String email) {
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new InformationNotFoundException("no user with " + email + " found");
        }else {
            return user;
        }
    }
}
