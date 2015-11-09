<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>

<%@ page import="namoo.security.v3.domain.authentication.entity.MenuBean"%>
<%@ page import="namoo.security.v3.domain.authentication.entity.PMSMenu"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello</title>
<%

    PMSMenu rootMenu = MenuBean.getMenus(application);

%>
</head>
<body>
	<ul>
		<%
		    for (PMSMenu menu : rootMenu.getSubResources()) {
		%>

					  <a href="${pageContext.request.contextPath}<%=menu.getAdjustedUri()%>"><%=menu.getName()%></a>

		<%
		    		
		    		for (PMSMenu subMenu : menu.getSubResources()) {
		%>
					
						<li>
							<a href="${pageContext.request.contextPath}<%=subMenu.getAdjustedUri()%>"><%=subMenu.getName()%></a>
						</li>
					
		<%
		    		}
		    }
		%>
	</ul>

</body>
</html>