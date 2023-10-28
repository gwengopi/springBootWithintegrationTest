package home.java.learn.spring.mvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import home.java.learn.spring.mvc.controller.BookController;
import home.java.learn.spring.mvc.model.Book;
import home.java.learn.spring.mvc.repository.BookRepository;
import home.java.learn.spring.mvc.service.BookService;
import home.java.learn.spring.mvc.service.BookServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class BookControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @InjectMocks
    private static BookServiceImpl bookService;

/*    @MockBean
    private static BookServiceImpl bookServiceImpl;*/


    @MockBean
    private static BookRepository bookRepository;

    @InjectMocks
    private BookController bookController;

    @BeforeEach
    void beforeAll() {
        List<Book> mockBooks = Arrays.asList(
                new Book(1L, "Mock Book 1", "Mock Author 1", 2023),
                new Book(2L, "Mock Book 2", "Mock Author 2", 2024)
        );

        given(bookRepository.findAll()).willReturn(mockBooks);
        given(bookRepository.findById(any())).willReturn(Optional.ofNullable(mockBooks.get(0)));
//        given(bookServiceImpl.getAllBooks()).willReturn(mockBooks);
    }

    @Test
    public void testGetAllBooksFromMock() throws Exception {

        // Define the behavior of the bookService mock
//        when(bookService.getAllBooks()).thenReturn(mockBooks);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].title").value("Mock Book 1"))
                .andExpect(jsonPath("$[0].author").value("Mock Author 1"))
                .andExpect(jsonPath("$[0].year").value(2023))
                .andExpect(jsonPath("$[1].id").value(2))
                .andExpect(jsonPath("$[1].title").value("Mock Book 2"))
                .andExpect(jsonPath("$[1].author").value("Mock Author 2"))
                .andExpect(jsonPath("$[1].year").value(2024));
    }

//    @Test
    public void testGetAllBooks() throws Exception {

//        when(bookService.getAllBooks()).then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$").isArray());
//                .andExpect(MockMvcResultMatchers.content().)
    }

    @Test
    public void testGetBookById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void testCreateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Test Book");
        book.setAuthor("Test Author");
        book.setYear(2023);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/books")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdatebook() throws Exception {
/*        List<Book> mockBooks = Arrays.asList(
                new Book(1L, "Mock Book 1", "Mock Author 1", 2023),
                new Book(2L, "Mock Book 2", "Mock Author 2", 2024)
        );

        given(bookService.getAllBooks()).willReturn(mockBooks);*/

        List<Book> bookList = bookService.getAllBooks();
        Book updateBook =new Book(1L, "Mock","1990", 2023);
        updateBook.setAuthor("Kevin");
        given(bookRepository.save(any(Book.class))).willReturn(updateBook);
        when(bookRepository.save(any(Book.class))).thenReturn(updateBook);
        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateBook)))
//                .andExpect( jsonPath("$.author").value("Kevin"))
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());

        //verifying method call once with argument captor
//        verify(bookRepository).save(any(Book.class));
    }

    @Test
    public void testUpdateBook() throws Exception {
        Book book = new Book();
        book.setTitle("Updated Book Title");
        book.setAuthor("Updated Author");
        book.setYear(2025);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/1")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteBook() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/books/1"))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void testUpdateBookById()
        throws Exception {
        Book book = new Book();
        book.setTitle("Updated Book Title");
        book.setAuthor("Updated Author");
        book.setYear(2025);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/books/2")
                       .contentType("application/json")
                       .content(objectMapper.writeValueAsString(book)))
               .andExpect(status().isOk());
    }
}

