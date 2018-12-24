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
				<p class="lead text-center">
					Hey! It seems you haven't registered yet. Consider registering because...
	   			</p>
				<!-- <ol>
					<li>As a guest user, if your browser cookies are deleted, we will have
					no way to preserve your tracked item data.</li>
					<li>Registered users recieve email notifications whenever their item's
					price changes.</li>
				</ol>
				<a class="register-appear" href="#">Click here to register...</a> -->
	  		</div>
		</div>
	</shiro:notAuthenticated>
		
		<jsp:include page="/widgets/RegisterAndLoginWidgets.jsp"></jsp:include>
					
		<c:forEach items="${itemList}" var="item" varStatus="loop"> 
		  <div class="row bg-light p-4">
	        	<div class="col-sm-4 d-flex align-items-center">
	        		<img class="vertical-align rounded mx-auto d-block" src="https://via.placeholder.com/150"/>
	        	</div>
	       		<div class="pl-4 col-sm-8">
		       		<p class="lead text-left">
		       			<a href="<c:out value = "${paramUrl}"/>"><c:out value = "${item.getItemName()}"/></a>
			        </p>
			        <p class="text-left">Site: <c:out value = "${item.getStore().getStoreName()}"/></p>
			        <p class="text-left">Original Price (Since first tracked): <fmt:formatNumber value = "${item.getOriginalItemPrice()}" type = "currency"/></p>
			        <p class="text-left">Current Price: <fmt:formatNumber value = "${item.getCurrentItemPrice()}" type = "currency"/></p>
			        <p class="text-left">Price Change: <span class="item-price-change-selector"><fmt:formatNumber value = "${item.getItemPriceDifference()}" type = "currency"/></span></p>
		        </div>
			</div>
		</c:forEach>
	</div>
       
</body>
</html>
