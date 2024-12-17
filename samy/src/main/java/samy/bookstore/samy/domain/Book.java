package samy.bookstore.samy.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "book")
@Data
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private LocalDate publishedDate;

    private int copiesAvailable = 50;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


}
