<%--
/**
 * User: Piterskuy
 * Date: 14.09.2015
 */
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Meal list</title>
</head>
<body>
<h2><a href="${pageContext.request.contextPath}" target="_self">>На заглавную</a></h2>

<h2>Meal list</h2>

<table style="border-color: #ffcc99">
    <thead>
    <tr>
        <th>Дата|Время</th>
        <th>Описание</th>
        <th>Калории</th>
    </tr>
    </thead>
    <jsp:useBean id="mealList" scope="request" type="java.util.List"/>
    <c:forEach items="${mealList}" var="meal">

        <c:choose>
            <c:when test="${meal.exceed==\"true\"}">
                <tr style="background-color: #ff9999">
            </c:when>
            <c:otherwise>
                <tr style="background-color: #ddffdd">
            </c:otherwise>
        </c:choose>
        <td>${meal.dateTime}</td>
        <td>${meal.description}</td>
        <td>${meal.calories}</td>
        </tr>
    </c:forEach>
</table>

<%--Добавление записей--%>
<%--<form action="meal.jsp" method="POST">--%>
    <%--First Name: <input type="text" name="first_name">--%>
    <%--<br />--%>
    <%--Last Name: <input type="text" name="last_name" />--%>
    <%--<input type="submit" value="Submit" />--%>
<%--</form>--%>

</body>
</html>
