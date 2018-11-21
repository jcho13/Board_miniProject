package com.javalec.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.ui.Model;

import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

public interface IBoardService {
	public void deleteBoard(BoardVo vo);
	public List<BoardVo> getBoardList();
	public void updateBoard(BoardVo vo);
	public void searchBoard(String search_option, String keyword);
	public BoardVo selectBoard(BoardVo vo);
	public void insertBoard(BoardVo vo);
	public void updateBoardCount(BoardVo vo);
}
