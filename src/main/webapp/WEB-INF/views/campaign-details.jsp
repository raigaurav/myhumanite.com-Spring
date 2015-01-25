<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/campaign-details.css"/>' />


</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-10">
				<div id="campaigndetails">
					<div class="heading">
						<h2>${heading}</h2>
					</div>
					<div class="purpose">Purpose : ${purpose}</div>
					<hr>
					<div class="category">Category : ${category}</div>
					<hr>
					<div class="host">Host : <a href="profile?userid=${host}">${host}</a></div>
					<hr>
					<div class="city">City : ${city}</div>
					<hr>
					<div class="location">Landscape : ${location}</div>
					<hr>
					<div class="date">Timings : ${date}</div>
					<hr>
					<security:authentication property="principal.username"
						var="currentuser" />
					<c:if test="${currentuser!=host&&check==0}">
						<a href="joinCampaign?idcampaign=${idcampaign}"><input
							id="joinbutton" type="button" value="I Want to Volunteer"></a>
					</c:if>
					<c:if test="${currentuser!=host&&check!=0}">
						<h3 style="margin-left: 30%; color: #337AB7;">Cause already
							joined</h3>
					</c:if>
				</div>
			</div>
			<c:if test="${currentuser==host}">
				<div class="col-md-2">
					<div id="sidebar">
						<h3>Volunteers List</h3>
						<ol>
							<c:forEach begin="0" end="${volunteerList.size()}" var="i">
								<li><a href="profile?userid=${volunteerList[i]}">${volunteerList[i]}</a>
								</li>
							</c:forEach>
						</ol>
					</div>
				</div>
			</c:if>
		</div>
	</div>
</body>
</html>