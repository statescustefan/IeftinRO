<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Spring MVC Form Handling</title>
</head>
<body>
f
<h2>Student Information</h2>
<form:form method = "GET" action="/">
    <table>
        <tr>
            <td><form:label path = "name">Search</form:label></td>
            <td><form:input path = "name" /></td>
        </tr>
        <td colspan = "2">
            <input type = "submit" value = "Submit"/>
        </td>
        </tr>
    </table>
</form:form>
</body>
</html>