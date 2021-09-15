<%--
  Created by IntelliJ IDEA.
  User: cloud
  Date: 2021/09/10
  Time: 11:52 오전
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <h1>Do ALL</h1>

    <sec:authorize access="isAnonymous()">  <!--익명의 사용자(로그인하지 않은 사용자)-->
        <a href="/customLogin">Login plz...</a>
    </sec:authorize>
    <sec:authorize access="isAuthenticated()">
        <a href="/logout">Logout</a>
    </sec:authorize>

</body>
</html>
