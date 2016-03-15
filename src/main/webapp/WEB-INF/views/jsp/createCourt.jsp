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
							<li class="active"><a href="<c:url value='/create_court'/>">Create</a></li>
						</ul>
						<div class="page-header">
							<h1>
								<img src="<c:url value='/static/img/upmIcon.png' />"
									alt="upm icon" /> Create Court
							</h1>
						</div>
						<div class="row">
							<div class="col-md-6">
								<form:form action="create_court" modelAttribute="court">
									<div class="form-group">
										<label for="courtId">Court Id</label>
										<form:input path="courtId" placeholder="CourtId"
											required="required" class="form-control" />
										<form:errors path="courtId" cssClass="error" />
									</div>
									<div class="form-group">
										<label for="active">Active</label><br>
										<form:radiobutton path="active" value="true"
											required="required" />
										True
										<form:radiobutton path="active" value="false"
											required="required" />
										False
										<form:errors path="active" cssClass="error" />
									</div>
									<p>
										<input type="submit" class="btn btn-default" value="Create">
									</p>
								</form:form>

							</div>
							<div class="col-md-6"></div>
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