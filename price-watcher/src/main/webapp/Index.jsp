<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Home Page" />
</jsp:include>
<body>
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<div class="jumbotron" id="login-selector">
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<h1 class="display-3 text-center">Log In</h1>
				</div>
				<div class="col-sm">
					<form id="loginForm" onsubmit="return checkParams(loginForm);"
						action="/price-watcher/userLogIn" method="POST">
						<input id="my_tooltip_open" name="username" class="form-control input-spacing"
							placeholder="Username" type="text"> <input name="password"
							class="form-control input-spacing" placeholder="Password" type="password">
						<button class="btn btn-primary btn-lg btn-block">Log In</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="jumbotron" id="register-selector">
		<div class="container">
			<div class="row">
				<div class="col-sm">
					<h1 class="display-3 text-center">Register</h1>
				</div>
				<div class="col-sm">
					<form id="registerForm" onsubmit="return checkParams(loginForm);"
						action="/price-watcher/userLogIn" method="POST">
						<input id="my_tooltip_open" name="username" class="form-control input-spacing"
							placeholder="Username" type="text"> <input name="password"
							class="form-control input-spacing" placeholder="Password" type="password">
						<button class="btn btn-primary btn-lg btn-block">Register</button>
					</form>
				</div>
			</div>
		</div>
	</div>

	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-sm-8 offset-sm-2">
					<h1 class="display-3 text-center">Price Watcher</h1>
					<p class="text-center lead">Get alerted when your favorite items drop in price. Start
						tracking items below, it's easy!</p>
					<form action="/price-watcher/sendUrls" method="POST">
						<ol class="centerText">
							<li class="centerText">Enter the URL to your items below</li>
							<li class="centerText">Click submit! That's it!</li>
						</ol>
						<input class="form-control input-spacing" type="text" name="paramUrl"> <input
							type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
					</form>
				</div>
			</div>
		</div>
	</div>

	<div id="error_tooltip" class="well">
		<div class="alert alert-dismissible alert-warning">
			<button class="close error_tooltip_close">&times;</button>
			<strong id="login_error_text"></strong>
		</div>
	</div>

	<script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>
	<script>
		$(document).ready(function() {
			$('#error_tooltip').popup({
				type : 'tooltip',
				transition : '0.3s all 0.1s',
				offsettop : 75,
				tooltipanchor : $('#my_tooltip_open')
			});
		});
	</script>

</body>
</html>