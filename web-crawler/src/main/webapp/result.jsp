<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Result page</title>
</head>
<body>
<div width="100%">
<table style="width:80%" table border="1" width="50%" cellpadding="5" align= "center">
    <tr>
        <th>URL</th>
        <c:forEach items="${terms}" var="term">
        <th>${term}</th>
        </c:forEach>
        <th>TOTAL</th>
    </tr>
    <c:forEach items="${result.resultItemDtoList}" var="item">
    <tr>
        <td>${item.searchUrl}</td>
        <c:forEach items="${terms}" var="term">
        <td>${item.termCountMap[term]}</td>
        </c:forEach>
        <td>XXX</td>
    </tr>
    </c:forEach>
</table>
<a href="/web-crawler">Back</a>
</div>
</body>
</html>