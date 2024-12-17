package samy.bookstore.samy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import samy.bookstore.samy.domain.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}
