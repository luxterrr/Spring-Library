package com.kaizen.Library.book;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<BookModel> getAll() {return bookService.getAll();}

    @PostMapping("/{code}")
    public BookModel create (@RequestBody BookModel bookModel) {return bookService.save(bookModel);}

    @DeleteMapping
    public void delete (@PathVariable Long code) {bookService.delete(code);}

}
