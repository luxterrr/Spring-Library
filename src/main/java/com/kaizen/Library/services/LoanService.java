package com.kaizen.Library.services;

import com.kaizen.Library.DTOS.LoanDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.domains.loan.Status;
import com.kaizen.Library.domains.user.User;
import com.kaizen.Library.repositories.LoanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanRepository loanRepository;

    //@Autowired
   // private RestTemplate restTemplate;

    public Loan createLoan(LoanDTO loan)throws Exception {
        User client = this.userService.findUserbyId(loan.clientId());
        Book item = this.bookService.findBookByCode(loan.itemId());

        userService.validateUser(client);
        bookService.validateBook(item);

        boolean isValid = userService.validateUser(client) && bookService.validateBook(item);

        if (!isValid) {
            throw new Exception("LOAN NOT AUTORIZED");
        }

        Loan newLoan = new Loan();
        newLoan.setClient(client);
        newLoan.setItem(item);
        newLoan.setTimestamp(LocalDateTime.now());
        newLoan.setStatus(Status.PENDENT);

        item.setQuantity(item.getQuantity() - 1);

        this.loanRepository.save(newLoan);
        this.userService.saveUser(client);
        this.bookService.saveBook(item);

        return newLoan;
    }

    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    @Transactional
    public void returnLoan(Long id) throws Exception {
        Loan loan = loanRepository.findById(id).orElseThrow(EntityNotFoundException::new) ;

        Book book = loan.getItem();

        book.setQuantity(book.getQuantity() + 1);
        loan.setStatus(Status.RETURNED);
    }
}
