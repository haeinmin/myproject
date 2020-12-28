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
<h1>글 쓰기</h1>
<form action="write.do" method="post">
분류:
<select class="custom-select" name="type">
  <option selected>선택</option>
  <option value="서울">검사후기</option>
  <option value="경기">자가격리일상</option>
  <option value="인천">관련기사</option>
</select>
<p>
제목: <br />
<input type="text" name="title" id="" />
</p>
<textarea name="content" id="" cols="30" rows="5"></textarea>
<input type="submit" value="등록" />
</form>
</body>
</html>