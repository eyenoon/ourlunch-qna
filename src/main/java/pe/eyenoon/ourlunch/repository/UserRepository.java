package pe.eyenoon.ourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.eyenoon.ourlunch.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
