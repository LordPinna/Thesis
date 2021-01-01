<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>newCustomer</title>

</head>
<body>
	<div>
		<form action="<c:url value="/controller/customer.create"/>"
			method="get">
			<input type="text" name="name" value="${param.name}"> <input
				type="text" name="surname" value="${param.surname}"> <input
				type="text" name="username" value="${param.username}"> <input
				type="password" name="password" value="${param.password}"> <input
				type="email" name="email" value="${param.email}">
			<button type="submit">Insert</button>
		</form>
	</div>
</body>
</html>
