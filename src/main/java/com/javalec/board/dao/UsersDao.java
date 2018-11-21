package com.javalec.board.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Repository;

import com.javalec.board.vo.BoardVo;
import com.javalec.board.vo.UsersVo;

// DB°ü·Ã
@Repository
public class UsersDao {

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

	public UsersVo loginCheck(UsersVo vo) {
		System.out.println("***Dao login Check (select)");
		UsersVo resultVO = new UsersVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT id, password FROM users WHERE id=? and password=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				resultVO.setId(rs.getString(1));
				resultVO.setPassword(rs.getString(2));
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

	public void delete(BoardVo vo, UsersVo u_vo) {
		System.out.println("***users Dao delete");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();
			String sql = "DELETE FROM board WHERE NO = ? AND PASSWORD = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, vo.getNo());
			pstmt.setString(2, u_vo.getPassword());

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

	public void insert(UsersVo vo) {
		System.out.println("***users Dao insert");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "INSERT INTO users " + "VALUES (?, ?, ?, ?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getRole());

			pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("USERS DAO INSERT error : " + e);
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

	public int Update(int num, String get_content) {
		System.out.println("***users Dao update");
		Connection conn = null;
		PreparedStatement pstmt = null;

		try {
			conn = getConnection();

			String sql = "UPDATE board set content = ?" + " where no=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, get_content);
			pstmt.setInt(2, num);
			System.out.println("DAO get content: " + get_content);

			pstmt.executeUpdate();
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

		return 0;
	}

	public int count(BoardVo vo) {
		System.out.println("***users Dao count");
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

	public UsersVo select(UsersVo vo) {
		System.out.println("***Dao getBoard (select)");
		UsersVo resultVO = new UsersVo();
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {
			conn = getConnection();
			String sql = "SELECT id, password, name, role FROM users WHERE id=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			rs = pstmt.executeQuery();

			if (rs.next()) {
				String id = rs.getString(1);
				String password = rs.getString(2);
				String name = rs.getString(3);
				String role = rs.getString(4);

				resultVO.setId(id);
				resultVO.setPassword(password);
				resultVO.setName(name);
				resultVO.setRole(role);
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
}