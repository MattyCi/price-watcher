<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Home Page"/>
</jsp:include>
<body>

<jsp:include page="/widgets/nav.jsp"></jsp:include>

<div class="row">
    <div class="container">
        <div class="formContainer col-md-8">
			<div class="jumbotron">
            	<h1 class="centerText">Price Watcher</h1>
            	<p class="centerText">Get alerted when your favorite items drop in price.</p>
            	<form action="/price-watcher/sendUrls" method = "POST">
                	<p class="centerText">Start tracking items below, it's easy!</p>
                	<ol class="centerText">
                		<li class="centerText">Enter the URLs to your items below</li>
                		<li class="centerText">Click submit! That's it!</li>
                	</ol>
               	 	<input class="form-control input-spacing" type="text" name="paramUrl">
                	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
            	</form>
			</div>
        </div>
    </div>
</div>

<div id="error_tooltip" class="well">
    <div class="alert alert-dismissible alert-warning">
  		<button class="close error_tooltip_close">&times;</button>
  		<strong id="login_error_text"></strong>
	</div>
</div>

<script src="https://cdn.rawgit.com/vast-engineering/jquery-popup-overlay/1.7.13/jquery.popupoverlay.js"></script>
<script>
	$(document).ready(function () {
    	$('#error_tooltip').popup({
    		type: 'tooltip',
    		transition: '0.3s all 0.1s',
    		offsettop: 75,
    		tooltipanchor: $('#my_tooltip_open')
		});
	});
</script>

</body>
</html>