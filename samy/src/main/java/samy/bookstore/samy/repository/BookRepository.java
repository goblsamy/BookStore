package samy.bookstore.samy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import samy.bookstore.samy.domain.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b where b.author.name=:authorName")
    List<Book> findByName(String authorName);
}
