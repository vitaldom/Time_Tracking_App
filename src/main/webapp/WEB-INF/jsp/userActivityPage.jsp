<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Time Tracking App - <fmt:message key="user.activity.page.header" /></title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="user.activity.page.header" /></h2>

<div>
    <h4><fmt:message key="user.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>

<%--<h1><c:out value="${sessionScope.activityToUpdate.name}"/></h1>--%>

<table border="0">
    <tr>
        <td><fmt:message key="user.activities.table.id" />:</td>
        <td><c:out value="${sessionScope.activityToUpdate.id}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="user.activities.table.name" />:</td>
        <td><c:out value="${sessionScope.activityToUpdate.name}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="user.activities.table.description" />:</td>
        <td><c:out value="${sessionScope.activityToUpdate.description}"/> </td>
    </tr>
    <tr>
        <td><fmt:message key="user.activities.table.admin" />:</td>
        <td>
            <c:out value="${sessionScope.activityToUpdate.administrator.firstName}" />
            <c:out value="${sessionScope.activityToUpdate.administrator.secondName}" />
        </td>
    </tr>
    <tr>
        <td><fmt:message key="user.activities.table.time.tracked" />:</td>
        <td><c:out value="${sessionScope.activityToUpdate.timeTracked}"/> </td>
    </tr>
</table>
<br>
<hr>

<form class="form" method="post" action="${pageContext.request.contextPath}/user/track">
    <table border="0">
<%--        <p style='color: red;'>${error_message}</p>TODO add error message--%>
<%--        <c:remove var="error_message" scope="session" />--%>
        <tr>
            <td><label for="trackTime"><fmt:message key="user.activity.track.time" />:</label></td>
            <td><input required type="number" id="trackTime" name="timeToTrack" value="" min="1" max="12"/></td>
        </tr>
        <tr>
            <td colspan ="2">
                <input type="hidden" name="activityId" value="${sessionScope.activityToUpdate.id}">
                <input type="hidden" name="timeTracked" value="${sessionScope.activityToUpdate.timeTracked}">

<%--                <fmt:message key="user.activity.track.time.button" var="buttonValue" />--%>
<%--                <input type="submit" name="submit" value="${buttonValue}">--%>
                <button type="submit"><fmt:message key="user.activity.track.time.button" /></button>
            </td>
        </tr>
    </table>
</form>


</body>
</html>
