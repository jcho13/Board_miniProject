<%@page import="java.sql.*"%>
<%@page import="com.javalec.board.dao.BoardDao"%>
<%@page import="com.javalec.board.vo.BoardVo"%>
<%@page import="com.javalec.board.dao.UsersDao"%>
<%@page import="com.javalec.board.vo.UsersVo"%>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
	System.out.println("updateform에 들어옴");
	
	String msg_error = (String)request.getAttribute("msg_error") ;
		System.out.println("msg_error :" + msg_error) ;
		if(msg_error!= null){
	%>
	<h2><font color='orange'>
	<%=msg_error%> </font></h2>
	<%
	}%>
<font color='skyblue'><h3>
수정할 방명록 번호: ${guestBookVo.no}</font></h3>
	<form action="update.do" method="post">
		비밀번호 입력 : <input type="password" name="password">
 		<input type='hidden' name="no" value="${guestBookVo.no}"> 
 		<textarea type='hidden' rows="10" cols="60" name="content" value="${guestBookVo.content}"></textarea>
		<% %>
		<input type="submit" value="수정">
	</form>
	<h2> <font color='skyblue'><a href="/spring_guestbook_v3_twolayers">메인으로 돌아가기</a></font></h2>
</body>
</html>