<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ attribute name="articleNo" type="java.lang.Integer"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
<c:if test="${not empty sessionScope.authUser }">
	<div>
		<form action="${root }/reply/add.do" method="post">
			<input type="number" value="${articleNo }" name="no" id="" hidden />
			<input type="text" name="body" id="" /> <input type="submit"
				value="등록" />
		</form>
	</div>
</c:if> --%>


<c:if test="${not empty sessionScope.authUser }">
	<form action="${root }/reply/add.do" method="post">
		<input type="number" value="${articleNo }" name="no" id="" hidden />
		<table>
			<tr bgcolor="#F5F5F5">
				<!-- 아이디-->
				<td width="150">
					<div>${authUserId}</div>
				</td>
				<!-- 본문 작성-->
				<td width="550">
					<div>
						<textarea name="body" rows="4" cols="70"></textarea>
					</div>
				</td>
				<!-- 댓글 등록 버튼 -->
				<td width="100">
					<div id="btn" style="text-align: center;">
						<input type="submit" value="등록" />
					</div>
				</td>
			</tr>
		</table>
	</form>
</c:if>
