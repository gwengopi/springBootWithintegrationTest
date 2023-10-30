package home.java.learn.spring.mvc.mapper;

import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;
import org.mapstruct.Mapper;

@Mapper
public interface BookMapper {

    BookDTO toDTO(Book book);
    Book toEntity(BookDTO bookDTO);

}
