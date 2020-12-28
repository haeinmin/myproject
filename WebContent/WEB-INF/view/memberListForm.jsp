<%@page import="java.util.ArrayList"%>
<%@page import = "member.model.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<% ArrayList<Member> memberList = (ArrayList<Member>) request.getAttribute("memberList"); %>
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
<h1>전체 회원 목록</h1>
<table>
<tr>
<td>아이디</td>
<td>이름</td>
<td>비밀번호</td>
<td>가입일</td>
</tr>
<%for (Member member : memberList) { %>
<tr>
	<td><%=member.getId() %></td>
	<td><%=member.getPassword() %></td>
	<td><%=member.getName() %></td>
	<td><%=member.getRegDate() %></td>
</tr>
<%} %>
</table>
</body>
</html>