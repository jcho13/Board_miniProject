package com.javalec.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.board.dao.BoardDao;
import com.javalec.board.service.IBoardService;
import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

// 고객 요청
@Controller
public class BoardController {

	@Autowired
	private IBoardService boardService;

	@RequestMapping("/getboardlist.do")
	public String getBoardList(Model model, HttpSession session) {
		System.out.println("리스트 조회 컨트롤러 입니다");
		if (session.getAttribute("id") != null) {
			// Controller->Jsp
			model.addAttribute("list", boardService.getBoardList());
			return "getBoardList.jsp";
		}
		return "loginform.do";
	}

	@RequestMapping("/getboard.do")
	// 게시글 상세로 이동
	public String getBoard(Model model, BoardVo vo) {
		BoardVo bo = boardService.selectBoard(vo);
		System.out.println("getboard 컨트롤러 입니다!!!\n" + bo.toString());

		boardService.updateBoardCount(bo);
		System.out.println("[보드 두]"+bo.getCount());
		model.addAttribute("board", boardService.selectBoard(bo));
		return "getBoard.jsp";
	}

	@RequestMapping("/insertformboard.do")
	// 게시글 등록 폼
	public String InsertFormBoardController(BoardVo vo) {
		return "insertBoard.jsp";
	}

	@RequestMapping("/insertboard.do")
	// 게시글 등록 작업
	public String InsertBoardController(BoardVo vo) {
		System.out.println("1 : " + vo.toString());
		boardService.insertBoard(vo);
		System.out.println("2 : " + vo.toString());
		return "getboardlist.do";
	}

	@RequestMapping("/delete.do")
	// 삭제 처리
	public String deleteGuestBookController(BoardVo vo) {
		System.out.println("deleteGuestBookController 안, 번호: " + vo.getNo());
		boardService.deleteBoard(vo);
		return "/getboardlist.do";
	}

	@RequestMapping("/updateform.do")
	// 수정 폼
	public String updateFormGuestBookController(BoardVo vo, Model model) {
		System.out.println("*****updateFormGuestBookController 안 : " + vo.toString());
		model.addAttribute("board", boardService.selectBoard(vo));
		return "modifyBoard.jsp";
	}

	@RequestMapping("/update.do")
	// 수정 작업
	public String updateBoardController(BoardVo vo) {
		boardService.updateBoard(vo);
		return "/getboardlist.do";
	}

	@RequestMapping("/searchkeywordlist.do")
	public String getBoardList(BoardDao dao, Model model,
			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword) {

		System.out.println("글 키워드 검색 목록 검색 처리 키워드 " + keyword);
		String search_option = "search_name";

		model.addAttribute("list", dao.getKeywordList(search_option, keyword));
		model.addAttribute("searchKeyword", keyword);

		return "getBoardList.jsp";
	}
}
