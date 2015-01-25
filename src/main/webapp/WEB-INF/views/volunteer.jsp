<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Volunteer</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/volunteer.css"/>' />

<script>
	$(function() {
		var query = $("#searchbar").val();
		$("#campaignArea").load("campaignsearch?searchbar=" + query);

		$("#submitbutton").click(function() {
			var query = $("#searchbar").val();
			$("#campaignArea").load("campaignsearch?searchbar=" + query);
		});
	});
</script>

</head>
<body>
	<div class="container-fluid">
		<div id="top">
			<h1>Volunteer opportunities</h1>
		</div>
		<div id="sidebar">
			<hr>
			<a href="newcampaign">Start a new campaign</a>
			<hr>
			<input type="text" class="form-control" id="searchbar"
				name="searchbar" placeholder="Search">
			<hr>
			<button class="btn btn-warning" id="submitbutton">Search</button>
			<hr>

		</div>

		<div id="campaignArea"></div>
	</div>

</body>
</html>