package home.java.learn.spring.mvc.service;

import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;

import java.util.List;

public interface BookService {
    List<home.java.learn.spring.mvc.dto.BookDTO> getAllBooks();
    Book getBookById(Long id);
    BookDTO createBook(BookDTO book);
    void updateBook(Long id, Book book);
    void deleteBook(Long id);
}

