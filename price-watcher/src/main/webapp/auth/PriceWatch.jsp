<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Tracking Page"/>
</jsp:include>
<body>

<jsp:include page="/widgets/nav.jsp"></jsp:include>

<div class="row">
    <div class="container">
        <div class="formContainer col-md-8">
			<div class="jumbotron">
            	<h1 class="centerText">Price Watcher</h1>
            	<p class="centerText">Site: <c:out value = "${store.getStoreName()}"/></p>
            	<p class="centerText">Entered URL: <c:out value = "${paramUrl}"/></p>
            	<p class="centerText">Product Name: <c:out value = "${item.getItemName()}"/></p>
            	<p class="centerText">Current Price: <c:out value = "${item.getCurrentItemPrice()}"/></p>
			</div>
        </div>
    </div>
</div>



</body>
</html>
