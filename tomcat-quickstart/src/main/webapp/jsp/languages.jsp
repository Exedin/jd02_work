<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<ul>
<c:forEach items="${languages}" var="lang">
    <li><c:out value="${lang}"/></li>
</c:forEach>
</ul>