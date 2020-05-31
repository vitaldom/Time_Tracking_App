<%@ include file="../includes/languageChangeBlock.jsp"%>
<%@ include file="../includes/logoutButton.jsp"%>

<html>
<head>
    <title>Tax Declaration app - <fmt:message key="user.activity.list.page.header" /></title>
    <link rel="icon" href="data:,">
</head>
<body>

<h2><fmt:message key="user.activity.list.page.header" /></h2>

<div>
    <h4><fmt:message key="user.menu.logged.as" /></h4>
    <p><c:out value="${sessionScope.user.firstName}" />&nbsp;<c:out value="${sessionScope.user.secondName}" /></p>
</div>
<hr>
<p style="color: green; font-weight: 700">${service_message}</p>
<c:remove var="service_message" scope="session" />

<table style="border: 1px solid;">
    <tr style="background-color: #dadada; height: 30px;">
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.id" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.name" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.description" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.admin" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.time.tracked" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.view" /></th>
        <th style="min-width: 150px;"><fmt:message key="user.activities.table.status" /></th>
    </tr>

    <c:forEach items="${sessionScope.activityList}" var="activity" varStatus="varStatus">
        <tr style="padding-bottom: 20px; text-align: center">
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${activity.id}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${activity.name}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${activity.description}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${activity.administrator.firstName}" />
                <c:out value="${activity.administrator.secondName}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:out value="${activity.timeTracked}" />
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <form action="${pageContext.request.contextPath}/user/activity" method="POST">
                    <input type="hidden" name="activityIndex" value="${varStatus.index}">
                    <button type="submit" id="button-submit"><fmt:message key="user.activities.track.button" /></button>
                </form>
            </td>
            <td style="padding: 15px 0; border: 1px solid;">
                <c:choose>
                    <c:when test="${activity.status == 'DELETION_REQUESTED'}">
                        <fmt:message key="status.deletion.requested" />
                    </c:when>
                    <c:when test="${activity.status == 'IN_PROCESS'}">
                        <form action="${pageContext.request.contextPath}/user/requestActivityDeletion" method="POST">
                            <input type="hidden" name="activityId" value="${activity.id}">
                            <button type="submit" ><fmt:message key="user.activities.delete.request.button" /></button>
                        </form>
                    </c:when>
<%--                    <c:otherwise> TODO add option or delete--%>
<%--                        ...--%>
<%--                    </c:otherwise>--%>
                </c:choose>
            </td>

        </tr>
    </c:forEach>
</table>

</body>
</html>
