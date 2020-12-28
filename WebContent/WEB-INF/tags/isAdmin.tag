<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%if(session.getAttribute("authUserId")!=null && 
session.getAttribute("authUserId").equals("admin")) {%>
<jsp:doBody />

<%}%>
 