package com.javalec.board.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.javalec.board.dao.BoardDao;
import com.javalec.board.dao.UsersDao;
import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

//요청 처리 (분기)
@Service("boardService")
public class BoardService implements IBoardService {

	// GuestBookDao가 Repository로 되어있어서 오토와이어 되는거?
	@Autowired
	private BoardDao dao;

	public BoardService() {
		System.out.println("GuestBookService 호출");
	}

	@Override
	public void insertBoard(BoardVo vo) {
		dao.Insert(vo);
	}

	@Override
	public void deleteBoard(BoardVo vo) {
		dao.delete(vo);
	}

	@Override
	public List<BoardVo> getBoardList() {
		return dao.getBoardList();
	}

	@Override
	public BoardVo selectBoard(BoardVo vo) {
		return dao.getBoard(vo);
	}

	@Override
	public void updateBoard(BoardVo vo) {
		dao.Update(vo);
	}

	@Override
	public void updateBoardCount(BoardVo vo) {
		dao.UpdateCount(vo);
	}

	@Override
	public void searchBoard(String search_option, String keyword) {
		dao.getKeywordList(search_option, keyword);
	}

}
