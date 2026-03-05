package com.kaizen.Library.services;

import com.kaizen.Library.DTO.LoanDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.domains.user.User;
import com.kaizen.Library.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

        if (!isValid){
            throw new Exception("EMPRESITMO NAO AUTORIZADO");
        }

        Loan newLoan = new Loan();
        newLoan.setClient(client);
        newLoan.setItem(item);
        newLoan.setTimestamp(LocalDateTime.now());

        item.setQuantity(item.getQuantity() - 1);

        this.loanRepository.save(newLoan);
        this.userService.saveUser(client);
        this.bookService.saveBook(item);

        return newLoan;
    }

    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    public void returnLoan(Long id) {
        Book newBook = new Book();
        newBook.setQuantity(newBook.getQuantity() + 1);
        loanRepository.deleteById(id);

    }

}
