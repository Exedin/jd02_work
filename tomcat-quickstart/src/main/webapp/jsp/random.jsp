<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<h1>
    Random Number
</h1>
<%! String s="Hello "; %>
<% s=s+" world!"; %>
<h1><%=s%></h1>

<h2><%=Math.random()%></h2>

<c:set var="salary" scope="session" value="${500*3}"/>
<c:out value="${salary}"/>

<c:choose>
    <c:when test="${salary>1000}">
        I am rich
    </c:when>
    <c:otherwise>
        I am poor
    </c:otherwise>
</c:choose>


</html>