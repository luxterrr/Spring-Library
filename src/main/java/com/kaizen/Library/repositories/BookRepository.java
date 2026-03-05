package com.kaizen.Library.repositories;

import com.kaizen.Library.domains.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book>findBookByCode(Long code);
}
