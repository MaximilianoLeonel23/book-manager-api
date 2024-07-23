package com.projects.book_manager_api.service;

import com.projects.book_manager_api.dto.UserResponseDTO;
import com.projects.book_manager_api.model.User;
import com.projects.book_manager_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserResponseDTO> getUsers(String name, String email) {
        List<User> usersFound = new ArrayList<>();
        if (name != null && !name.isEmpty() && email != null && !email.isEmpty()) {
            usersFound = userRepository.findByNameContainingAndEmailContaining(name, email);
        } else if (name != null && !name.isEmpty()) {
            usersFound = userRepository.findByNameContaining(name);
        } else if (email != null && !email.isEmpty()) {
            usersFound = userRepository.findByEmailContaining(email);
        } else {
            usersFound = userRepository.findAll();
        }
        return usersFound.stream().map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail()
        )).collect(Collectors.toList());
    }
}
