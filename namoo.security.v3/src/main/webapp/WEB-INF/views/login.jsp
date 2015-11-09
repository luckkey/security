<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
</head>
<body>
	<!-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★로그인 S★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★-->
	<fieldset>
		<legend>로그인</legend>
		<form id="loginForm" action="${pageContext.request.contextPath}/j_spring_security_check" method="post">
			<p>
				<span class="ipt userid">
					<input type="text" id="userId" name='j_username' autofocus placeholder="아이디를 입력해주세요." onkeydown="f_engNum(this)" />
				</span> 
				<span class="ipt userpwd">
					<input type="password" id="userPassword" name="j_password" />
				</span>
				<button type="submit">로그인</button>
			</p>
			<p class="mgt5">
				<label><input type="checkbox" id="rememberMe"
					name="_spring_security_remember_me" />로그인 상태유지</label> <a
					href="javascript:;" id="btnFindJoin" class="mgl95">사용자등록신청조회</a>
			</p>
		</form>
	</fieldset>
	<!-- ★★★★★★★★★★★★★★★★★★★★★★★★★★★★로그인 E★★★★★★★★★★★★★★★★★★★★★★★★★★★★★★-->
</body>
</html>