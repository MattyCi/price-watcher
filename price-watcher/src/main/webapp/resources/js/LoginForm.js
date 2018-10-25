// validate login parameters
function checkParams(loginForm) {
	var userName = loginForm["username"].value;
	var password = loginForm["password"].value;

	if (userName == "") {
		var errorText = "Username cannot be empty!";
		document.querySelector('#login_error_text').innerHTML = errorText; 
		$('#error_tooltip').popup('show');
		return false;
	} else if (password == "") {
		var errorText = "Password cannot be empty!";
		document.querySelector('#login_error_text').innerHTML = errorText;
		$('#error_tooltip').popup('show');
		return false;
	} else {
		return true;
	}
}