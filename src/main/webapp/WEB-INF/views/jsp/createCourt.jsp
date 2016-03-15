<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Create Court</h1>
	<form:form action="create_court" modelAttribute="court">
		<p>Id:
			<form:input path="courtId" placeholder="CourtId" required="required" />
			<form:errors path="courtId" cssClass="error" />
		</p>
		<p>Active:
			<form:radiobutton path="active" value="true" required="required"/>True
			<form:radiobutton path="active" value="false" required="required"/>False
			<form:errors path="active" cssClass="error" />
		</p>
		<p><input type="submit" value="Create"></p>
	</form:form>
</body>
</html>