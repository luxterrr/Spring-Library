package com.kaizen.Library.services;

import com.kaizen.Library.DTOS.LoanDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.loan.Loan;
import com.kaizen.Library.domains.loan.StatusLoan;
import com.kaizen.Library.domains.user.StatusUser;
import com.kaizen.Library.domains.user.User;
import com.kaizen.Library.repositories.LoanRepository;
import com.kaizen.Library.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class LoanService {

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    public LoanService(EmailService emailService) {
        this.emailService = emailService;
    }

    public Loan createLoan(LoanDTO loan)throws Exception {
        User client = this.userService.findUserbyId(loan.clientId());
        Book item = this.bookService.findBookByCode(loan.itemId());

        userService.validateUser(client);
        bookService.validateBook(item);

        boolean isValid = userService.validateUser(client) && bookService.validateBook(item);

        if (!isValid) {
            throw new RuntimeException("LOAN IS NOT AUTHORIZED, REASON: BOOK OUT OF STOCK, CLIENT INACTIVE OR ALREADY HAVE A LOAN");
        }

        Loan newLoan = new Loan();
        newLoan.setClient(client);
        newLoan.setItem(item);
        newLoan.setTimestamp(LocalDateTime.now());
        newLoan.setDeadline(LocalDateTime.now().plusMinutes(1));
        newLoan.setStatusLoan(StatusLoan.PENDENT);

        item.setQuantity(item.getQuantity() - 1);
        client.setOnLoan(true);

        this.loanRepository.save(newLoan);
        this.userService.saveUser(client);
        this.bookService.saveBook(item);

        String deadLineStr = newLoan.getDeadline().format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        emailService.sendEmail(newLoan.getClient().getEmail(), "LOAN OF THE BOOK" + item.getTitle() +  "WAS CONFIRMED",
                "HELLO, YOUR LOAN WAS REALIZED. TERM TO RETURN " + deadLineStr);

        return newLoan;
    }

    public List<Loan> getAllLoans() {
        return this.loanRepository.findAll();
    }

    @Transactional
    public void returnLoan(Long id) {
        Loan loan = loanRepository.findById(id).orElseThrow(RuntimeException::new) ;

        Book book = loan.getItem();
        User client = loan.getClient();

        book.setQuantity(book.getQuantity() + 1);

        client.setStatusUser(StatusUser.ACTIVE);

        loan.setStatusLoan(StatusLoan.RETURNED);

        emailService.sendEmail(client.getEmail(), "RETURNED CONFIRMED", "THE BOOK WAS RETURNED, TY <3");
    }

    @Scheduled(fixedRate = 1000)
    public void checkLateLoans () {
        LocalDateTime now = LocalDateTime.now();

        List<Loan> pendentLoans = loanRepository.findByStatusLoan(StatusLoan.PENDENT);

        for (Loan loan : pendentLoans) {
            if (loan.getDeadline().isBefore(now)) {
                System.out.println("LOAN ID" + loan.getId() + "IS LATE");

                loan.setStatusLoan(StatusLoan.LATE);
                loanRepository.save(loan);

                User client = loan.getClient();
                client.setStatusUser(StatusUser.INACTIVE);
                userRepository.save(client);

                emailService.sendEmail(client.getEmail(), "THE TERM OF THE BOOK EXPIRED", "THE TERM WAS AT " + loan.getDeadline() + "YOUR STUPID BTCH");
            }
        }
    }
}
