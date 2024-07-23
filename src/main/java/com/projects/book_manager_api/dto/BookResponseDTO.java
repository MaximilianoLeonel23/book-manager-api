package com.projects.book_manager_api.dto;

import com.projects.book_manager_api.model.Status;

public record BookResponseDTO(
        Long id,
        String title,
        String isbn,
        Long author,
        Status status,
        Integer published_year
) {
}
