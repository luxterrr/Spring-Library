package com.kaizen.Library.book;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    public final BookRepository bookRepository;
    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<BookModel> getAll() {return bookRepository.findAll();}
    public BookModel save(BookModel bookModel) {return bookRepository.save(bookModel);}
    public void delete(Long code) {bookRepository.deleteById(code);}
}
