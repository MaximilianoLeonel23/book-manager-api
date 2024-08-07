package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("SELECT b FROM Book b WHERE b.isbn = :isbn AND b.status = 'AVAILABLE'")
    Optional<Book> findBookByISBNAndAvailable(@Param("isbn") String isbn);
}
