<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Home Page" />
</jsp:include>
<body>
	<jsp:include page="/widgets/nav.jsp"></jsp:include>

	<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>

	<div class="container-fluid">
		<div class="row bg-light p-4">
           		<div class="col-sm-4 d-flex align-items-center">
           			<img class="vertical-align" src="https://via.placeholder.com/150"/>
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
