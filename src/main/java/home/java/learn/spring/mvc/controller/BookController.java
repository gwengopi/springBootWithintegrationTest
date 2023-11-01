package home.java.learn.spring.mvc.controller;

import home.java.learn.spring.mvc.custom.exception.NotFoundException;
import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookDTO> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity getBookById(@PathVariable Long id) throws NotFoundException {
        Book bookById = bookService.getBookById(id);
        if(bookById != null && bookById.getAuthor() != null)
            return ResponseEntity.ok(bookById);
        else {
            throw new NotFoundException("Requested book is not found");
        }
//        return ResponseEntity.status(HttpStatusCode.valueOf(404)).body("Requested book is not found");
        }


    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookService.createBook(book);
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }
}
