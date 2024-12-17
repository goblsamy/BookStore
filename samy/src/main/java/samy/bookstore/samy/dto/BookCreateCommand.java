package samy.bookstore.samy.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookCreateCommand {

    @NotNull(message = "Cannot be null!")
    @NotBlank(message = "Cannot be blank!")
    @NotEmpty(message = "Cannot be empty!")
    private String title;

    @Past(message = "Publishing date cannot be in the future!")
    private LocalDate publishedDate;

    @NotNull(message = "Author id cannot be null!")
    private int authorId;
}
