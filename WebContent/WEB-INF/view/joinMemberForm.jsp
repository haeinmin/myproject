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
<form action="join.do" method="post">
<p>
아이디: <br /> <input type="text" name="id" id="" value="${param.id }"/>
<c:if test="${errors.id }">아이디를 입력하세요.</c:if>
<c:if test="${errors.duplicateId }">이미 사용 중인 아이디입니다.</c:if>
</p>
<p>
이름: <br /> <input type="text" name="name" id="" value="${param.name }" />
<c:if test="${errors.name }">이름을 입력하세요.</c:if>
</p>
<p>
비밀번호: <br /> <input type="password" name="password" id="" />
<c:if test="${errors.password }">비밀번호를 입력하세요.</c:if>
</p>
<p>
비밀번호 확인: <br /> <input type="password" name="confirmPassword" id="" />
<c:if test="${errors.confirmPassword }">비밀번호를 재입력하세요.</c:if>
<c:if test="${errors.noMatch }">비밀번호가 일치하지 않습니다.</c:if>
</p>
<input type="submit" value="회원가입" />
</form>
</body>
</html>