package com.kaizen.Library.controllers;

import com.kaizen.Library.DTO.LoanDTO;
import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping
    public ResponseEntity<Loan> createLoan(@RequestBody LoanDTO loan )throws Exception {
        Loan newLoan = this.loanService.createLoan(loan);
        return new ResponseEntity<>(newLoan, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Loan>> getAllLoans() {
        List<Loan> loans = this.loanService.getAllLoans();
        return new ResponseEntity<>(loans, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> returnLoan(@PathVariable Long id) {
        loanService.returnLoan(id);
        return ResponseEntity.noContent().build();
    }

}
