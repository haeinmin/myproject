<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-3"></div>
			<div class="col-6">
				<h1>게시글 목록</h1>
				<div class="list-container">
					<table>
						<thead>
							<tr>
								<th>#</th>
								<th>지역</th>
								<th>제목</th>
								<th>작성자</th>
								<th>조회수</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="article" items="${articlePage.content }">
								<tr>
									<td>${article.number }</td>
									<td>${article.type }</td>
									<td><a
										href="${root }/article/read.do?no=${article.number }&pageNo=${articlePage.currentPage}">
											<c:out value="${article.title }" />
									</a></td>
									<td>${article.writer.name }</td>
									<td>${article.readCount }</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<div
						class="mt-5 pagenation-container d-flex justify-content-center">
						<nav aria-label="Page navigation example">
							<ul class="pagination">
								<c:if test="${articlePage.startPage > 5}">
									<li class="page-item"><a class="page-link"
										href="${root }/article/list.do?pageNo=${articlePage.startPage - 5 }">Previous</a></li>
								</c:if>

								<c:forEach begin="${articlePage.startPage }"
									end="${articlePage.endPage }" var="pNo">
									<li class="page-item"><a class="page-link"
										href="${root }/article/list.do?pageNo=${pNo}">${pNo }</a></li>

								</c:forEach>
								<c:if test="${articlePage.endPage < articlePage.totalPages }">
									<li class="page-item"><a class="page-link"
										href="${root }/article/list.do?pageNo=${articlePage.startPage + 5 }">Next</a></li>
								</c:if>
							</ul>
						</nav>
					</div>


				</div>
			</div>
		</div>
	</div>
</body>
</html>