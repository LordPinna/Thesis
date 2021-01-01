<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>newConference</title>
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
			<h1>Conference ${confname} created successfully</h1>
		</div>
		<div>
			<a
				href="<c:url value="/controller/conference.stop?confname=${confname}"/>">Stop
				conference</a>
			<div>
				<form class="form-inline"
					action="<c:url value="/controller/pin.set?confname=${confname}"/>"
					method="post">
					<div class="form-group">
						Insert PIN: <input type="text" name="pin">
						<button type="submit" class="btn btn-default">Set</button>
						Current PIN: ${pin}
					</div>
				</form>
			</div>
		</div>
		<div>
			Conference members
			<ul>
				<c:forEach var="phone" items="${phonesin}">
					<li>${phone.extension}
						<form method="post"
							action="<c:url value="/controller/user.kick?extension=${phone.extension}&confname=${confname}&pin=${pin}"/>">
							<button class="btn btn-primary" type="submit">Kick this
								user from conference</button>
						</form>
						<form method="post"
							action="<c:url value="/controller/user.mute?extension=${phone.extension}&confname=${confname}&pin=${pin}"/>">
							<button class="btn btn-primary" type="submit">Mute</button>
						</form>
						<form method="post"
							action="<c:url value="/controller/user.unmute?extension=${phone.extension}&confname=${confname}&pin=${pin}"/>">
							<button class="btn btn-primary" type="submit">Unmute</button>
						</form>
					</li>
				</c:forEach>
			</ul>
		</div>
		<div>
			Available members
			<ul>
				<c:forEach var="phone" items="${phonesout}">
					<li><form method="post"
							action="<c:url value="/controller/user.add?extension=${phone.extension}&confname=${confname}&pin=${pin}"/>">${phone.customer.surname} ${phone.customer.name} - ${phone.extension}
							<button class="btn btn-primary" type="submit">Add this
								user to conference</button>
						</form></li>
				</c:forEach>
			</ul>

		</div>
	</div>
</body>
</html>
