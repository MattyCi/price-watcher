/* Various utils for the tracking page
 * 
 */

window.onload = function() {
	setPriceColors();
};

function setPriceColors() {
	var currentPriceElement = $('#item-difference-0');

	if('$0.00' == currentPriceElement.text()) {
		currentPriceElement.text("$0.00 (No Change)");
		currentPriceElement.addClass('text-secondary');
	}
}