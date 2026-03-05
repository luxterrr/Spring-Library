package com.kaizen.Library.domains.book;

import com.kaizen.Library.DTOS.BookDTO;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_book")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String author;
    private String title;
    private String category;
    private Integer quantity;

    public Book(BookDTO livro) {
        this.author = livro.author();
        this.category = livro.category();
        this.title = livro.title();
        this.quantity = livro.quantity();
    }
}
