package com.projects.book_manager_api.dto;


import java.time.LocalDate;

public record LoanResponseDTO(
        Long id,
        Long userId,
        Long bookId,
        LocalDate borrowDate,
        LocalDate returnDate,
        Boolean active
) {
}
