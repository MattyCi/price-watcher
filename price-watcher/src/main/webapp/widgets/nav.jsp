<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>

<nav class="navbar navbar-expand-sm navbar-dark bg-primary">
	<a class="navbar-brand" href="/price-watcher">PW</a>
	<button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbar" aria-controls="navbar" aria-expanded="false" aria-label="Toggle navigation">
		<span class="navbar-toggler-icon"></span>
	</button>

	<div class="navbar-collapse collapse justify-content-end" id="navbar" style="">
    	<ul class="navbar-nav">
			<li class="nav-item">
    			<shiro:authenticated>
					<form id="logoutForm" class="form-inline my-2 my-lg-0 justify-content-end" action="/price-watcher/userLogOut">
      					<button class="btn btn-secondary my-2 my-sm-0 btn-block-xs-only">Log Out</button>
   					</form>
				</shiro:authenticated>
    			<shiro:notAuthenticated>
    				<form id="loginForm" class="form-inline my-2 my-lg-0 justify-content-end" onsubmit="return checkParams(loginForm);" action="/price-watcher/userLogIn" method="POST">
    					<input id="my_tooltip_open" name="username" class="form-control mr-sm-2" placeholder="Username" type="text">
    					<input name="password" class="form-control mr-sm-2" placeholder="Password" type="password">
      					<button class="btn btn-secondary my-2 my-sm-0 btn-block-xs-only">Log In</button>
   					</form>
				</shiro:notAuthenticated>
			</li>
		</ul>
	</div>
</nav>