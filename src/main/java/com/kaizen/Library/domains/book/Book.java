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
@EqualsAndHashCode()

public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String author;
    private String title;
    private Integer volumes;

    public Book(BookDTO book) {
        this.author = book.author();
        this.title = book.title();
        this.volumes = book.volumes();
    }
}
