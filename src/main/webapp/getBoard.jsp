<%@page import="com.javalec.board.dao.BoardDao"%>
<%@page import="com.javalec.board.vo.BoardVo"%>
<%@page import="com.javalec.board.dao.UsersDao"%>
<%@page import="com.javalec.board.vo.UsersVo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 상세</title>
</head>
<body>
<% 	BoardVo vo = (BoardVo)request.getAttribute("board");
	System.out.println(vo.toString());
%>
<center>
	<h1>게시글 상세</h1>
	<hr>
	<form action="" method="post">
	<a href="/Board_miniProject/dologout.do" >로그아웃</a>
		<table border="1" cellpadding="0" cellspacing="0" width=700px>
			<tr>
				<td  width=20%>제목</td>
				<td >&nbsp;&nbsp;<input name="" type="text"
					 disabled="disabled" value=<%=vo.getTitle() %> /> </td>
			</tr>
			<tr>
				<td >작성자</td>
				<td >&nbsp;&nbsp;<input name="" type="text"
					disabled="disabled" value=<%=vo.getWriter() %> /></td>
			</tr>
			<tr>
				<td >내용</td>
				<td >&nbsp;&nbsp;<textarea name="" cols="70" rows="10" disabled="disabled"><%=vo.getContent() %></textarea>
				</td>
			</tr>
			<tr>
				<td >등록일</td>
				<td ><%=vo.getRegdate() %></td>
			</tr>
			<tr>
				<td >조회수</td>
				<td ><%=vo.getCount() %></td>
			</tr>

		</table>
	</form>
	<hr>
</center>
<div align="center"	width:100%	height:50px  margin:20px auto;>
<%
	if (((String) session.getAttribute("id")).equals(vo.getId())) {
%>
	<a href="/Board_miniProject/updateform.do?no=<%=vo.getNo() %>" >변경</a>&nbsp;&nbsp;&nbsp; 
	<a href="/Board_miniProject/delete.do?no=<%=vo.getNo() %>" >삭제</a>&nbsp;&nbsp;&nbsp;
<%} %>
	<a href="/Board_miniProject/getboardlist.do" >목록</a>
</div>
</body>
</html>
