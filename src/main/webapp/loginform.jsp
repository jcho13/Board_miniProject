<%@page import="java.sql.*"%>
<%@page import="com.javalec.board.dao.BoardDao"%>
<%@page import="com.javalec.board.vo.BoardVo"%>
<%@page import="com.javalec.board.dao.UsersDao"%>
<%@page import="com.javalec.board.vo.UsersVo"%>
<%@page import="java.io.*"%>
<%@page import="javax.servlet.*"%>
<%@page import="java.util.*"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert</title>
</head>
<body>
<%
System.out.println("***loginform") ;
%>	</form>
	<h2><font color='orange'> Welcome! </font><br></h2>
	<h4><font color='skyblue'> Please login </font><br></h4>
	<form action="dologin.do" method="post">
	<h3><font color="pink">
		아이디 : <input type="text" name="id"> <br>
		비밀번호 : <input type="password" name="password"> <br>
		<input type="submit"	value="로그인">
		
		<td><a href="/Board_miniProject/joinform.do">회원가입</a>
			<a href="/Board_miniProject/findpassword.do">비밀번호 찾기</a>
		</td>
		</form>
	</font></h3>
		<h3><font color="red">${msg}</font></h3>
</body>
</html>