<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>list.jsp</title>
</head>
<body>
<h1>[ 회원 목록 ]</h1>

<c:forEach var="member" items="${list }">
	ID : ${member.id }
	이름 : ${member.name }
	<a href="delete?id=${member.id }">삭제</a>
	<br>
</c:forEach>

</body>
</html>