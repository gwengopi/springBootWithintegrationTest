package home.java.learn.spring.mvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookDTO {

    @JsonIgnore
    private Long id;
    @NotNull
    @NotEmpty()
    private String bookTitle;
    private String bookAuthor;
    private int publishedYear;
}
