<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Blog</title>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" 
integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>
		<div class="container">
		  <div class="row">
		  	 <div class="col-sm" style="height: 120px; background-color: #689df2;">
		 		 <h2 style="color: white; margin-top: 3%; margin-left: 17%;"><span >My blog</span></h2>
		  	</div>
		  </div>
		  <div class="row">
		    <div class="col-" style="width: 200px;">
		     	<ul class="list-group">
				  <li class="list-group-item"><a href= "addPost" class="btn btn-success"> Add post</a></li>
				  <li class="list-group-item"><a href= "home" class="btn btn-success"> Back to main page</a></li>
				</ul>	
		    </div>
		    <div class="col-sm">
		      <div style="text-align: center; margin-top: 5%;">
		      		<c:forEach var="listItem" items="${postMap }">
		      		
		      			<div style="position: relative; text-align:left; padding-left: 1%; padding-bottom: 2%; margin-top: 2%; margin-bottom: 10px; border-top: 1px solid #689df2; border-bottom: 2px solid #689df2;">
		      			
		      				<h3 style="font-weight: bold;">${listItem.getValue().getPostHeader() }</h3>
		      				<div style=" position: absolute; right:10%; top:3%;"><a href="edit?id=${listItem.getValue().getId() }" class="btn btn-success">Edit</a></div>
		      				<div style=" position: absolute; right:1%; top:3%;"><a href="delete?id=${listItem.getValue().getId() }" class="btn btn-success">Delete</a></div>
		      				<br />
		      				
		      				<div style="word-break: break-all; word-wrap: break-word;">${listItem.getValue().getPostContent() }</div>
		      			
		      			</div>
		      			
		      			<c:forEach var="comments" items="${commentsMap }">
		      			
		      				<c:if test="${comments.getKey() == listItem.getKey() }">
		      				
		      					<c:forEach var="comment" items="${comments.getValue()}">
		      					
		      						<div style="postion: relative; text-align:left; margin-top: 2%;">
		      							<span style="font-weight: bold;">${comment.getAuthor()} :</span> ${comment.getContent()}
		      							<a href="commentEdit?id=${comment.getId() }&idpost=${listItem.getValue().getId() }">Edit</a>
		      							<a href="commentDelete?id=${comment.getId() }">Delete</a>
		      						</div>
		      						
		      						<c:if test="${editComment.get(1) == listItem.getKey() && editComment.get(0) == comment.getId()}">
		      						<form:form method="post" modelAttribute="comment" action="saveComment?commentID=${editComment.get(0) }">
				
										<div style="width: 90%;" class="form-group">
											<form:input path="content" placeholder="Content" type="text" class="form-control" />
											<form:errors path="content" style="color: red;" class="form-text text-muted" />
										</div>

									<form:button  type="submit" class="btn btn-success">Edit</form:button> <a href="mainBlogView" class="btn btn-success">Cancel</a>
									
									</form:form>
									</c:if>
		      						
		      					</c:forEach>
		      				
		      				</c:if>
		      			
		      			</c:forEach>
		      			
		      			<a href="?post=${listItem.getKey() }" class="btn btn-success" style="margin-top: 2%;">Add comment</a>
		      			
		      			<c:if test="${idComment == listItem.getKey()}">
			      			<div id=${listItem.getKey() } style="display: block; margin-top: 20px;">
			      			
			      				<form:form method="post" modelAttribute="comment" action="addComment">
				
										<div style="width: 30%;" class="form-group">
											<form:input path="author" placeholder="Author" type="text" class="form-control" />
										</div>
										
										<div style="width: 90%;" class="form-group">
											<form:input path="content" placeholder="Content" type="text" class="form-control" />
										</div>
										
										<div class="form-group">
											Author and content fields should have at least 4 characters
										</div>
							
									<form:button  type="submit" class="btn btn-success">Add</form:button> <a href="mainBlogView" class="btn btn-success">Cancel</a>
							
								</form:form>
			      			
			      			</div>
		      			</c:if>
		      			
		      		</c:forEach>
			  </div>
		    </div>
		  </div>
		</div>	
			

	
</body>
</html>