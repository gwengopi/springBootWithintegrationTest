package home.java.learn.spring.mvc.controller;

import home.java.learn.spring.mvc.custom.exception.NotFoundException;
import home.java.learn.spring.mvc.dto.BookDTO;
import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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

    @GetMapping(value = "getAllBooksByParameters")
    public List<BookDTO> getAllBooksByParmeters
            (@RequestParam(value = "bookTitle",required = false) String bookTitle,
            @RequestParam(value = "bookAuthor",required = false) String bookAuthor,
             @RequestParam(value = "pageNumber",required = false)Integer pageNumber,
             @RequestParam(value = "pageSize",required = false)Integer pageSize)
    {
        return bookService.getAllBooksByParameters(bookTitle,bookAuthor,pageNumber,pageSize);
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
    public ResponseEntity createBook(@RequestBody @Validated BookDTO book) {
        bookService.createBook(book);
        return ResponseEntity.status(HttpStatusCode.valueOf(200)).body("Book created successfully");
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody Book book) {
        bookService.updateBook(id, book);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadBooks(@RequestParam("file") MultipartFile file) throws IOException {

        bookService.uploadBook(file);
        return ResponseEntity.status(HttpStatus.OK).body("Successfully uploaded and processed books.");
    }
}
