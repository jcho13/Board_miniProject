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

// �� ��û
@Controller
public class BoardController {

	@Autowired
	private IBoardService boardService;

	@RequestMapping("/getboardlist.do")
	public String getBoardList(Model model, HttpSession session) {
		System.out.println("����Ʈ ��ȸ ��Ʈ�ѷ� �Դϴ�");
		if (session.getAttribute("id") != null) {
			// Controller->Jsp
			model.addAttribute("list", boardService.getBoardList());
			return "getBoardList.jsp";
		}
		return "loginform.do";
	}

	@RequestMapping("/getboard.do")
	// �Խñ� �󼼷� �̵�
	public String getBoard(Model model, BoardVo vo) {
		BoardVo bo = boardService.selectBoard(vo);
		System.out.println("getboard ��Ʈ�ѷ� �Դϴ�!!!\n" + bo.toString());

		boardService.updateBoardCount(bo);
		System.out.println("[���� ��]"+bo.getCount());
		model.addAttribute("board", boardService.selectBoard(bo));
		return "getBoard.jsp";
	}

	@RequestMapping("/insertformboard.do")
	// �Խñ� ��� ��
	public String InsertFormBoardController(BoardVo vo) {
		return "insertBoard.jsp";
	}

	@RequestMapping("/insertboard.do")
	// �Խñ� ��� �۾�
	public String InsertBoardController(BoardVo vo) {
		System.out.println("1 : " + vo.toString());
		boardService.insertBoard(vo);
		System.out.println("2 : " + vo.toString());
		return "getboardlist.do";
	}

	@RequestMapping("/delete.do")
	// ���� ó��
	public String deleteGuestBookController(BoardVo vo) {
		System.out.println("deleteGuestBookController ��, ��ȣ: " + vo.getNo());
		boardService.deleteBoard(vo);
		return "/getboardlist.do";
	}

	@RequestMapping("/updateform.do")
	// ���� ��
	public String updateFormGuestBookController(BoardVo vo, Model model) {
		System.out.println("*****updateFormGuestBookController �� : " + vo.toString());
		model.addAttribute("board", boardService.selectBoard(vo));
		return "modifyBoard.jsp";
	}

	@RequestMapping("/update.do")
	// ���� �۾�
	public String updateBoardController(BoardVo vo) {
		boardService.updateBoard(vo);
		return "/getboardlist.do";
	}

	@RequestMapping("/searchkeywordlist.do")
	public String getBoardList(BoardDao dao, Model model,
			@RequestParam(value = "searchKeyword", defaultValue = "", required = false) String keyword) {

		System.out.println("�� Ű���� �˻� ��� �˻� ó�� Ű���� " + keyword);
		String search_option = "search_name";

		model.addAttribute("list", dao.getKeywordList(search_option, keyword));
		model.addAttribute("searchKeyword", keyword);

		return "getBoardList.jsp";
	}
}
