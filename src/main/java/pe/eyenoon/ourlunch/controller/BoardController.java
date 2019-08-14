package pe.eyenoon.ourlunch.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pe.eyenoon.ourlunch.entity.Board;
import pe.eyenoon.ourlunch.model.RequestDTO;
import pe.eyenoon.ourlunch.service.BoardService;

@Controller
@RequestMapping({"", "/"})
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping({""})
    public String list(@PageableDefault Pageable pageable, Model model) {
        Page<Board> boardList = boardService.findBoardList(pageable);
        boardList.stream().forEach(e -> e.getContent());
        model.addAttribute("boardList", boardList);

        return "/board/list";
    }

    @GetMapping("/detail")
    public String detail(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));

        return "/board/detailForm";
    }

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("board", new Board());

        return "/board/writeForm";
    }

    @PostMapping("/insert")
    public String insert(@ModelAttribute RequestDTO.BoardRequestDTO board) {
        boardService.insertContent(board);

        return "redirect:/";
    }

    @GetMapping("/modify")
    public String modifyForm(@RequestParam(value = "idx", defaultValue = "0") Long idx, Model model) {
        model.addAttribute("board", boardService.findBoardByIdx(idx));

        return "board/modifyForm";
    }

    @PostMapping("modify")
    public String modify(@ModelAttribute RequestDTO.BoardRequestDTO board) {
        boardService.updateContent(board);

        return "redirect:/";
    }

    @GetMapping("delete")
    public String delete(@RequestParam(value = "idx", defaultValue = "0") Long idx) {
        boardService.deleteContent(idx);

        return "redirect:/";
    }
}
