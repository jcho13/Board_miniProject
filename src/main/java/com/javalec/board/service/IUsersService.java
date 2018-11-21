package com.javalec.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.javalec.board.vo.UsersVo;

public interface IUsersService {
	public void InsertService(UsersVo vo);
	public UsersVo SelectService(UsersVo vo);
	public UsersVo loginCorrect(UsersVo vo);
}
