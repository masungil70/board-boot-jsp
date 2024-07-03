package kr.or.kosa.board.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.kosa.board.service.BoardService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;

	@GetMapping("/board/list.do")
	public String list(Model model) throws Exception {

		try {
			long tick = System.nanoTime();
			model.addAttribute("list", boardService.listBoard());
			tick = System.nanoTime() - tick;
			log.info("실행 시간 {}", tick);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/list";
	}

	@GetMapping("/board/writeForm.do")
	public String writeForm(Model model) {
		return "/board/writeForm";
	}

	@GetMapping("/board/view.do")
	public String view(Model model, @RequestParam() Long id) throws Exception {

		System.out.println("id->" + id);
		try {
			if (id != null) {
				model.addAttribute("boardResponse", boardService.detailBoardResponse(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/view";
	}

	@PostMapping("/board/updateForm.do")
	public String updateForm(Model model, @RequestParam() Long id) throws Exception {

		System.out.println("id->" + id);
		try {
			if (id != null) {
				model.addAttribute("boardResponse", boardService.updateDataBoardResponse(id));
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "/board/updateForm";
	}

	@PostMapping("/board/update.do")
	public String update(Model model, BoardRequest boardRequest) throws Exception {

		try {
			System.out.println(boardRequest);
			boardService.updateBoard(boardRequest);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/board/list.do";
	}

	@PostMapping("/board/write.do")
	public String write(Model model, BoardRequest boardRequest) throws Exception {

		try {
			boardService.save(boardRequest);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/board/list.do";
	}

	@PostMapping("/board/delete.do")
	public String delete(Model model, @RequestParam() Long id) throws Exception {

		try {
			if (id != null) {
				boardService.deleteById(id);
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/board/list.do";
	}

	@PostMapping("/board/deletes.do")
	public String boardDeleteAction(Model model, @RequestParam() List<Long> ids) throws Exception {

		try {
			boardService.deleteAll(ids);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

		return "redirect:/board/list.do";
	}
}
