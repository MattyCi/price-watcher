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