package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameLikeOrSurnameLike(String name, String surname);
}
