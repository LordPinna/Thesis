<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="it.blueface.model.Customer"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Home</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css"
	integrity="sha384-rwoIResjU2yc3z8GV/NPeZWAv56rSmLldC3R/AZzGRnGxQQKnKkoFVhFQhNUwEyJ"
	crossorigin="anonymous">
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"
	integrity="sha384-A7FZj7v+d/sdmMqp/nOQwliLvUsJfDHW+k9Omg/a/EheAdgtzNs3hpfag6Ed950n"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"
	integrity="sha384-DztdAPBWPRXSA/3eYEEUWrWCy7G5KFbe8fFjk5JAIxUYHKkDx6Qin1DkWx51bBrb"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"
	integrity="sha384-vBWWzlZJ8ea9aCX4pEW3rVHjgjt7zpkNpZk+02D9phzyeVkE+jo0ieGizqPLForn"
	crossorigin="anonymous"></script>
</head>

<body>
	<div class="container">
		<div class="page-header">
			<h1>This is your home, ${customer.name} ${customer.surname}</h1>
		</div>
		<div>Your credit: ${customer.credit}</div>
		<ul>
			<li><button class="btn btn-primary" type="button"
					data-toggle="collapse" data-target="#collapseList"
					aria-expanded="false" aria-controls="collapseList">
					List your phones <span class="glyphicon glyphicon-list"
						aria-hidden="true"></span>
				</button>
				<div class="collapse" id="collapseList">
					<div class="well">
						<table class="table table-hover">
							<tr>
								<td>Extension</td>
								<td>Conference name ${noCredit}</td>
							</tr>
							<c:forEach var="phone" items="${phones}">
								<tr>
									<td><button type="button" class="btn btn-primary">${phone.name}
											${phone.extension}</button></td>
									<td><form class="form-inline"
											action="<c:url value="/controller/conference.create"/>"
											method="post">
											<div class="form-group">
												<input type="hidden" name="extension"
													value="${phone.extension}"> <input type="text"
													class="form-control" name="confname"
													placeholder="Conference name">
											</div>
											<button type="submit" class="btn btn-default">Create
												conference</button>
										</form></td>
								</tr>
							</c:forEach>
						</table>
					</div>
				</div></li>
			<li><button class="btn btn-primary" type="button"
					data-toggle="collapse" data-target="#collapseAdd"
					aria-expanded="false" aria-controls="collapseAdd">
					Add a new phone <span class="glyphicon glyphicon-plus"
						aria-hidden="true"></span>
				</button>
				<div class="collapse" id="collapseAdd">
					<div class="well">
						<form class="form-inline"
							action="<c:url value="/controller/phone.add"/>" method="post">
							<div class="form-group">
								<label for="name">Name: </label> <input type="text"
									class="form-control" name="name" placeholder="Name">
							</div>
							<div class="form-group">
								<label for="extension">Extension: </label> <input type="text"
									class="form-control" name="extension" placeholder="Extension">
							</div>
							<div class="form-group">
								<label for="ip">IP: </label> <input type="text"
									class="form-control" name="ip" placeholder="IP">
							</div>
							<div class="form-group">
								<label for="port">Port: </label> <input type="text"
									class="form-control" name="port" placeholder="Port">
							</div>
							<button type="submit" class="btn btn-default">Confirm</button>
						</form>
					</div>
				</div></li>
		</ul>
	</div>

</body>
</html>
