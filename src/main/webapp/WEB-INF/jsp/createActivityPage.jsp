<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="new.activity.page.header" /></title>
    <link rel="icon" href="data:,">
</head>

<body>

<h2><fmt:message key="new.activity.page.header" /></h2><br>

<form class="form" method="post" action="${pageContext.request.contextPath}/activities/create">

    <table border="0">
        <p style='color: red;'>${error_message}</p>
        <c:remove var="error_message" scope="session" />
        <tr>
            <td><label for="activity-name"><fmt:message key="new.activity.name" />:</label></td>
            <td><input required type="text" id="activity-name" name="activity-name" /> </td>
        </tr>
        <tr>
            <td><label for="activity-description"><fmt:message key="new.activity.description" />:</label></td>
            <td><input required type="text" id="activity-description" name="activity-description" /> </td>
        </tr>
        <tr>
            <td><label for="user-id"><fmt:message key="new.activity.user.id" />:</label></td>
            <td>
                <select name="user-id" id="user-id">
                    <c:forEach items="${requestScope.userList}" var="user">
                        <option value="${user.id}">
                               ${user.firstName} ${user.secondName}
                        </option>
                    </c:forEach>
                </select>
            </td>
        </tr>

        <tr>
            <td colspan ="2">
                <fmt:message key="new.activity.submit.button" var="buttonValue" />
                <input type="submit" name="submit" value="${buttonValue}">
            </td>
        </tr>
    </table>
</form>

</body>
</html>
