<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Share Story</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/mystory.css"/>' />

<script>
	$(function() {
		$('.maindiv').append($('<div>').load('stories'));

		$(document).scroll(
				function() {
					if ($(window).scrollTop() + $(window).height() == $(
							document).height()) {
						// Reached page bottom. Call the ajax function or any other foo here.
						$('.maindiv').append($('<div>').load('stories'));
					}
				});
	})

	$(document).on('click','a.gallery', function(e) {
		e.preventDefault();
		$.colorbox({
			photo : true,
			width : '600px',
			height : '600px',
			href : this.href,
			opacity : 0.35,
		});
	});
</script>

</head>
<body>

			
	<div id="artimage"></div>
	<div class="maindiv"></div>

</body>
</html>