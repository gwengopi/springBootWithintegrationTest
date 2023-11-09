package home.java.learn.spring.mvc.service;

import home.java.learn.spring.mvc.mapper.BookMapper;
import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.repository.BookRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import home.java.learn.spring.mvc.dto.BookDTO;
import org.springframework.web.multipart.MultipartFile;

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


    public ResponseEntity uploadBook(MultipartFile file) throws IOException {
        List<BookDTO> books;

        try {
            books = new BufferedReader(new InputStreamReader(file.getInputStream()))
                    .lines()
                    .map(line -> line.split(","))
                    .filter(parts -> parts.length >= 3)
                    .map(parts -> {
                        BookDTO book = new BookDTO();
                        book.setBookTitle(parts[0]);
                        book.setBookAuthor(parts[1]);
//                        book.setPublishedYear(parts[2]);
                        return book;
                    })
                    .collect(Collectors.toList());

            books.stream().forEach(System.out::println);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error processing the uploaded file.");
        }

        if (books.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No valid books found in the uploaded file.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }
}
