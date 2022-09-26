<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <title>INDEX</title>
</head>
<body>
<a href="${pageContext.request.contextPath}/index">index</a>
<form method="get" action="${pageContext.request.contextPath}/test">
  username:<input name="username" type="text"/>
  <input type="submit" value="提交"/>
</form>
message:${message}
</body>
</html>
