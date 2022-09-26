<jsp:useBean id="user" scope="request" type="com.newSSM.pojo.User"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>SUCCESS</title>
</head>
<body>
你输入的是：${username}
${user.toString()}
</body>
</html>
