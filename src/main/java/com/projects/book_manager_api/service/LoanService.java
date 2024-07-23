package com.projects.book_manager_api.service;

import com.projects.book_manager_api.dto.LoanResponseDTO;
import com.projects.book_manager_api.model.Loan;
import com.projects.book_manager_api.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepository;

    public List<LoanResponseDTO> getLoanByUserId(Long id) {
        List<Loan> loans = loanRepository.findByUserId(id);
        return loans.stream().map(l -> new LoanResponseDTO(
                l.getId(),
                l.getUser().getId(),
                l.getBook().getId(),
                l.getBorrowDate(),
                l.getReturnDate(),
                l.getActive()
        )).collect(Collectors.toList());
    }

    public List<LoanResponseDTO> getLoanByBookId(Long id) {
        List<Loan> loans = loanRepository.findByBookId(id);
        return loans.stream().map(l -> new LoanResponseDTO(
                l.getId(),
                l.getUser().getId(),
                l.getBook().getId(),
                l.getBorrowDate(),
                l.getReturnDate(),
                l.getActive()
        )).collect(Collectors.toList());
    }

    public List<LoanResponseDTO> getLoanByAvailability() {
        List<Loan> loans = loanRepository.findByActiveTrue();
        return loans.stream().map(l -> new LoanResponseDTO(
                l.getId(),
                l.getUser().getId(),
                l.getBook().getId(),
                l.getBorrowDate(),
                l.getReturnDate(),
                l.getActive()
        )).collect(Collectors.toList());
    }

    public List<LoanResponseDTO> getLoanByDateRange(LocalDate startDate, LocalDate endDate) {
        List<Loan> loans = loanRepository.findByBorrowDateBetween(startDate, endDate);
        return loans.stream().map(l -> new LoanResponseDTO(
                l.getId(),
                l.getUser().getId(),
                l.getBook().getId(),
                l.getBorrowDate(),
                l.getReturnDate(),
                l.getActive()
        )).collect(Collectors.toList());
    }
}
