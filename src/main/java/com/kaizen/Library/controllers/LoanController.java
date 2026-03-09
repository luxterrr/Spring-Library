package com.kaizen.Library.controllers;

import com.kaizen.Library.DTOS.LoanDTO;
import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/loans")
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

    @PatchMapping("/{id}/return")
    public ResponseEntity<String> returnLoan(@PathVariable Long id) throws Exception {
        loanService.returnLoan(id);
        return ResponseEntity.ok("BOOK RETURNED");
    }
}
