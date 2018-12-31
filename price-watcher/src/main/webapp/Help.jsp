<div class="container bg-pw-light">
	<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>
		<div class="row justify-content-center py-3">
			<div class="col-sm-12 col-md-10 col-lg-8">
				<p class="display-3 text-center">Price Watcher</p>
				<p class="text-center lead">Get alerted when your favorite items drop in price. Start
					tracking items below, it's easy!</p>
				<form action="/price-watcher/trackItem" method="POST">
					<ol class="text-center">
						<li class="text-center">Enter the URL to your items below</li>
						<li class="text-center">Click submit! That's it!</li>
					</ol>
					<input class="form-control input-spacing" placeholder="URL" type="text" name="paramUrl"> 
					<div class="col-md-3 no-pad">
      					<input type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
   			 		</div>
				</form>
			</div>
			
		</div>
		<jsp:include page="/widgets/MostPopularItems.jsp"/>
		<jsp:include page="/widgets/SupportedStores.jsp"/>
	</div>