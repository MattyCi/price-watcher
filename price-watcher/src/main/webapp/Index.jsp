<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Home Page" />
</jsp:include>
<body class="bg-pw-light">
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<div class="container">
	<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>
		<div class="row">
			<div class="col-sm-8 offset-sm-2 py-3">
				<h1 class="display-3 text-center">Price Watcher</h1>
				<img class="vertical-align rounded mx-auto d-block py-2" src="/price-watcher/resources/images/price_bot.png" alt="Price Watcher Logo">
				<p class="text-center lead">Get alerted when your favorite items drop in price. Start
					tracking items below, it's easy!</p>
				<form action="/price-watcher/trackItem" method="POST">
					<ol class="centerText">
						<li class="centerText">Enter the URL to your items below</li>
						<li class="centerText">Click submit! That's it!</li>
					</ol>
					<input class="form-control input-spacing" placeholder="URL" type="text" name="paramUrl"> <input
						type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
				</form>
			</div>
		</div>
	</div>

	<div id="error_tooltip" class="well">
		<div class="alert alert-dismissible alert-warning">
			<button class="close error_tooltip_close">&times;</button>
			<strong id="error_text"></strong>
		</div>
	</div>
	<jsp:include page="/widgets/footer.jsp"/>
</body>
</html>