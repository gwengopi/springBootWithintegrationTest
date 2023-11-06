package home.java.learn.spring.mvc.mapper;

import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface BookMapper {

    BookMapper MAPPER = Mappers.getMapper(BookMapper.class);

    @Mapping(source = "title", target = "bookTitle")
    @Mapping(source = "author", target = "bookAuthor")
    @Mapping(source = "year", target = "publishedYear")
    BookDTO toDTO(Book book);

    @Mapping(source = "bookTitle", target = "title")
    @Mapping(source = "bookAuthor", target = "author")
    @Mapping(source = "publishedYear", target = "year")
    Book toEntity(home.java.learn.spring.mvc.dto.BookDTO bookDTO);

}
