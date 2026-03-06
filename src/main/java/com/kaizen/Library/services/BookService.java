package com.kaizen.Library.services;

import com.kaizen.Library.DTOS.BookDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.repositories.BookRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public boolean validateBook(Book item) {
        if (item.getVolumes() <= 0){
            return false;
        }else return true;
    }

    public Book findBookByCode(Long code) throws Exception {
        return this.bookRepository.findBookByCode(code).orElseThrow(() -> new EntityNotFoundException("BOOK NOT FOUND"));
    }

    public Book createBook(BookDTO item) {
        Book newBook = new Book(item);
        this.saveBook(newBook);
        return newBook;
    }

    public List<Book> getAllBooks() {return bookRepository.findAll();}
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    public void deleteBook(Long code) {
        bookRepository.deleteById(code);
    }
}
