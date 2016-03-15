<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>UPM Padel</title>
</head>
<body>
	<h1>Court List</h1>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Active</th>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${courtList}" var="court">
				<tr>
					<td>${court.courtId}</td>
					<td>${court.active}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="<c:url value='/create_court'/>">Create Court</a>
</body>
</html>