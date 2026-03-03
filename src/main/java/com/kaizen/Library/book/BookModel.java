package com.kaizen.Library.book;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_book")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class BookModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long code;

    private String author;
    private String title;
    private String category;
    private Integer quantity;


}
