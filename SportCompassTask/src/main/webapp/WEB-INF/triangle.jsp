<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
		<div style="margin: 0 auto; width: 500px; padding-top: 50px;">
		<form:form method="post" modelAttribute="tri" action="triangle">
			
			<div class="form-group">
				<label>A parametr</label>
				<form:input path="aParam" type="text" class="form-control" />
			</div>
			
			<div class="form-group">
				<label>B parametr</label>
				<form:input path="bParam"  type="text" class="form-control" />
			</div>
			
			<div class="form-group">
				<label>C parametr</label>
				<form:input path="cParam" type="text" class="form-control" />
			</div>
			
			<form:button type="submit" class="btn btn-success">Count</form:button>
			
		</form:form>
		
		<br /> <br />
		<a href= "home" class="btn btn-success"> Back to main page</a>
		</div>
</body>
</html>