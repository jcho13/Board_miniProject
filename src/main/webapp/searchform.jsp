<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.javalec.board.dao.BoardDao"%>
<%@page import="com.javalec.board.vo.BoardVo"%>
<%@page import="com.javalec.board.dao.UsersDao"%>
<%@page import="com.javalec.board.vo.UsersVo"%>
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
		String search_option = request.getParameter("search_option");
			String search = request.getParameter("search");

			System.out.println("search form에 들어옴");
			System.out.println("search_option : " + search_option);
			System.out.println("search : " + search);

			List<BoardVo> list = (List<BoardVo>) request.getAttribute("list");
			Iterator iter = list.iterator();

			while (iter.hasNext()) {
				BoardVo g_vo = (BoardVo) iter.next();
	%>
	<table border='3px' cellspacing='0' cellpadding='5px' width=500>
		<tr>
			<td>${guestBookVo.no}</td>
			<td>${guestBookVo.name}</td>
			<td>${guestBookVo.reg_date}</td>
		</tr>
		<tr>
			<td colspan=4>${guestBookVo.content}</td>
		</tr>
	</table>
	<%
		}
	%>


</body>
</html>