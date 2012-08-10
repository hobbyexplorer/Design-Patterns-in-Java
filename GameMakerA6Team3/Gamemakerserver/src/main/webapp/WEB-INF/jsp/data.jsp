<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<title>Knownspace Sample</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.css" />
<script src="http://code.jquery.com/jquery-1.6.4.min.js"></script>
<script src="http://code.jquery.com/mobile/1.0rc2/jquery.mobile-1.0rc2.min.js"></script>
</head>
<body>
<div data-role="page" class="type-home">
	<div data-role="content">
		<div data-role="controlgroup">
			<a data-transition="fade" href="index" data-role="button">Back</a>
		</div>
		<ul data-role="listview" data-inset="true" data-theme="c" data-dividertheme="b">
			<li data-role="list-divider">Sample Data</li>
			<c:if test="${empty samples}">
				<li>No data yet</li>
			</c:if>
			<c:forEach var="sample" items="${samples}">
				<li>${sample.name} : ${sample.value}</li>
			</c:forEach>
		</ul>
	</div>
</div>
</body>
</html>

