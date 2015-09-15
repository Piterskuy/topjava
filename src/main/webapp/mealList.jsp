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
        <tr>
        <c:choose>
            <c:when test="${meal.exceed==\"true\"}">
                <td style="background-color: #ffcc99">${meal.dateTime}</td>
                <td style="background-color: #ffcc99">${meal.description}</td>
                <td style="background-color: #ffcc99">${meal.calories}</td>
            </c:when>
            <c:otherwise>
                <td>${meal.dateTime}</td>
                <td>${meal.description}</td>
                <td>${meal.calories}</td>
            </c:otherwise>
        </c:choose>
        </tr>
    </c:forEach>
</table>
</body>
</html>
