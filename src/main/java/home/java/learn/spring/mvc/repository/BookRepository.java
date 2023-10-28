package home.java.learn.spring.mvc.repository;


import home.java.learn.spring.mvc.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // Add custom query methods if needed
}

