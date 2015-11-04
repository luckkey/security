<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" scope="request" />
<html>
    <head><title>로그인페이지</title></head>
    <body>
        <%@ include file="./header.jsp" %>
        <h2>로그인</h2>
        <form name="form" method="post" action="${ctx}/do/login">
        	<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        	<!-- spring security 4버전부터 csrf 보호 설정이 기본으로 되어 있어서 사용하지 않으려면 security-context.xml에서 설정을 바꿔줘야 한다.  -->
        	<!-- PATCH, POST, PUT, DELETE 메소드에 대해서 위와 같이 hidden값을 추가로 넣어줘야지 제대로 동작한다. -->
            <table>
                <tr height="40px">
                	<td>사용자아이디</td>
                	<td><input type="text" name="user_id"></td>
                </tr>
                <tr height="40px">
                	<td>패스워드</td>
                	<td>
                	<input type="password" name="user_password">
                	<input type="checkbox" name="myremembermeparam" value="true">
                	</td>
                </tr>
            </table>
            <table>
                <tr><td align="center"><input type="submit" value="로그인"></td>
                    <td align="center"><input type="reset" value="리셋"></td>
                </tr>
                <tr>
                    <td colspan="2">
                    	${securityExceptionMessage}<br/>
                        <c:if test="${not empty param.fail}">
                            <font color="red">로그인이 실패했습니다.<br/></font>
                        </c:if>
                    </td>
                </tr>
            </table>
        </form>
    </body>
</html>

