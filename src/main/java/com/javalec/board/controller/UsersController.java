package com.javalec.board.controller;

import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.javalec.board.service.IUsersService;
import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

// �� ��û
@Controller
public class UsersController {

	@Autowired
	private IUsersService usersService;

	@RequestMapping("/loginform.do")
	// �α��� ������ �̵��ϱ�
	public String loginController(Model model, HttpSession session) {
		System.out.println("�α��� ��Ʈ�ѷ� �Դϴ�");
		return "loginform.jsp";
	}

	@RequestMapping("/dologin.do")
	// �α����ϱ�
	public String loginController(UsersVo vo, Model model, HttpSession session) throws Exception {
		UsersVo result = usersService.loginCorrect(vo);

		if (result.getId() != null) {
			System.out.println(result.toString());
			session.setAttribute("id", result.getId());
			session.setAttribute("password", result.getPassword());
			return "getboardlist.do";
		} else {
			model.addAttribute("msg", "���̵�/��й�ȣ Ȯ�� ��û!!");
			return "loginform.jsp";
		}
	}

	@RequestMapping("/dologout.do")
	// �α׾ƿ� �ϱ�
	public String logoutController(HttpSession session) {
		session.invalidate();
		return "loginform.jsp";
	}

	@RequestMapping("/findpassword.do")
	// ��й�ȣ ã�� ������ �̵��ϱ�
	public String FindPasswordController(Model model, HttpSession session) {
		System.out.println("findpassword ��Ʈ�ѷ� �Դϴ�");
		return "findpassword.jsp";
	}

	@RequestMapping("/searchpassword.do")
	// ��й�ȣ ã�� ó���ϴ� �κ�
	public String searchPasswordController(Model model, UsersVo vo) {
		System.out.println("search Password ��Ʈ�ѷ� �Դϴ�");
		UsersVo passwordVo = usersService.SelectService(vo);
		model.addAttribute("password", passwordVo.getPassword());
		return "findpassword.jsp";
	}

	@RequestMapping("/joinform.do")
	// ȸ������ ������ �̵��ϱ�
	public String joinFormController(Model model, HttpSession session) {
		System.out.println("joinform ��Ʈ�ѷ� �Դϴ�");
		return "joinform.jsp";
	}

	@RequestMapping("/write.do")
	// ȸ�������ϱ�
	public String writeController(UsersVo vo,
			@RequestParam(value = "role", defaultValue = "", required = false) String role) {
		System.out.println("write ��Ʈ�ѷ� �Դϴ�");

		System.out.println("writeController vo : " + vo.toString());

		usersService.InsertService(vo);

		return "getboardlist.do";
	}
}
