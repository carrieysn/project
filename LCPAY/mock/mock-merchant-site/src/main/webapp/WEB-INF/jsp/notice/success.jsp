<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: sweet
  Date: 16-9-22
  Time: 上午11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>支付成功</title>
</head>
<body>
    <h2>支付成功</h2>

    <c:forEach items="${form.entrySet()}" var="entry">
        <p>
            <span>Key: </span>${entry.getKey()}
            <span>Value: </span>${entry.getValue()}
        </p>
    </c:forEach>

</body>
</html>
