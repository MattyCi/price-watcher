// global
var loginSelectorFlag = 0;
var registerSelectorFlag = 0;

// on load events
window.onload = function() {
	var loginSelectors = document.getElementsByClassName("login-appear");
	var registerSelectors = document.getElementsByClassName("register-appear");
	
	for (var i = 0; i < loginSelectors.length; i++) {
        var loginSelector = loginSelectors[i];
        loginSelector.onclick = function() {
        	displayArea("login");
        }
	}
	
	for (var i = 0; i < registerSelectors.length; i++) {
        var registerSelector = registerSelectors[i];
        registerSelector.onclick = function() {
        	displayArea("register");
        }
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
