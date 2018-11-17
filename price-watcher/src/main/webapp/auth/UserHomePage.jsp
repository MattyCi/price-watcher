<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Save your Recipes" />
</jsp:include>
<body>
	<jsp:include page="/widgets/nav.jsp" />
	<div class="jumbotron">
		<div class="container">
			<div class="row">
				<div class="col-sm-8 offset-sm-2">
					<h1 class="display-3 text-center">Welcome, <s:property value="username"/>!</h1>
					<p class="text-center lead">Get alerted when your favorite items drop in price. Start
						tracking items below, it's easy!</p>
					<p class="centerText"></p>
					<form action="/price-watcher/sendUrls" method="POST">
						<ol class="centerText">
							<li class="centerText">Enter the URLs to your items below</li>
							<li class="centerText">Click submit! That's it!</li>
						</ol>
						<input class="form-control input-spacing" type="text" name="paramUrl"> <input
							type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>
