// global
var loginSelectorFlag = 0;
var registerSelectorFlag = 0;

// TODO: Refactor this trash
function displayArea(areaToToggle) {
	if ((areaToToggle !== undefined) && (areaToToggle !== null)) {
		if (areaToToggle === "login") {
			if (!loginSelectorFlag) {
				document.getElementById("login-selector").style.display = "flex";
				document.getElementById("login-active-toggle").classList.add("active");
				loginSelectorFlag = 1;
				
				if (registerSelectorFlag == 1) {
					hideArea("register");
				}
				
			} else {
				hideArea("login");
			}
		} else if (areaToToggle === "register") {
			if (!registerSelectorFlag) {
				document.getElementById("register-selector").style.display = "flex";
				document.getElementById("register-active-toggle").classList.add("active");
				registerSelectorFlag = 1;
				
				if (loginSelectorFlag == 1) {
					hideArea("login");
				}
				
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
