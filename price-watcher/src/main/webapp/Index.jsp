<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Home Page" />
</jsp:include>
<body class="bg-lg-only">
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<div class="container bg-pw-light">
	<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>
		<div class="row justify-content-center py-3">
			<div class="col-sm-12 col-md-10 col-lg-8">
				<p class="display-3 text-center">Price Watcher</p>
				<p class="text-center lead">Get alerted when your favorite items drop in price. Start
					tracking items below, it's easy!</p>
				<form action="/price-watcher/trackItem" method="POST">
					<ol class="text-center">
						<li class="text-center">Enter the URL to your items below</li>
						<li class="text-center">Click submit! That's it!</li>
					</ol>
					<input class="form-control input-spacing" placeholder="URL" type="text" name="paramUrl"> <input
						type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
				</form>
			</div>
		</div>
		<jsp:include page="/widgets/MostPopularItems.jsp"/>
		<div class="row justify-content-center py-3">
			<div class="col-12">
				<h2 class="text-center">Supported Stores</h2>
				<p class="text-center lead">
					We support price tracking for the following retailers:
				</p>
			</div>
			<div class="col-2">
				<h5 class="text-center">Microcenter</h5>
			</div>
			<div class="col-2">
				<h5 class="text-center">Amazon</h5>
			</div>
			<div class="col-2">
				<h5 class="text-center">Jet</h5>
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