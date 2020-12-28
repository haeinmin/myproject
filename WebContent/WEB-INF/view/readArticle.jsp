<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
.no-block1 {
	width: 20%;
}

.writer-block1 {
	width: 20%;
}

.title-block1 {
	width: 20%;
}
.type-block1 {
	width: 20%;
}
.no-block2 {
	height: 15px;
}

.writer-block2 {
	height: 15px;
	border-bottom: medium;
	width: 80%;
}

.title-block2 {
	height: 15px;
	width: 80%;
}

.content-block {
	height: 300px;
	width: 100%;
}

.btns{overflow:hidden;clear:both;width:100%} 
.btns .btn-info{float:left}
.btns .btn-secondary{float:right; margin-right: 5px;} 

</style>


<title>Insert title here</title>
</head>
<body>
	<u:navbar></u:navbar>
	<div class="container">
		<div class="row">
			<div class="col-2"></div>
			<div class="col-8">
				<h1>게시글 보기</h1>
				<table class="table">
					<tr>
						<td class="no-block1"><i class="fab fa-slack-hash"></i></td>
						<td class="no-block2">${articleData.article.number }</td>
					</tr>
					<tr>
						<td class="type-block1">지역</td>
						<td class="type-block2">${articleData.article.type }</td>
					</tr>
					<tr>
						<td class="writer-block1"><i class="fas fa-user-edit"></i></td>
						<td class="writer-block2">${articleData.article.writer.name }</td>
					</tr>
					<tr>
						<td class="title-block1">제목</td>
						<td class="title-block2">
						  <c:out value="${articleData.article.title }"></c:out>
						</td>
					</tr>
					<tr>
						<td colspan="2" class="content-block">
						  <u:pre value="${articleData.articleContent }"></u:pre>
						</td>
					</tr>
					<tr>
						<td colspan="2">
					<div class="btns">
						  <c:set var="pageNo" value="${empty param.pageNo ? '1' : param.pageNo }"></c:set> 
						  <button type="button" class="btn btn-info" onclick="location.href='list.do?pageNo=${pageNo }'">글 목록</button>
						 <c:if test="${authUser.userId == articleData.article.writer.id }">
							<button type="button" class="btn btn-secondary" onclick="location.href='delete.do?no=${articleData.article.number }'">삭제</button>
							<button type="button" class="btn btn-secondary" onclick="location.href='modify.do?no=${articleData.article.number }'">수정</button>
						</c:if>
					</div>
						</td>
					</tr>
				</table>
				<u:replyForm articleNo="${articleData.article.number }" />
				<u:listReply />
			</div>
		</div>
	</div>

</body>
</html>