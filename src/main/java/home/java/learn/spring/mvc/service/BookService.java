package home.java.learn.spring.mvc.service;

import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book createBook(Book book);
    void updateBook(Long id, Book book);
    void deleteBook(Long id);
}

