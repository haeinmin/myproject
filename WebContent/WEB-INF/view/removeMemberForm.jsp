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
	<h1>회원 탈퇴</h1>
	<form action="remove.do" method="post">
	<p>
	비밀번호: <br />
	<input type="password" name="password" id="" />
	<c:if test="${errors.password }">비밀번호를 입력하세요.</c:if>
	<c:if test="${errors.wrongPw }">비밀번호가 일치하지 않습니다.</c:if>
	</p>
	<p>
	탈퇴하시겠습니까? <input type="checkbox" name="confirmDelete" id="" />
	<c:if test="${errors.noCheck }">클릭해주세요.</c:if>
	</p>
	<input type="submit" value="탈퇴" />
	</form>
</body>
</html>