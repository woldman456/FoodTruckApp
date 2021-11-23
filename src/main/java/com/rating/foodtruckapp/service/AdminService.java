package com.rating.foodtruckapp.service;

import com.rating.foodtruckapp.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AdminService {
    public User listUser(String email);

}
