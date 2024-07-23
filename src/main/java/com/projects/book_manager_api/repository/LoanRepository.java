package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long id);
    List<Loan> findByBookId(Long book);
    List<Loan> findByActiveTrue();

    List<Loan> findByBorrowDateBetween(LocalDate startDate, LocalDate endDate);
}
