<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>UPM Padel Administration</title>
<meta name="UPM Padel" content="Administration site">
<meta name="author" content="Inigo Molinuevo">
<link href="<c:url value='/static/css/bootstrap.min.css' />"
	rel="stylesheet">
<link href="<c:url value='/static/css/style.css' />" rel="stylesheet">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-md-12">
				<nav class="navbar navbar-default" role="navigation">
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse"
							data-target="#bs-example-navbar-collapse-1">
							<span class="sr-only">Toggle navigation</span><span
								class="icon-bar"></span><span class="icon-bar"></span><span
								class="icon-bar"></span>
						</button>
						<a class="navbar-brand">UPM Padel</a>
					</div>
					<div class="collapse navbar-collapse"
						id="bs-example-navbar-collapse-1">
						<ul class="nav navbar-nav">
							<li class="active"><a href="<c:url value='/show_courts'/>">Court</a></li>
						</ul>
					</div>
				</nav>
				<div class="row">
					<div class="col-md-1"></div>
					<div class="col-md-10">
						<ul class="nav nav-tabs">
							<li><a href="<c:url value='/show_courts'/>">Show</a></li>
							<li><a href="<c:url value='/create_court'/>">Create</a></li>
						</ul>
						<div class="page-header">
							<h1>
								<img src="<c:url value='/static/img/upmIcon.png' />"
									alt="upm icon" /> Show Courts
							</h1>
						</div>
						<div class="alert alert-success alert-dismissable">
							<button type="button" class="close" data-dismiss="alert"
								aria-hidden="true">×</button>
							<h4>Court created successfully!</h4>
							<strong>New court: </strong>${court}
						</div>
					</div>
					<div class="col-md-1"></div>
				</div>
			</div>
		</div>
	</div>
	<script src="<c:url value='/static/js/jquery.min.js' />"></script>
	<script src="<c:url value='/static/js/bootstrap.min.js' />"></script>
	<script src="<c:url value='/static/js/scripts.js' />"></script>
</body>
</html>