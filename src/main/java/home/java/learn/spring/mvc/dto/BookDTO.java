package home.java.learn.spring.mvc.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {

    @JsonIgnore
    private Long id;
    private String bookTitle;
    private String bookAuthor;
    private int publishedYear;
}
