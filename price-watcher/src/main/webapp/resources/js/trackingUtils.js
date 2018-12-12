/* Various utils for the tracking page
 * 
 */

$(document).ready(function() {
	setPriceColors();
});

function setPriceColors() {
	var priceChangeElement = $('#item-price-change-0');
	// get double value from dollar amount string
	var priceChange = Number(priceChangeElement.text().replace(/[^0-9.-]+/g,""));
	
	if(0 == priceChange) {
		console.log("price is the same!");
		priceChangeElement.text("$0.00");
		priceChangeElement.addClass('text-secondary');
	} else if (priceChange > 0) {
		console.log("price increased!");
		priceChangeElement.addClass('text-success');
	} else {
		console.log("price decreased!");
		priceChangeElement.addClass('text-danger');
	}
}