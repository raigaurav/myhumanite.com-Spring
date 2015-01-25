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
    <p>${emptymessage}</p>
	<c:forEach var="j" begin="0" end="${heading.size()-1}">
		<c:set var="i" value="${heading.size()-1-j}" />

		<div class="campaigns">
			<div style="height: 15%;">
				<h4>
					<a href="campaign-details?idcampaign=${idcampaign[i]}">${heading[i]}</a>
				</h4>
			</div>
			<hr>
			<div class="row">
				<div class="col-md-6">
					<p style="margin-left: 15px;">${host[i]}</p>
				</div>
				<div class="col-md-6">
					<p>${category[i]}</p>
				</div>
			</div>
			<div class="row footer">
				<div class="col-md-6">
					<p style="margin-left: 15px;">${city[i]}</p>
				</div>
				<div class="col-md-6">
					<p>${date[i]}</p>
				</div>
			</div>
		</div>
	</c:forEach>
</body>
</html>