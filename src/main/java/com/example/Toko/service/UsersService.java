package com.example.Toko.service;

import com.example.Toko.model.Users;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UsersService {
    Users addUsers(Users users);
    void deleteUsers(String UserId);
    Users updateUsers(Users users);
    List<Users> getAllUsers();
    Users getUsersDetail(String UserId);
}
