<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<div class="jumbotron" id="login-selector">
	<div class="container">
		<div class="row">
			<div class="col-sm">
				<h1 class="display-3 text-center">Log In</h1>
			</div>
			<div class="col-sm">
				<form id="loginForm" onsubmit="return checkParams(loginForm);" action="/price-watcher/userLogIn"
					method="POST">
					<input id="login_tooltip_open" name="username" class="form-control input-spacing"
						placeholder="Email" type="text"> <input name="password"
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
				<form id="registerForm" onsubmit="return checkParams(registerForm);"
					action="/price-watcher/userRegister" method="POST">
					<input id="register_tooltip_open" name="username" class="form-control input-spacing"
						placeholder="Email" type="text"> <input name="password"
						class="form-control input-spacing" placeholder="Password" type="password">
					<button class="btn btn-primary btn-lg btn-block">Register</button>
				</form>
			</div>
		</div>
	</div>
</div>