<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Story</title>

<script src='<c:url value="/resources/js/dropzone.js"/>'>
	
</script>
<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/images/dropzone.css"/>' />

<link rel="stylesheet" type="text/css"
	href='<c:url value="/resources/css/post.css"/>' />

<script src='<c:url value="/resources/js/jquery.validate.min.js"/>'></script>

<script>
	$(function() {

		$('#postForm').validate({
			rules : {
				heading : {
					minlength : 3,
					maxlength : 25,
					required : true
				},
				location : {
					minlength : 3,
					maxlength : 20,
					required : true
				},
				name : {
					required : true,
				},
				story : {
					required : true,
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


<script type="text/javascript">
	Dropzone.options.myAwesomeDropzone = {
		maxFiles : 1,
		accept : function(file, done) {
			console.log("uploaded");
			done();
		},
		init : function() {
			this.on("maxfilesexceeded", function(file) {
				this.removeAllFiles();
				this.addFile(file);
			});
		}
	};
</script>

</head>
<body>
	<div class="container-fluid">
		<div id="outsideForm">
			<div id="upload">
				<h2>Let's rise the humanity again!</h2>
				<div class="row">
					<div class="col-md-6">
						<form id="postForm" action="savepost" method="POST">
							<div class="row">
								<div class="form-group col-md-6">
									<label for="heading">Heading</label> <input
										class="form-control" id="heading" name="heading"
										placeholder="Heading" type="text">
								</div>
								<div class="form-group col-md-6">
									<label for="location">Location</label> <input
										class="form-control" id="location" name="location"
										placeholder="Location" type="text">
								</div>
							</div>
							<br /> <label for="story">Your Story</label>
							<div class="form-group">
								<textarea rows="6" cols="60" class="form-control" name="story"
									id="story" placeholder="Enter Your Story"></textarea>
								<br />
							</div>
							<div class="row">
								<div class="form-group col-md-6">
									<label for="type">Type</label><br /> <select id="type"
										name="type" class="btn btn-default">
										<option value="idea">Idea</option>
										<option value="story" selected="selected">Story</option>
									</select>
								</div>
								<div class="form-group col-md-6">
									<br /><input class="btn btn-danger" type="submit"
										value="POST">
								</div>
							</div>
						</form>
					</div>
					<div class="col-md-6">
						<form class="dropzone" id="my-awesome-dropzone"
							action="uploadImage" method="POST" enctype="multipart/form-data"></form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>