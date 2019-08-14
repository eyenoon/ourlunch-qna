package pe.eyenoon.ourlunch.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.model.RequestDTO;
import pe.eyenoon.ourlunch.repository.BoardRepository;

import java.time.LocalDateTime;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public Page<Board> findBoardList(Pageable pageable) {
        pageable = PageRequest.of(pageable.getPageNumber() <= 0 ? 0 : pageable.getPageNumber() - 1,
                                  pageable.getPageSize(),
                                  new Sort(Sort.Direction.DESC, "idx"));
        return boardRepository.findAll(pageable);
    }

    public Board findBoardByIdx(Long idx) {
        return boardRepository.findById(idx).orElse(new Board());
    }

    public void insertContent(RequestDTO.BoardRequestDTO requestDTO)
    {
        boardRepository.save(Board.builder()
                                  .title(requestDTO.getTitle())
                                  .writer(requestDTO.getWriter())
                                  .content(requestDTO.getContent())
                                  .createdDate(LocalDateTime.now())
                                  .updatedDate(LocalDateTime.now())
                                  .build());
    }

    public void deleteContent(Long idx) {
        boardRepository.deleteById(idx);
    }

    public void updateContent(RequestDTO.BoardRequestDTO requestDTO) {
        boardRepository.save(requestDTO.toEntity());
    }
}
