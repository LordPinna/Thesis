<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="it.blueface.model.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>customerHome</title>
<link
	href="http://localhost:8080/BluefaceProject/bootstrap/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body>
	<div>Your phones</div>
	${noCredit}
	<div class="container">
		<table class="table table-hover">
			<tr>
				<td>Extension</td>
				<td>Conference name</td>
			</tr>
			<c:forEach var="phone" items="${phones}">
				<tr>
					<td>${phone.extension}</td>
					<td>
						<form action="<c:url value="/controller/conference.create"/>"
							method="get">
							<input type="hidden" name="extension" value="${phone.extension}">
							<input type="text" name="confname">
							<button type="submit" name="call">Create conference</button>
						</form>
					</td>
				</tr>
			</c:forEach>
		</table>
	</div>
</body>
</html>
