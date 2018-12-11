<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<nav class="navbar navbar-expand-lg navbar-dark bg-primary">
	<a class="navbar-brand" href="/price-watcher">Price Watcher</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse"
		data-target="#navbarColor01" aria-controls="navbarColor01"
		aria-expanded="true" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="navbar-collapse collapse show" id="navbarColor01" style="">
		<ul class="navbar-nav mr-auto">
			<shiro:notAuthenticated>
				<li id="login-active-toggle" class="nav-item"><a id="" class="nav-link login-appear" href="#">Log In</a></li>
				<li id="register-active-toggle" class="nav-item"><a id="" class="nav-link register-appear" href="#">Register</a></li>
			</shiro:notAuthenticated>
			<shiro:authenticated>
				<li class="nav-item"><a class="nav-link" href="/price-watcher/userLogOut">Log Out</a></li>
			</shiro:authenticated>
			<li class="nav-item"><a class="nav-link" href="#">About</a></li>
		</ul>
	</div>
</nav>