<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>기본 사용자 홈 페이지</title>
</head>
<body>
	<table style="width:100%">
		<tr>
			<td colspan="2"><%@ include file="./common/header.jsp" %></td>
		</tr>
		<tr>
			<td style="width:30%"><%@ include file="./common/menu.jsp" %></td>
			<td valign="top" bgcolor="lightgray"><h1>기본사용자 홈페이지</h1></td>
		</tr>
	</table>
</body>
</html>