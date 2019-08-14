package pe.eyenoon.ourlunch.model;

import lombok.Data;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.entity.User;

import java.time.LocalDateTime;

public class RequestDTO {

    @Data
    public static class BoardRequestDTO {
        private Long idx;
        private String title;
        private String writer;
        private String content;
        private LocalDateTime createdDate;
        private LocalDateTime updatedDate;
        private User user;

        public Board toEntity() {
            return new Board(this.idx, this.title, this.writer, this.content, this.createdDate, LocalDateTime.now());
        }
    }
}
