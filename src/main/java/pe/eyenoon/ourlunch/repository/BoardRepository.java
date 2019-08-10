package pe.eyenoon.ourlunch.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.entity.User;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Board findByUser(User user);
}
