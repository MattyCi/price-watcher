// global
var errorText;
var tooltipId = null;

// initialize tooltip api
$(document).ready(function() {
	$('#error_tooltip').popup({
		type : 'tooltip',
		transition : '0.3s all 0.1s',
		offsettop : 75,
	});
});

// validate login parameters
function checkParams(form) {
	var formId = form.id;
	var userName = form["username"].value;
	var password = form["password"].value;

	// used to decide what id to display tooltip over in case of error
	if (formId === 'loginForm') {
		tooltipId = 'login';
	} else {
		tooltipId = 'register';
	}
	
	if (userName === "" || password === "") {
		showError("Username or password cannot be empty!");
		return false;
	} else {
		if (!validate(userName)) {
			return false;
		}
	}
	return true;
}

function validateEmail(email) {
	var re = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	return re.test(email);
}

function validate(email) {
	if (validateEmail(email)) {
		return true;
	} else {
		showError("Invalid email!");
	}
	return false;
}

function showError(errorText) {
	document.querySelector('#error_text').innerHTML = errorText;
	
	$('#error_tooltip').popup({
		tooltipanchor : $('#'+tooltipId+'_tooltip_open') // change jQuery object to anchor to the proper id
	});
	
	$('#error_tooltip').popup('show');
}
