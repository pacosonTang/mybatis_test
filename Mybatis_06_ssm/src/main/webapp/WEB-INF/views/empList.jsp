<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 <%
 	String basePath = request.getContextPath();
 %>  
<html>
<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>员工列表</title>
</head>
<body>
	<h1>员工列表</h1>
	<table>
		<tr>
			<th>id</th>
			<th>lastName</th>
			<th>email</th>
			<th>gender</th>		
		</tr>
		<c:forEach items="${EMP_LIST }" var="emp">
			<tr>
				<td>${emp.id} </td>
				<td>${emp.lastName}</td>
				<td>${emp.email}</td>
				<td>${emp.gender}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>
