package com.projects.book_manager_api.controller;

import com.projects.book_manager_api.dto.AuthorResponseDTO;
import com.projects.book_manager_api.model.Author;
import com.projects.book_manager_api.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping
    public ResponseEntity<List<AuthorResponseDTO>> getAuthor(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String surname,
            @RequestParam(required = false) LocalDate fromDate,
            @RequestParam(required = false) LocalDate toDate
    ) {
        List<AuthorResponseDTO> authors = authorService.getAuthors(name, surname, fromDate, toDate);
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/multipleBooks")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorsWithMultipleBooks() {
        List<AuthorResponseDTO> authors = authorService.getAuthorsWithMultipleBooks();
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/availableBooks")
    public ResponseEntity<List<AuthorResponseDTO>> getAuthorsWithAvailableBooks() {
        List<AuthorResponseDTO> authors = authorService.getAuthorsWithAvailableBooks();
        if (!authors.isEmpty()) {
            return ResponseEntity.ok(authors);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
