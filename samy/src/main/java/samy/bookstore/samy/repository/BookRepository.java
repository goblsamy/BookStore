package samy.bookstore.samy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import samy.bookstore.samy.domain.Book;

public interface BookRepository extends JpaRepository<Book, Long> {


}
