package samy.bookstore.samy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import samy.bookstore.samy.domain.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {


    @Query("select b from Book b where b.author.name=:authorName")
    List<Book> findByName(String authorName);


    @Query("select b from Book b where b.publishedDate between :fromDate and :toDate")
    List<Book> findPublishedDateBetween(@Param("fromDate") LocalDate fromDate, @Param("toDate") LocalDate toDate);
}
