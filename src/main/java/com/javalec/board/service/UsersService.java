package com.javalec.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javalec.board.dao.UsersDao;
import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

//요청 처리 (분기)
@Service("usersService")
public class UsersService implements IUsersService {

	// GuestBookDao가 Repository로 되어있어서 오토와이어 되는거?
	@Autowired
	private UsersDao dao;

	@Override
	public void InsertService(UsersVo vo) {
		dao.insert(vo);
	}

	@Override
	public UsersVo SelectService(UsersVo vo) {
		return dao.select(vo);
	}

	@Override
	public UsersVo loginCorrect(UsersVo vo) {
		return dao.loginCheck(vo);
		
	}

	
	

}
