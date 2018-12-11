<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Tracking Page" />
</jsp:include>
<body>
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<div class="container-fluid">
		<shiro:notAuthenticated>
			<div class="row bg-warning p-4">
				<div class="pl-4 col-sm-8">
					<p class="lead text-left">
						Hey! To continue and track this item's price, you have to register. <a class="register-appear" href="#">Click here to get on that...</a>
		   			</p>
		  		</div>
			</div>
		</shiro:notAuthenticated>
		
		<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>
		
		<div class="row bg-light p-4">
           		<div class="col-sm-4 d-flex align-items-center">
           			<img class="vertical-align rounded mx-auto d-block" src="https://via.placeholder.com/150"/>
           		</div>
       			<div class="pl-4 col-sm-8">
	       			<p class="lead text-left">
	       				<a href="<c:out value = "${paramUrl}"/>"><c:out value = "${item.getItemName()}"/></a>
		           	</p>
		           	<p class="text-left">Site: <c:out value = "${store.getStoreName()}"/></p>
		           	<p class="text-left">Original Price (Since first tracked): <fmt:formatNumber value = "${item.getOriginalItemPrice()}" type = "currency"/></p>
		           	<p class="text-left">Current Price: <fmt:formatNumber value = "${item.getCurrentItemPrice()}" type = "currency"/></p>
		           	<p class="text-left">Price Difference: <span id="item-difference-0"><fmt:formatNumber value = "${item.getItemPriceDifference()}" type = "currency"/></span></p>
	           	</div>
		</div>
	</div>
       
</body>
</html>
