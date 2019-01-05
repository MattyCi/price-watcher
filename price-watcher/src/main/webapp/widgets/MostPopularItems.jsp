<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:useBean id="ItemDAO" class="org.matt.daos.ItemDAO" scope="session"/>

<div class="row justify-content-center pb-3">
	<div class="col-12 py-3">
		<h2 class="text-center">Most Popular Items</h2>
	</div>
<c:forEach items="${ItemDAO.getMostPopularItems()}" var="popularItem" varStatus="loop">
	<div class="col-6 col-md-3 py-1 reduced-p">
		<p class="text-center">
			<a href="<c:out value = "${popularItem.getUrl()}"/>"><c:out value="${popularItem.getItemName()}"/></a>
		</p>
		<img class="vertical-align mx-auto rounded d-block pb-1" src="<c:out value="${popularItem.getImageUrl()}" />"
			width="120" height="120"/>
	</div>
</c:forEach>
</div>