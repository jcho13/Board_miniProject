<%@page import="com.javalec.board.vo.UsersVo"%>
<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>새글등록</title>

</head>
<body>

<%
	String password = (String) request.getAttribute("password");
%>

<center>
	<h3>비밀번호 찾기</h3>
	<hr>
	<form action="searchpassword.do" method="post" >
		<table border="1" cellpadding="0" cellspacing="0" width="700">
			<tr>
				<td width=30%>아이디</td>
				<td ><input type="text" name="id" size="20" /></td>
			</tr>
			<tr>
				<td >비밀번호</td>
				<td ><h2><font color="blue"> <%=password %> </font></h2></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><input type="submit"
					value="비밀번호찾기" /></td>
			</tr>
		</table>
		
		<a href="loginform.do">로그인 하러가기</a>
	</form>
	<hr>
</center>

</body>
</html>