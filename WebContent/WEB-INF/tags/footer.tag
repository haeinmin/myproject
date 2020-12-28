<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<br />
전체: <%= session.getAttribute("totalCount") %>
오늘: <%= session.getAttribute("todayCount") %>