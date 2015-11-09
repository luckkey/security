<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
	안녕하세요. ${pageContext.request.userPrincipal.name} 님.
	<a href="${pageContext.request.contextPath}/j_security_logout" >로그아웃</a>
</body>
</html>