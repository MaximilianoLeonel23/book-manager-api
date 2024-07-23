package com.projects.book_manager_api.controller;

import com.projects.book_manager_api.dto.BookDetailedResponseDTO;
import com.projects.book_manager_api.dto.BookResponseDTO;
import com.projects.book_manager_api.model.Book;
import com.projects.book_manager_api.model.Status;
import com.projects.book_manager_api.repository.AuthorRepository;
import com.projects.book_manager_api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<BookResponseDTO>> getBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) String isbn,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false) String authorSurname,
            @RequestParam(required = false) Status status,
            @RequestParam(required = false) Integer fromYear,
            @RequestParam(required = false) Integer toYear
    ) {
        List<BookResponseDTO> books = bookService.getBooks(title, isbn, authorName, authorSurname, status, fromYear, toYear);
        if (books.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(books);
        }
    }

    @GetMapping("/available")
    public ResponseEntity<BookDetailedResponseDTO> getBookByAvailabilityAndISBN(
            @RequestParam String isbn
    ) {
        BookDetailedResponseDTO book = bookService.findBookByISBNAndAvailable(isbn);
        if (book != null) {
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }

    }
}
