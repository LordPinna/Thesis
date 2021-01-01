<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="it.blueface.model.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>newCustomer</title>

</head>
<body>
	<div>
		<form action="<c:url value="/controller/phone.add"/>" method="get">
			Username<input type="text" name="username" value="${param.username}">
			Extension: <input type="text" name="extension"
				value="${param.extension}"> IP: <input type="text" name="ip"
				value="${param.ip}"> Port: <input type="text" name="port"
				value="${param.port}">
			<button type="submit">Insert</button>
		</form>
	</div>
</body>
</html>
