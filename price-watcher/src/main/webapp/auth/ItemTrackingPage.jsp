<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
<head>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Tracking Page" />
</jsp:include>
<jsp:include page="/widgets/TrackingPageAssets.jsp" />
</head>
<body class="bg">
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<div class="container bg-pw-light">
		<c:choose>
			<c:when test="${empty itemList}">
				<div class="row">
					<div class="col-sm-12 pt-4">
						<h2 class="text-center">You haven't tracked any items.</h2>
						<p class="text-center">Consider tracking some items by entering a URL to a new item, or
							check out some of the most popular items.</p>
					</div>
				</div>
			</c:when>
			<c:otherwise>
				<shiro:notAuthenticated>
					<div class="row justify-content-center py-4">
						<div class="col-sm-12 col-lg-10 col-xl-8 accordion" id="register-warning-btn">
							<button class="btn btn-warning btn-lg btn-block btn-text-wrap" type="button"
								data-toggle="collapse" data-target="#collapseOne" aria-expanded="true"
								aria-controls="collapseOne">Consider registering! Click here to see why.</button>
							<div id="collapseOne" class="collapse" data-parent="#register-warning-btn">
								<div class="card-body bg-white">
									<ol class="text-left">
										<li>As a guest user, if your browser cookies are deleted, we will have no way to
											preserve your tracked item data.</li>
										<li>Registered users receive email notifications whenever their item's price changes.</li>
									</ol>
									<button type="button" class="btn btn-block btn-outline-primary register-appear">Register</button>
								</div>
							</div>
						</div>
					</div>
				</shiro:notAuthenticated>
			</c:otherwise>
		</c:choose>

		<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>

		<div class="row justify-content-center py-2">
			<div class="col-sm-12 col-lg-10 col-xl-8">
				<h2 class="text-center">Track an item:</h2>
				<!--  TODO: Make the form appear with a button -->
				<form action="/price-watcher/trackItem" method="POST">
					<input class="form-control input-spacing" placeholder="URL" type="text" name="paramUrl">
					<input type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
				</form>
			</div>
		</div>
		
		<c:if test="${not empty itemList}">
			<div class="col-12 py-3">
				<h2 class="text-center">Your Tracked Items</h2>
			</div>
		</c:if>
		
		<c:forEach items="${itemList}" var="item" varStatus="loop">
			<div class="row justify-content-center p-2">
				<div class="col-sm-4 col-lg-2 d-flex align-items-center">
					<img class="vertical-align mx-auto rounded d-block pb-1" src="https://via.placeholder.com/150" />
				</div>
				<div class="col-sm-8 col-lg-6">
					<p class="lead">
						<a href="<c:out value = "${paramUrl}"/>"><c:out value="${item.getItemName()}" /></a>
					</p>
					<p>
						Site:
						<c:out value="${item.getStore().getStoreName()}" />
					</p>
					<p>
						Original Price (Since first tracked):
						<fmt:formatNumber value="${item.getOriginalItemPrice()}" type="currency" />
					</p>
					<p>
						Current Price:
						<fmt:formatNumber value="${item.getCurrentItemPrice()}" type="currency" />
					</p>
					<p>
						Price Change: <span class="item-price-change-selector"><fmt:formatNumber
								value="${item.getItemPriceDifference()}" type="currency" /></span>
					</p>
				</div>
			</div>
		</c:forEach>
		<jsp:include page="/widgets/MostPopularItems.jsp" />
	</div>
	<jsp:include page="/widgets/footer.jsp" />
</body>
</html>
