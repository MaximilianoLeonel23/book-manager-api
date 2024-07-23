package com.projects.book_manager_api.dto;

import java.time.LocalDate;

public record AuthorResponseDTO(
        Long id,
        String name,
        String surname,
        LocalDate birthday
) {
}
