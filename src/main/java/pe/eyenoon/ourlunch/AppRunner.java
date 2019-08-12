package pe.eyenoon.ourlunch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.entity.User;
import pe.eyenoon.ourlunch.entity.enums.BoardType;
import pe.eyenoon.ourlunch.repository.BoardRepository;
import pe.eyenoon.ourlunch.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.stream.IntStream;

@Component
public class AppRunner implements ApplicationRunner {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        User user = userRepository.save(User.builder()
                        .name("bsc")
                        .password("1234")
                        .email("bsc@naver.com")
                        .createdDate(LocalDateTime.now())
                        .build());

        IntStream.rangeClosed(1, 200).forEach(index ->
                boardRepository.save(Board.builder()
                        .title("Content " + index)
                        .content("Content Example " + index)
                        .boardType(BoardType.question)
                        .createdDate(LocalDateTime.now())
                        .updatedDate(LocalDateTime.now())
                        .user(user).build()));
    }
}
