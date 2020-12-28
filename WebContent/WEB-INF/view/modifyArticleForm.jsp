<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
  <script src="https://kit.fontawesome.com/a076d05399.js"></script>
<title>Insert title here</title>
</head>
<body>
<form action="modify.do" method="post">
<input type="text" name="no" value="${modReq.articleNumber }" hidden />
<p>
  no: <br />
  ${modReq.articleNumber }
</p>
<p>
  title: <br />
  <input type="text" name="title" value="${modReq.title }" />
  <c:if test="${errors.title }">insert title here</c:if>
</p>
<p>
  content: <br />
  <textarea name="content" id="" cols="30" rows="5">${modReq.content}</textarea>
</p>
<input type="submit" value="go" />

</form>
</body>
</html>