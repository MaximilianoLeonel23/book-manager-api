package com.projects.book_manager_api.service;

import com.projects.book_manager_api.dto.AuthorResponseDTO;
import com.projects.book_manager_api.model.Author;
import com.projects.book_manager_api.repository.AuthorRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthorService {
    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private EntityManager entityManager;

    public List<AuthorResponseDTO> getAuthors(String name, String surname, LocalDate fromDate, LocalDate toDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Author> criteriaQuery = criteriaBuilder.createQuery(Author.class);
        Root<Author> author = criteriaQuery.from(Author.class);

        List<Predicate> predicates = new ArrayList<>();

        if (name != null && !name.isEmpty()) {
            predicates.add(criteriaBuilder.like(author.get("name"), "%" + name + "%"));
        }

        if (surname != null && !surname.isEmpty()) {
            predicates.add(criteriaBuilder.like(author.get("surname"), "%" + surname + "%"));
        }

        if (fromDate != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(author.get("birthday"), fromDate));
        }

        if (toDate != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(author.get("birthday"), toDate));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Author> authorsFound = entityManager.createQuery(criteriaQuery).getResultList();
        return authorsFound.stream().map(a -> new AuthorResponseDTO(
                a.getId(),
                a.getName(),
                a.getSurname(),
                a.getBirthday()
        )).collect(Collectors.toList());
    }

    public List<AuthorResponseDTO> getAuthorsWithMultipleBooks() {
        List<Author> authors = authorRepository.findAuthorWithMultipleBooks();
        return authors.stream().map(author -> new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getBirthday()
        )).collect(Collectors.toList());
    }

    public List<AuthorResponseDTO> getAuthorsWithAvailableBooks() {
        List<Author> authors = authorRepository.findAuthorWithAvailableBooks();
        return authors.stream().map(author -> new AuthorResponseDTO(
                author.getId(),
                author.getName(),
                author.getSurname(),
                author.getBirthday()
        )).collect(Collectors.toList());
    }
}
