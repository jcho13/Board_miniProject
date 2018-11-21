<%@page import="com.javalec.board.vo.UsersVo"%>
<%@page import="com.javalec.board.vo.BoardVo"%>
<%@page import="java.util.*" %>
<%@page contentType="text/html; charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>글 목록</title>
</head>
<body>
	<center>
		<h1>게시판 목록</h1>
		<h3><%=session.getAttribute("id")%>님 환영합니다</h3>
		<a href="/Board_miniProject/dologout.do">이만 로그아웃 하기</a><br><br>
		
		<!-- 검색 시작 -->
		<form action="searchkeywordlist.do" method="post">
			<table border="1" cellpadding="0" cellspacing="0" width=700px>
				<tr>
					<td align="right"><select name="searchCondition">
							<option value="TITLE">제목
							<option value="CONTENT">내용
					</select> <input name="searchKeyword" type="text" /> <input type="submit"
						value="검색" /></td>
				</tr>
			</table>
		</form>

		<!-- 검색 종료 -->
		<table border="1" cellpadding="0" cellspacing="0" width=700px>
			<tr>
				<th>번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>등록일</th>
				<th>조회수</th>
			</tr>
<%
	List<BoardVo> list = (List<BoardVo>)request.getAttribute("list");
	Iterator iter = list.iterator();
	
	while(iter.hasNext()){
		BoardVo vo = (BoardVo)iter.next();
%>
			<tr>
				<td><%=vo.getNo()%></td>
	<td align="left"><a href="/Board_miniProject/getboard.do?no=<%=vo.getNo() %>"	><%=vo.getTitle()%></a></td>
				<td><%=vo.getWriter()%></td>
				<td><%=vo.getRegdate()%></td>
				<td><%=vo.getCount()%></td>
			</tr>
<%} %>
		</table>

		<br>
		<div align="center" width:100%	height:50px margin:20pxauto;>
			<a href="/Board_miniProject/insertformboard.do">게시글 등록</a>&nbsp;&nbsp;&nbsp;
		</div>
	</center>

</body>
</html>