
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <title>METEO</title>
    </head>
    <body>

        <ul>
            <c:forEach items="${meteoSelectionnes}" var="select">
                <li>${select}</li>
            </c:forEach>
        </ul>
        <form method="post">
            <select name="meteo">
                <c:forEach items="${options}" var="opt">
                    <c:choose>
                        <c:when test="${opt.key == lastUsed}">
                            <option selected value="${opt.key}">${opt.value}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${opt.key}">${opt.value}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
            <button type="submit">Valider</button>
        </form>

    </body>
</html>
