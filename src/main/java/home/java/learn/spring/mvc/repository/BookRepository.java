package home.java.learn.spring.mvc.repository;


import home.java.learn.spring.mvc.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RepositoryRestResource(collectionResourceRel = "book",path = "book")
public interface BookRepository extends JpaRepository<Book, Long> {

//    @RestResource(path = "title", rel = "title")
    public List<Book> findByTitle(@Param("title") String title, Pageable p);


}

