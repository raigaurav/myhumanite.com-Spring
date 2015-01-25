<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Start a new campaign</title>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/newcampaign.css"/>' />
<script src='<c:url value="/resources/js/jquery.validate.min.js"/>'></script>

<script>
	$(function() {

		$('#postForm').validate({
			rules : {
				heading : {
					minlength : 3,
					maxlength : 40,
					required : true
				},
				purpose : {
					minlength : 10,
					required : true
				},
				category : {
					required : true
				},
				city : {
					minlength : 3,
					maxlength : 20,
					required : true
				},
				location : {
					minlength : 3,
					maxlength : 20,
					required : true
				},
				date : {
					required : true
				},

			},
			highlight : function(element) {
				$(element).closest('.form-group').addClass('has-error');
			},
			unhighlight : function(element) {
				$(element).closest('.form-group').removeClass('has-error');
			},
			errorElement : 'span',
			errorClass : 'help-block',
			errorPlacement : function(error, element) {
				if (element.parent('.input-group').length) {
					error.insertAfter(element.parent());
				} else {
					error.insertAfter(element);
				}
			}
		});

	});
</script>
</head>
<body>
	<div class="fluid-container">
		<div id="campaign-details">
			<form action="saveCampaign" id="postForm" method="post" role="form">
				<div id="insideform">
					<div class="row">
						<div class="col-md-6">
							<input class="form-control" id="heading" name="heading"
								type="text" placeholder="Heading">
						</div>
					</div>

					<div class="row">
						<div class="col-md-6">

							<textarea rows="6" cols="60" class="form-control" name="purpose"
								id="purpose" placeholder="Purpose"></textarea>
						</div>
					</div>

					<div class="row">
						<div class="col-md-10">
							<input class="form-control" name="category" id="category"
								type="text" placeholder="Category">
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">
							<input class="form-control" name="city" id="city" type="text"
								placeholder="City">
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">
							<input class="form-control" name="location" id="location"
								type="text" placeholder="location">
						</div>
					</div>
					<div class="row">
						<div class="col-md-10">
							<input class="form-control" name="date" id="date" type="text"
								placeholder="Enter the timings">
						</div>
					</div>
					<input type="submit" class="btn btn-success" value="Start Campaign">
				</div>
			</form>
		</div>
	</div>
</body>
</html>