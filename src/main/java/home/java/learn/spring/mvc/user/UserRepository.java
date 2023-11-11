package home.java.learn.spring.mvc.user;

import home.java.learn.spring.mvc.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
