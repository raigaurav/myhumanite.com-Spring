<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/home.css"/>'>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>SignUp | myhumanite</title>
<script type="text/javascript">
	$(function() {
		$("#inputEmail").focus();

		jQuery('.iframe').colorbox({
			iframe : true,
			width : '400px',
			height : '600px',
			transition : "elastic",
			opacity : 0.3,
		});
	})
</script>

<script>
	$(function() {
		var query = "";
		$("#campaignArea").load("campaignsearch?searchbar=" + query);
	});
</script>


</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div id="heading" class="col-md-12">
				<h2>myhumanite.com</h2>
			</div>
		</div>

		<div class="row">
			<div class="col-md-12" id="back"></div>
		</div>

		<div class="row">
			<div id="login" class="col-md-12">
				<div class="row">
					<div class="col-md-6">
						<a href="register" class="iframe"><button
								class="btn btn-md btn-warning">Register Here</button></a>
						<div id="info">
							<p>Take a step towards Humanity
							<p>
							<ul>
								<li>Volunteer in a cause</li>
								<li>Get Volunteers for your cause</li>
								<li>Share photos that rise the humanity again</li>
							</ul>
						</div>
					</div>
					<div class="col-md-6">
						<div class="panel">
							<div class="panel-heading">
								<h4>Login</h4>
							</div>
							<div class="panel-body">
								<form class="form-horizontal" id="loginform" role="form"
									method="POST"
									action="<c:url value="/j_spring_security_check" />">
									<div class="form-group">
										<label for="inputEmail" class="col-sm-3 control-label">Email</label>
										<div class="col-sm-9">
											<input class="form-control" name="j_username" id="inputEmail"
												placeholder="Email" required="" type="text">
										</div>
									</div>
									<div class="form-group">
										<label for="inputPassword3" class="col-sm-3 control-label">Password</label>
										<div class="col-sm-9">
											<input class="form-control" name="j_password"
												id="inputPassword" placeholder="Password" required=""
												type="password">
										</div>
									</div>
									<div class="form-group">
										<div class="col-sm-offset-3 col-sm-9">
											<div class="checkbox">
												<label class=""> <input class=""
													name="_spring_security_remember_me" type="checkbox">Remember
													me
												</label>
											</div>
										</div>
									</div>
									<div class="form-group last">
										<div class="col-sm-offset-3 col-sm-9">
											<button type="submit" class="btn btn-success btn-sm">Sign
												in</button>
											<button type="reset" class="btn btn-default btn-sm">Reset</button>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<div class="row">
			<div id="campaignArea" class="col-md-6"></div>
		</div>
	</div>
</body>
</html>