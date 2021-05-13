<%@ page pageEncoding="UTF-8" contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Welcome</title>
<%@ page isELIgnored="false"%>
</head>
<body>
	안녕하세요? foo service list users	<h2>${message}</h2>	in /WEB-INF/jsp/
	<c:forEach items="${users}" var="user">
		<tr>
			<td>${user.id}</td>
			<td>${user.name}</td>
			<td>${user.email}</td>
		</tr>
	</c:forEach>

</body>
</html>