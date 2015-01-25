<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src='<c:url value="/resources/js/jquery.validate.min.js"/>'></script>
<title>Insert title here</title>
<style>
body {
	background-color: #EEE5DC;
}

.row {
	margin-top: 25px;
}
</style>

<script>
	$(function() {

		$('form').validate({
			rules : {
				first_name : {
					minlength : 3,
					maxlength : 20,
					required : true
				},
				last_name : {
					minlength : 3,
					maxlength : 20,
					required : true
				},
				email : {
					required : true,
				},
				password : {
					minlength : 6,
					required : true,
				},
				confirm : {
					minlength : 6,
					equalTo : "#password"
				},
			},
			 submitHandler: function(form) {
							$.ajax({
							url : "newuser",
							type : "POST",
							data :  $(form).serializeArray(),
							success : function(data, textStatus, jqXHR) {
								 $(form).hide();
								 $("body").html("<h3 style='color:green'>Registration Successful</h3>");
							},
							error : function(jqXHR, textStatus, errorThrown) {
								//if fails     
							}
						});
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


		$("#email").focusout(function() {
			var email = $("#email").val();
			$.ajax({
				url : "userExists",
				type : "GET",
				data : "email=" + email,
				success : function(data) {
					if (data == false) {
						$("#emailmsg").text("Email already exists");
						$("#emailmsg").css("color", "#AA4550");
						$("#submit").attr("disabled", "true");
					} else {
						$("#emailmsg").text("");
						$("#submit").removeAttr("disabled");
					}
				}
			});
		});
	});
</script>
</head>
<body>
	<div class="container-fluid">
		<form id="registerform">
			<div class="row">
				<div class="form-group col-md-2">
					<label for="first_name">First Name</label> <input
						class="form-control" id="first_name" name="first_name"
						placeholder="First Name" type="text">
				</div>
				<div class="form-group col-md-2">
					<label for="last_name">Last Name</label> <input
						class="form-control" id="last_name" name="last_name"
						placeholder="Last Name" type="text">
				</div>
				<div class="form-group col-md-2">
					<label for="email">E-mail</label> <input class="form-control"
						id="email" name="email" placeholder="E-mail" type="email">
					<p id="emailmsg"></p>
				</div>
				<div class="form-group col-md-3">
					<label for="password">Password</label> <input class="form-control"
						id="password" name="password" placeholder="Password"
						type="password">
				</div>
				<div class="form-group col-md-3">
					<label for="confirm">Confirm</label> <input class="form-control"
						id="confirm" name="confirm" placeholder="Confirm Password"
						type="password">
				</div>
			</div>
			<br /> <input class="btn btn-warning btn-md" id="submit"
				type="submit" value="Sign Up">
		</form>
	</div>
</body>
</html>