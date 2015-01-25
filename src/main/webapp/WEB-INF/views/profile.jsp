<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Profile</title>

<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/profile.css" />' />
<script>
	$(function() {
		$("#submitButton").hide();
		$("#uploadBtn").change(function() {
			$("#submitButton").click();
		});

		$("#editProfile").hide();
		$("#edit").click(function() {
			$("#editProfile").show();
			$("#displayProfile").hide();
			if ($("#edit").text() == "Update") {
				$("#profileForm").submit();
			}
			;
			$("#edit").text("Update");
		});
		
	});
</script>
</head>
<body>
	<security:authentication property="principal.username"
		var="currentuser" />
	<div class="container-fluid">
		<div id="profile">
			<div id="left">
				<div id="profilepic">
					<c:set var="url" value="/resources/profileimages/" />
					<img src='<c:url value="${url}${profilepicpath}" />'>
				</div>
				<c:if test="${currentuser==userid}">
					<div id="options">
						<div class="fileUpload">
							<span>Upload Profile Pic</span>
							<form action="uploadpic" method="post"
								enctype="multipart/form-data">
								<input id="uploadBtn" name="uploadFile" type="file"
									class="upload" /> <input type="submit" id="submitButton">
							</form>
						</div>
						<hr>
						<button id="edit" class="mybt">Edit Profile</button>
						<hr>
						<a href="mycampaigns"><button class="mybt">My Campaigns</button></a>
						<hr>
					</div>
				</c:if>
			</div>
			<div id="right">
				<div id="displayProfile">
					<div>
						<h4>Name</h4>
						<hr />
						<h3>${first_name} ${last_name}</h3>
					</div>

					<div>
						<h4>City</h4>
						<hr />
						<h3>${usercity}</h3>
					</div>
					<div>
						<h4>Country</h4>
						<hr />
						<h3>${country}</h3>
					</div>
					<div>
						<h4>Interest(Volunteering)</h4>
						<hr />
						<h3>${interest}</h3>
					</div>
					<div>
						<h4>Score</h4>
						<hr />
						<h3>${score}</h3>
					</div>
				</div>
				<div id="editProfile">
					<form id="profileForm" class="form-inline" action="updateProfile">
						<div>
							<h4>First Name</h4>
							<hr />
							<input name="first_name" class="inputProfile form-control"
								type="text" value="${first_name}">
						</div>
						<div>
							<h4>Last Name</h4>
							<hr />
							<input name="last_name" class="inputProfile form-control"
								type="text" value="${last_name}">
						</div>
						<div>
							<h4>City</h4>
							<hr />
							<input name="usercity" class="inputProfile form-control"
								type="text" value="${usercity}">
						</div>
						<div>
							<h4>Country</h4>
							<hr />
							<input name="country" class="inputProfile form-control"
								type="text" value="${country}">
						</div>
						<div>
							<h4>Interests</h4>
							<hr />
							<input name="interest" class="inputProfile form-control"
								type="text" value="${interest}">
						</div>
					</form>
				</div>
			</div>
		</div>
		<div id="campaignlist">
			<h3>User Activity</h3>
			<c:if test="${heading.size()>0}">
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
			</c:if>
		</div>
	</div>
</body>
</html>