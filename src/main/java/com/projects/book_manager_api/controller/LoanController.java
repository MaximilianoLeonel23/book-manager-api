package com.projects.book_manager_api.controller;

import com.projects.book_manager_api.dto.LoanResponseDTO;
import com.projects.book_manager_api.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping("/byUser")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByUserId(@RequestParam Long userId) {
        List<LoanResponseDTO> loanList = loanService.getLoanByUserId(userId);
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }

    @GetMapping("/byBook")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByBookId(@RequestParam Long bookId) {
        List<LoanResponseDTO> loanList = loanService.getLoanByBookId(bookId);
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }

    @GetMapping("/byAvailability")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByAvailability() {
        List<LoanResponseDTO> loanList = loanService.getLoanByAvailability();
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }

    @GetMapping("/byBorrowDateRange")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByBorrowDateRange(
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
            ) {
        List<LoanResponseDTO> loanList = loanService.getLoanByDateRange(startDate, endDate);
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }

    @GetMapping("/byUserAndBorrowDateRange")
    public ResponseEntity<List<LoanResponseDTO>> getLoanByUserAndBorrowDateRange(
            @RequestParam Long userId,
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate
    ) {
        List<LoanResponseDTO> loanList = loanService.getLoanByUserAndBorrowDateRange(userId, startDate, endDate);
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }

    @GetMapping("/byBookAndActive")
    public ResponseEntity<List<LoanResponseDTO>> getLoansActiveByBook(
            @RequestParam Long bookId
    ) {
        List<LoanResponseDTO> loanList = loanService.getLoansActiveByBook(bookId);
        if (loanList.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(loanList);
        }
    }
}
