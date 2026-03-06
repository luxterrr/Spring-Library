package com.kaizen.Library.controllers;

import com.kaizen.Library.DTOS.BookDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.domains.googlebook.VolumeInfo;
import com.kaizen.Library.services.BookService;
import com.kaizen.Library.services.GoogleBooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook (@RequestBody BookDTO book) {
        Book newBook = bookService.createBook(book);
        return new ResponseEntity<>(newBook, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = this.bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    @DeleteMapping("/{code}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long code) {
        bookService.deleteBook(code);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/external/{isbn}")
    public ResponseEntity<Book> addExternalBook (@PathVariable String isbn) {
        Book info = googleBooksService.importByIsbn(isbn);

        if (info == null) {
            return ResponseEntity.notFound().build();
        }

        Book newBook = new Book();
        newBook.setTitle(info.getTitle());

        if (info.getAuthor() != null && ! info.getAuthor().isEmpty()) {
            newBook.setAuthor(String.join(", ", info.getAuthor()));
        }

        Book savedBook = bookService.saveBook(newBook);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }
}
