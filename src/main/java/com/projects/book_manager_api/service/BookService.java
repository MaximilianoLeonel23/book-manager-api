package com.projects.book_manager_api.service;

import com.projects.book_manager_api.dto.BookResponseDTO;
import com.projects.book_manager_api.model.Author;
import com.projects.book_manager_api.model.Book;
import com.projects.book_manager_api.model.Status;
import com.projects.book_manager_api.repository.AuthorRepository;
import com.projects.book_manager_api.repository.BookRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @PersistenceContext
    private EntityManager entityManager;

    public List<BookResponseDTO> getBooks(
            String title,
            String isbn,
            String authorName,
            String authorSurname,
            Status status,
            Integer fromYear,
            Integer toYear
    ) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Book> criteriaQuery = criteriaBuilder.createQuery(Book.class);
        Root<Book> book = criteriaQuery.from(Book.class);

        List<Predicate> predicates = new ArrayList<>();

        if (title != null && !title.isEmpty()) {
            predicates.add(criteriaBuilder.like(book.get("title"), "%" + title + "%"));
        }

        if (isbn != null && !isbn.isEmpty()) {
            predicates.add(criteriaBuilder.equal(book.get("isbn"), isbn));
        }


        if (authorName != null || authorSurname != null) {
            Subquery<Long> authorSubquery = criteriaQuery.subquery(Long.class);
            Root<Author> author = authorSubquery.from(Author.class);
            authorSubquery.select(author.get("id"));

            List<Predicate> authorPredicates = new ArrayList<>();
            if (authorName != null && !authorName.isEmpty()) {
                authorPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(author.get("name")), "%" + authorName.toLowerCase() + "%"));
            }

            if (authorSurname != null && !authorSurname.isEmpty()) {
                authorPredicates.add(criteriaBuilder.like(criteriaBuilder.lower(author.get("surname")), "%" + authorSurname.toLowerCase() + "%"));
            }

            authorSubquery.where(authorPredicates.toArray(new Predicate[0]));

            predicates.add(book.get("author").get("id").in(authorSubquery));
        };


        if (status != null) {
            predicates.add(criteriaBuilder.equal(book.get("status"), status));
        }

        if (fromYear != null) {
            predicates.add(criteriaBuilder.greaterThanOrEqualTo(book.get("publishedYear"), fromYear));
        }

        if (toYear != null) {
            predicates.add(criteriaBuilder.lessThanOrEqualTo(book.get("publishedYear"), toYear));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));
        List<Book> books = entityManager.createQuery(criteriaQuery).getResultList();

        return books.stream().map(b -> new BookResponseDTO(
                b.getId(),
                b.getTitle(),
                b.getIsbn(),
                b.getAuthor().getId(),
                b.getStatus(),
                b.getPublishedYear()
        )).collect(Collectors.toList());

    }
}
