<%--
  Created by IntelliJ IDEA.
  User: 25440
  Date: 2022/9/30
  Time: 1:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Jumping...</title>
</head>
<body>
<form method="post" action="${map.pageNum}">
    <label>
        <input type="hidden" name="querySoftwareName" value="${map.softwareName}"/>
        <input type="hidden" name="queryFlatformId" value="${map.flatformId}"/>
        <input type="hidden" name="queryCategoryLevel1" value="${map.categoryLevel1}"/>
        <input type="hidden" name="queryCategoryLevel2" value="${map.categoryLevel2}"/>
        <input type="hidden" name="queryCategoryLevel3" value="${map.categoryLevel3}"/>
    </label>
</form>

</body>
<script>
    window.onload = function (){
        document.forms[0].submit();
    }
</script>
</html>
