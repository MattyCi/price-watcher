<div class="row bg-pw-light py-3" id="login-selector">
	<div class="col-sm-8 offset-sm-2">
	<h1 class="display-3 text-center">Log In</h1>
		<form class="login-register-forms" id="loginForm" onsubmit="return checkParams(loginForm);" action="/price-watcher/userLogIn"
			method="POST">
			<input id="login_tooltip_open" name="username" class="form-control input-spacing"
				placeholder="Email" type="text"> <input name="password"
				class="form-control input-spacing" placeholder="Password" type="password">
			<button class="btn btn-primary btn-lg btn-block">Log In</button>
		</form>
	</div>
</div>

<div class="row bg-pw-light py-3" id="register-selector">
	<div class="col-sm-8 offset-sm-2">
		<h1 class="display-3 text-center">Register</h1>
		<form class="login-register-forms" id="registerForm" onsubmit="return checkParams(registerForm);"
			action="/price-watcher/userRegister" method="POST">
			<input id="register_tooltip_open" name="username" class="form-control input-spacing"
				placeholder="Email" type="text"> <input name="password"
				class="form-control input-spacing" placeholder="Password" type="password">
			<button class="btn btn-primary btn-lg btn-block">Register</button>
		</form>
	</div>
</div>