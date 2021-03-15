<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>read_data.jsp</title>
</head>
<body>
	<c:forEach var="obj" items="${list }"><!--  for(List obj:list) -->
	
	${obj.data1 },
	${obj.data2 },
	${obj.data3 } <br />
	
	
	</c:forEach>
</body>
</html>