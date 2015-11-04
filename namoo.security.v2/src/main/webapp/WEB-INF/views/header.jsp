<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder"%>
<%@ page import="org.springframework.security.core.Authentication"%>
<%@ page import="namoo.security.exam.security.CustomUserDetails"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%
	request.setAttribute("loginUserName", CustomUserDetails.getRealUserName());
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<sec:authorize access="isAnonymous()">
	<a title="로그인해주세요." href="${ctx}/login">로그인해주세요.</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
    안녕하세요, ${loginUserName} 님.
    <input type="button" value="로그아웃" onclick="javascript:document.getElementById('logout').submit()" />
</sec:authorize>
<ul>
	<li><a title="상품목록" href="${ctx}/product/list">상품목록</a></li>
	<sec:authorize access="hasRole('ROLE_ADMIN')">
		<li><a title="상품관리" href="${ctx}/admin/product">상품관리</a></li>
	</sec:authorize>
</ul>

<form id="logout" action="${ctx}/logout" method="post" >
  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
</form>
