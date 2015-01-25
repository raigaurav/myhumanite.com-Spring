<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu</title>
<script src='<c:url value="/resources/js/jquery-ui.min.js"/>'></script>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/js/jquery-ui.min.css"/>' />
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/menu.css"/>' />
<script>
	$(function() {
		$("#searchBox").val("");

		$("#searchBox").autocomplete({
			source : function(request, response) {
				var boxValue = $("#searchBox").val();
				$.ajax({
					type : "GET",
					url : "getLocations",
					dataType : "json",
					data : "Value=" + boxValue,
					success : function(ss) {
						response($.map(ss, function(loc) {
							return {
								label : loc,
								value : loc
							}
						}));
					}
				});
			},
			select : function(event, ui) {
				if (ui.item) {
					$('#searchBox').val(ui.item.value);
				}
				$('#search').submit();
				$('#submit').trigger('click');
			}
		});

		$("#submit").click(function() {
			var val = $("#searchBox").val();
			if (val == "") {
				alert("SearchBox is empty!");
				return false;
			}
		})
	})
</script>
</head>
<body>
	<div class="container-fluid">
		<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">My Waste</a>
			</div>
			<div class="collapse navbar-collapse navbar-ex1-collapse">
				<div class="collapse navbar-collapse">
					<ul class="nav navbar-nav">
						<li><a href="mystory"><span
								class="glyphicon glyphicon-home"></span> Home</a></li>
						<li><a href="volunteer"><span
								class="glyphicon glyphicon-globe"></span>Volunteer</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-pencil"></span> Story<b class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="mystory">My Story</a></li>
								<li><a href="newpost">Share</a></li>
							</ul></li>
						<li>
							<form class="navbar-form" action="filteredpost" method="GET"
								id="search" role="search">
								<div class="input-group">
									<input id="searchBox" name="searchBox" class="form-control"
										type="text" placeholder="Search Stories in Your City">
									<div class="input-group-btn">
										<button id="submit" class="btn btn-danger" type="submit">
											<i class="glyphicon glyphicon-search"></i>
										</button>
									</div>
								</div>
							</form>
						</li>
					</ul>

					<security:authentication property="principal.username"
						var="username" />
					<ul class="nav navbar-nav navbar-right">
						<li><a href="profile?userid=${username}">${username}</a></li>
						<li><a href='<c:url value="/j_spring_security_logout" />'><span
								class="glyphicon glyphicon-asterisk"></span>Logout</a></li>
						<li class="dropdown"><a href="#" class="dropdown-toggle"
							data-toggle="dropdown"><span
								class="glyphicon glyphicon-info-sign"></span> About Us<b
								class="caret"></b></a>
							<ul class="dropdown-menu">
								<li><a href="#">Vision</a></li>
								<li><a href="#">Contact Us</a></li>
							</ul></li>
					</ul>
				</div>
			</div>
		</nav>
	</div>
</body>
</html>