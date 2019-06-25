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
		<div style="margin: 0 auto; width: 500px; padding-top: 100px;">
			<form:form method="post" modelAttribute="post">
			
				<div class="form-group">
				<label>Type your post header here:</label>
				<form:input path="postHeader" type="text" class="form-control"></form:input>
				<form:errors path="postHeader" style="color: red;" class="form-text text-muted" />
				</div>
				
				<div class="form-group">
				<label>Type your post content here:</label>
				<form:textarea path="postContent" rows="10" cols="70"></form:textarea>
				<form:errors path="postContent" style="color: red;" class="form-text text-muted" />
				</div>
			
			<form:button type="submit" class="btn btn-success">Add</form:button>	<a href="blog" class="btn btn-success">Cancel</a>
			
			</form:form>
		</div>

</body>
</html>