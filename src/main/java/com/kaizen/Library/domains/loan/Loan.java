package com.kaizen.Library.domains.loan;


import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "tb_loan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Book item;

    private LocalDateTime timestamp;
    private LocalDateTime deadline;
    private StatusLoan statusLoan;
}
