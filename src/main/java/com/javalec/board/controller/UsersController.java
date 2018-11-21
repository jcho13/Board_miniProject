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

// 고객 요청
@Controller
public class UsersController {

	@Autowired
	private IUsersService usersService;

	@RequestMapping("/loginform.do")
	// 로그인 폼으로 이동하기
	public String loginController(Model model, HttpSession session) {
		System.out.println("로그인 컨트롤러 입니다");
		return "loginform.jsp";
	}

	@RequestMapping("/dologin.do")
	// 로그인하기
	public String loginController(UsersVo vo, Model model, HttpSession session) throws Exception {
		UsersVo result = usersService.loginCorrect(vo);

		if (result.getId() != null) {
			System.out.println(result.toString());
			session.setAttribute("id", result.getId());
			session.setAttribute("password", result.getPassword());
			return "getboardlist.do";
		} else {
			model.addAttribute("msg", "아이디/비밀번호 확인 요청!!");
			return "loginform.jsp";
		}
	}

	@RequestMapping("/dologout.do")
	// 로그아웃 하기
	public String logoutController(HttpSession session) {
		session.invalidate();
		return "loginform.jsp";
	}

	@RequestMapping("/findpassword.do")
	// 비밀번호 찾는 폼으로 이동하기
	public String FindPasswordController(Model model, HttpSession session) {
		System.out.println("findpassword 컨트롤러 입니다");
		return "findpassword.jsp";
	}

	@RequestMapping("/searchpassword.do")
	// 비밀번호 찾기 처리하는 부분
	public String searchPasswordController(Model model, UsersVo vo) {
		System.out.println("search Password 컨트롤러 입니다");
		UsersVo passwordVo = usersService.SelectService(vo);
		model.addAttribute("password", passwordVo.getPassword());
		return "findpassword.jsp";
	}

	@RequestMapping("/joinform.do")
	// 회원가입 폼으로 이동하기
	public String joinFormController(Model model, HttpSession session) {
		System.out.println("joinform 컨트롤러 입니다");
		return "joinform.jsp";
	}

	@RequestMapping("/write.do")
	// 회원가입하기
	public String writeController(UsersVo vo,
			@RequestParam(value = "role", defaultValue = "", required = false) String role) {
		System.out.println("write 컨트롤러 입니다");

		System.out.println("writeController vo : " + vo.toString());

		usersService.InsertService(vo);

		return "getboardlist.do";
	}
}
