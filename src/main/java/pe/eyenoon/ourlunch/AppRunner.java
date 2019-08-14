package pe.eyenoon.ourlunch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.entity.User;
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

        IntStream.rangeClosed(1, 21).forEach(index ->
                boardRepository.save(Board.builder()
                                          .title("담백이의 깊은 주름 " + index)
                                          .writer("피스")
                                          .content("This 주름의 깊이는 " + index + "CM이다.")
                                          .createdDate(LocalDateTime.now())
                                          .updatedDate(LocalDateTime.now())
                                          .user(user)
                                          .build()));
    }
}
