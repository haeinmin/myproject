<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="u" tagdir="/WEB-INF/tags" %>
<div class="container mb-3">
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <a class="navbar-brand" href="${root }/index.jsp"><span style="font-family: Unicorn pop; font-size:40px; color:#E91E63" > PUPPY'S</span></a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
    <ul class="navbar-nav mr-auto">
      <li class="nav-item active">
        <a class="nav-link" href="${root }/index.jsp"><i class="fas fa-home"></i> <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${root }/article/list.do"><i class="fas fa-list"></i> 글 목록</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="${root }/article/write.do"><i class="fas fa-pencil-alt"></i> 글 작성하기</a>
      </li>
    </ul>
    <!-- 로그인 안 했을 때만 나타나도록 태그로 감싸 -->
    <u:isNotLogin>
    <ul class="navbar-nav">
    	<li class="nav-item"><a href="${root }/join.do" class="nav-link">회원가입</a></li>
    	<li class="nav-item"><a href="${root }/login.do" class="nav-link">로그인</a></li>
    </ul>
    </u:isNotLogin>
    <u:isLogin>
    <ul class="navbar-nav">
    	<u:isAdmin>
    	<li class="nav-item"><a href="${root }/memberList.do" class="nav-link">회원목록</a></li>
    	</u:isAdmin>
    	<li class="nav-item"><a href="${root }/changePwd.do" class="nav-link">비밀번호 변경</a></li>
    	<li class="nav-item"><a href="${root }/logout.do" class="nav-link">로그아웃</a></li>
    	<li class="nav-item"><a href="${root }/removeMember.do" class="nav-link">회원 탈퇴</a></li>
    </ul>
    </u:isLogin>
  </div>
</nav>
</div>