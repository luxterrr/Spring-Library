package com.kaizen.Library.repositories;

import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.domains.loan.StatusLoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    List<Loan> findByStatusLoan(StatusLoan statusLoan);
}
