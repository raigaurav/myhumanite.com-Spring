<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>

</head>
<body>
	<c:set var="url" value="/resources/images/" />

	<c:forEach var="j" begin="0" end="${picPath.size()-1}">
		<c:set var="i" value="${picPath.size()-1-j}" />

		<div class="story">
			<div class="row">
				<div class="heading col-md-8">
					<h2>${heading[i]}</h2>
				</div>
				<div class="location col-md-4">
					<h6>${location[i]}</h6>
				</div>
			</div>
			<a href='<c:url value="${url}${picPath[i]}"/>' class="gallery"> <img
				title="${picPath[i]}" class="image"
				src='<c:url value="${url}${picPath[i]}"/>' />
			</a>
			<div class="text">
				<p>${story[i]}</p>
			</div>
			<br />
		</div>
	</c:forEach>

</body>
</html>