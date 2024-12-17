package samy.bookstore.samy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookInfo {

    private String title;
    private LocalDate publishedDate;
    private int copiesAvailable;
    private int authorId;
}
