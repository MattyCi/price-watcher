/* Various utils for the tracking page
 * 
 */

$(document).ready(function() {
	highlightNavLink();
	setPriceColors();
});

function setPriceColors() {
	var priceChangeElements = $('.item-price-change-selector');
	
	priceChangeElements.each(function() {
		// get double value from dollar amount string
		var priceChange = Number($(this).text().replace(/[^0-9.-]+/g,""));
		
		if(0 == priceChange) {
			console.log("price is the same!");
			$(this).text("$0.00");
			$(this).addClass('text-secondary');
		} else if (priceChange > 0) {
			console.log("price increased!");
			$(this).addClass('text-success');
		} else {
			console.log("price decreased!");
			$(this).addClass('text-danger');
		}
	});
}

function highlightNavLink() {
	console.log("yeet");
	var itemTrackingNavLinkElement = $('#item-tracking-nav-link');
	itemTrackingNavLinkElement.addClass("active");
}