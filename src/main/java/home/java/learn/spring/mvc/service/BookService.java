package home.java.learn.spring.mvc.service;

import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BookService {
    List<home.java.learn.spring.mvc.dto.BookDTO> getAllBooks();
    Book getBookById(Long id);
    BookDTO createBook(BookDTO book);
    void updateBook(Long id, Book book);
    void deleteBook(Long id);

    ResponseEntity uploadBook(MultipartFile file) throws IOException;

    List<BookDTO> getAllBooksByParameters(String bookTitle, String bookAuthor, Integer pageNumber, Integer pageSize);
}

