<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<jsp:include page="/widgets/header.jsp">
	<jsp:param name="title" value="Save your Recipes"/>
</jsp:include>
<body>

<jsp:include page="/widgets/nav.jsp"/>

<div class="row">
    <div class="container">
        <div class="formContainer col-md-8">
			<div class="jumbotron">
            	<h1 class="centerText">Welcome, <s:property value="username"/>!</h1>
            	<p class="centerText">Get alerted when your favorite items drop in price.</p>
            	<form action="/price-watcher/sendUrls" method = "POST">
                	Add items:<br>
               	 	<input class="form-control input-spacing" type="text" name="item0">
                	<input class="form-control input-spacing" type="text" name="item1">
                	<input class="form-control input-spacing" type="text" name="item2">
                	<input class="form-control input-spacing" type="text" name="item3">
                	<input class="form-control input-spacing" type="text" name="item4">
                	<input type="submit" class="btn btn-primary btn-lg btn-block" value="Submit">
            	</form>
			</div>
        </div>
    </div>
</div>

</body>
</html>
