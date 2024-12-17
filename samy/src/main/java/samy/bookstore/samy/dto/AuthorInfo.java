package samy.bookstore.samy.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDate;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthorInfo {

    private String name;
    private LocalDate birthDate;
    private List<BookInfoWithoutAuthorId> books;
}
