<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>input_data.jsp</title>
</head>
<body> <!-- form을 만들어서 입력 데이터를 받아 받아서 컨트롤러 가서 input_pro를 찾아-->
	<form action="input_pro" method="post">
	data1 : <input type="text" name="data1"/><br /> <!-- 오라클 이름이랑 꼭꼭 맞춰줘야됨 dataBean 이랑 -->
	data2 : <input type="text" name="data2"/><br /> <!-- 오라클 이름이랑 꼭꼭 맞춰줘야됨 -->
	data3 : <input type="text" name="data3"/><br /> <!-- 오라클 이름이랑 꼭꼭 맞춰줘야됨 -->
	
	<button type="submit">확인</button>
	</form>
</body>
</html>