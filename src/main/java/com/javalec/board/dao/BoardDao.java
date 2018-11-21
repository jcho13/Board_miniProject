package com.javalec.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

// DB°ü·Ã
@Repository
public class BoardDao {

	private Connection getConnection() throws SQLException {
		Connection conn = null;
		try {
			// 1. JDBC
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2. Connection
			String url = "jdbc:oracle:thin:@localhost:1521:xe";
			conn = DriverManager.getConnection(url, "scott", "tiger");

			System.out.println("DB Connection Success");
		} catch (Exception e) {
			System.out.println("DB Connection Error " + e);
		}
		return conn;
	}

	public void delete(BoardVo vo) {
		System.out.println("***************Dao delete");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "DELETE FROM board WHERE NO = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("DELETE SQLException error : " + e);
		} finally {
			// close
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("delete close error");
				e.printStackTrace();
			}
		}
	}

	public void Insert(BoardVo vo) {
		System.out.println("***************Board Dao insert");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "INSERT INTO board " + "VALUES (board_seq.NEXTVAL, ?, ?, ?, SYSDATE, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getCount());
			pstmt.setString(5, vo.getId());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Board DAO INSERT error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void Update(BoardVo vo) {
		System.out.println("***************Dao update");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "UPDATE board set title=?, writer=?, content = ? where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getWriter());
			pstmt.setString(3, vo.getContent());
			pstmt.setInt(4, vo.getNo());
			System.out.println(vo.toString());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Update error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void UpdateCount(BoardVo vo) {
		System.out.println("***************Dao update Count");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "UPDATE board set count = count+1 where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			System.out.println(vo.toString());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("UpdateCount error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public int count(BoardVo vo) {
		System.out.println("***************Dao count");
		int count = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "select count(*) from board;";

			pstmt = conn.prepareStatement(sql);

			count = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("INSERT error : " + e);
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return count;
	}

	public BoardVo getBoard(BoardVo vo) {
		System.out.println("***************Dao getBoard (select)");
		BoardVo resultVO = new BoardVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT no, id, title, writer, content, to_char(regdate, 'yyyy/mm/dd'), count"
					+ " FROM board WHERE no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				int no = rs.getInt(1);
				String id = rs.getString(2);
				String title = rs.getString(3);
				String writer = rs.getString(4);
				String content = rs.getString(5);
				String regdate = rs.getString(6);
				int count = rs.getInt(7);

				resultVO.setNo(no);
				resultVO.setId(id);
				resultVO.setTitle(title);
				resultVO.setWriter(writer);
				resultVO.setContent(content);
				resultVO.setRegdate(regdate);
				resultVO.setCount(count);
			}
		} catch (SQLException e) {
			System.out.println("GetBook error : " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("error: " + e);
			}
		}
		return resultVO;
	}

	public List<BoardVo> getBoardList() {
		System.out.println("***************Dao getBoardList (List)");
		List<BoardVo> list = new ArrayList<BoardVo>();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT no, title, writer, regdate, count" + " FROM board order by regdate desc";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				BoardVo vo = new BoardVo();

				int no = rs.getInt(1);
				String title = rs.getString(2);
				String writer = rs.getString(3);
				String regdate = rs.getString(4);
				int count = rs.getInt(5);

				vo.setNo(no);
				vo.setTitle(title);
				vo.setWriter(writer);
				vo.setRegdate(regdate);
				vo.setCount(count);

				list.add(vo);
			}
		} catch (SQLException e) {
			System.out.println("LIST board SQLException error: " + e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				System.out.println("Close error: " + e);
			}
		}
		return list;
	}

	public List<BoardVo> getKeywordList(String search_option, String keyword) {
		System.out.println("***************dao getKeywordList");
//		List<BoardVo> list = new ArrayList<BoardVo>();
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		String sql = null;
//
//		try {
//			conn = getConnection();
//
//			if (search_option.equals("search_name")) {
//				System.out.println("***search_name");
//				sql = "SELECT no, name, content, password, to_char(reg_date, 'yyyy/mm/dd') "
//						+ "from board where name like ? order by reg_date desc";
//			} else {
//				System.out.println("***search_content");
//				sql = "SELECT no, name, content, password, to_char(reg_date, 'yyyy/mm/dd') "
//						+ "from board where content like ? order by reg_date desc";
//			}
//
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setString(1, "%" + keyword + "%");
//			rs = pstmt.executeQuery();
//			
//			while (rs.next()) {
//				BoardVo vo = new BoardVo();
//
//				int no = rs.getInt(1);
//				String name = rs.getString(2);
//				String content = rs.getString(3);
//				String password = rs.getString(4);
//				String reg_date = rs.getString(5);
//
//				vo.setNo(no);
//				vo.setName(name);
//				vo.setContent(content);
//				vo.setPassword(password);
//				vo.setReg_date(reg_date);
//
//				list.add(vo);
//			}
//		} catch (SQLException e) {
//			System.out.println("LIST BOOK SQLException error: " + e);
//		} finally {
//			try {
//				if (rs != null)
//					rs.close();
//				if (pstmt != null)
//					pstmt.close();
//				if (conn != null)
//					conn.close();
//			} catch (SQLException e) {
//				System.out.println("Close error: " + e);
//			}
//		}
//		return list;
		return null;
	}

}