package com.projects.book_manager_api.dto;

import com.projects.book_manager_api.model.Status;

public record BookDetailedResponseDTO(
        Long id,
        String title,
        String isbn,
        AuthorResponseDTO author,
        Status status,
        Integer published_year
) {
}
