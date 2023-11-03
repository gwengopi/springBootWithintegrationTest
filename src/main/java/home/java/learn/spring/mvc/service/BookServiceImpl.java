package home.java.learn.spring.mvc.service;

import home.java.learn.spring.mvc.mapper.BookMapper;
import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import home.java.learn.spring.mvc.dto.BookDTO;

@Service
@AllArgsConstructor
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookMapper bookMapper;

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public BookDTO createBook(BookDTO  bookDTO) {
        Book book = bookMapper.toEntity(bookDTO);
        return bookMapper.toDTO(bookRepository.save(book));
    }

    @Override
    public void updateBook(Long id, Book book) {
        if (bookRepository.existsById(id)) {
            book.setId(id);
            bookRepository.save(book);
        }
    }

    @Override
    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
