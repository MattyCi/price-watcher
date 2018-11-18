// global
var loginSelectorFlag = 0;
var registerSelectorFlag = 0;

// on load events
window.onload = function() {
	var loginSelector = document.getElementById("login-appear");
	var registerSelector = document.getElementById("register-active-toggle");
	
	loginSelector.onclick = function() {
    	displayArea("login");
    }
	
	registerSelector.onclick = function() {
    	displayArea("register");
    }
}

// TODO: Refactor this trash
function displayArea(areaToToggle) {
	if ((areaToToggle !== undefined) && (areaToToggle !== null)) {
		if (areaToToggle === "login") {
			if (!loginSelectorFlag) {
				document.getElementById("login-selector").style.display = "block";
				document.getElementById("login-active-toggle").classList.add("active");
				loginSelectorFlag = 1;
			} else {
				hideArea("login");
			}
		} else if (areaToToggle === "register") {
			if (!registerSelectorFlag) {
				document.getElementById("register-selector").style.display = "block";
				document.getElementById("register-active-toggle").classList.add("active");
				registerSelectorFlag = 1;
			} else {
				hideArea("register");
			}
		} else {
			console.error("Unexpected input!");
		}
	} else {
		console.error("Null or undefined input!");
	}
}

//TODO: Refactor this trash
function hideArea(areaToToggle) {
	if ((areaToToggle !== undefined) && (areaToToggle !== null)) {
		if (areaToToggle === "login") {
			document.getElementById("login-selector").style.display = "none";
			document.getElementById("login-active-toggle").classList
					.remove("active");
			loginSelectorFlag = 0;
		} else if (areaToToggle === "register") {
			document.getElementById("register-selector").style.display = "none";
			document.getElementById("register-active-toggle").classList
					.remove("active");
			registerSelectorFlag = 0;
		} else {
			console.error("Unexpected input!");
		}
	} else {
		console.error("Null or undefined input!");
	}
}
