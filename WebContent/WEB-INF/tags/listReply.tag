<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%-- <div>
  <c:forEach items="${replyList }" var="reply">
  <div>
    <input type="text" value="${reply.body }" readonly />
    <span>${reply.memberId }</span>
    <span><font size="2" color="lightgray">${reply.regDate }</font></span>
	<a href="#" onclick="location.href='${root }/reply/modify.do?no=${reply.replyNo }'">수정</a>
    <a href="${root }/reply/delete.do?no=${reply.replyNo }">삭제</a>
  </div>
  </c:forEach>
</div> --%>


<div>
  <c:forEach items="${replyList }" var="reply">
  <tr>
  	<td colspan="5">
  		<div class="text_wrapper">
  		${reply.body }
  		</div>
  	</td>
  	<td colspan="1">
  		${reply.memberId } 
  		<font size="2" color="lightgray">${reply.regDate }</font>
  		<c:if test="${reply.memberId == authUserId }">
	  		<a href="#">수정</a>
	  		<a href="#">삭제</a>
  		</c:if>
  	</td>
  </tr>
  </c:forEach>
</div>