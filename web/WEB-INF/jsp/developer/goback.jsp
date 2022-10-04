<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="common/header.jsp"%>
<html>
<head>
    <title>ERROR! GO BACK!</title>
</head>
<body>
${fileUploadError }
<button type="button" class="btn btn-primary" id="back" onclick="window.history.back()">返回</button>
</body>
<%@include file="common/footer.jsp"%>
</html>
