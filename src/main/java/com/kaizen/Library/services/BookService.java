package com.kaizen.Library.services;

import com.kaizen.Library.DTO.BookDTO;
import com.kaizen.Library.domains.book.Book;
import com.kaizen.Library.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    public BookRepository bookRepository;

    public boolean validateBook(Book item)throws Exception{
        if (item.getQuantity() <= 0){
            return false;
        }else return true;
    }

    public Book findBookByCode(Long code) throws Exception{
        return this.bookRepository.findBookByCode(code).orElseThrow(() -> new Exception("LIVRO NAO ENCONTRADO"));
    }

    public Book createBook(BookDTO item) {
        Book newBook = new Book(item);
        this.saveBook(newBook);
        return newBook;
    }

    public List<Book> getAllBooks() {return bookRepository.findAll();}
    public void saveBook(Book book) {this.bookRepository.save(book);}

    public void deleteBook(Long code) {
        bookRepository.deleteById(code);
    }
}
