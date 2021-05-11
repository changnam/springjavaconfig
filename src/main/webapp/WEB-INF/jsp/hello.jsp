<%@ page contentType="text/html; charset = UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<title>Hello World</title>
<%@ page isELIgnored="false"%>
</head>

<body>
	<div class="container">
		<h1>This is secured!</h1>
		<h2>${message}</h2>
		<p>
			Hello <b><c:out value="${pageContext.request.remoteUser}" /></b>
		</p>
		<c:url var="logoutUrl" value="/logout" />
		<form class="form-inline" action="${logoutUrl}" method="post">
			<input type="submit" value="Log out" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />
		</form>

	</div>
</body>

</html>