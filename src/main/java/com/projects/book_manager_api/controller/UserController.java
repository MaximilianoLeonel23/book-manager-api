package com.projects.book_manager_api.controller;

import com.projects.book_manager_api.dto.UserResponseDTO;
import com.projects.book_manager_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getUsers(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email
    ) {
        List<UserResponseDTO> userList = userService.getUsers(name, email);
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }

    @GetMapping("/byNumberOfLoans")
    public ResponseEntity<List<UserResponseDTO>> getUsersByNumberOfLoans(
            @RequestParam Integer numberLoans
    ) {
        List<UserResponseDTO> userList = userService.getUsersByNumberOfLoans(numberLoans);
        if (userList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(userList);
        }
    }
}
