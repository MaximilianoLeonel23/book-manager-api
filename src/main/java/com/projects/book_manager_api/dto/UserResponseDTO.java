package com.projects.book_manager_api.dto;

public record UserResponseDTO(
        Long id,
        String name,
        String email
) {
}
