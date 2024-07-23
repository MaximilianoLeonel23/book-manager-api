package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByNameContaining(String name);

    List<User> findByEmailContaining(String email);

    List<User> findByNameContainingAndEmailContaining(String name, String email);

}
