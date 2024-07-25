package com.projects.book_manager_api.repository;

import com.projects.book_manager_api.model.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByUserId(Long id);
    List<Loan> findByBookId(Long book);
    List<Loan> findByActiveTrue();

    List<Loan> findByBorrowDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT l FROM Loan l WHERE l.user.id = :userId AND l.borrowDate BETWEEN :startDate AND :endDate")
    List<Loan> findByUserIdAndBorrowDateBetween(Long userId, LocalDate startDate, LocalDate endDate);

    @Query("SELECT l FROM Loan l WHERE l.book.id = :bookId And l.active = true")
    List<Loan> findByBookAndActive(Long bookId);
}
