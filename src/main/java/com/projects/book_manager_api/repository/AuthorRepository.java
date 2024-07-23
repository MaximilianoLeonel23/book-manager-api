package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByNameLikeOrSurnameLike(String name, String surname);

    @Query("SELECT a FROM Author a WHERE (SELECT COUNT(b) FROM Book b WHERE b.author = a) > 1")
    List<Author> findAuthorWithMultipleBooks();

    @Query("SELECT DISTINCT a FROM Author a JOIN a.books b WHERE b.status = 'AVAILABLE'")
    List<Author> findAuthorWithAvailableBooks();
}
