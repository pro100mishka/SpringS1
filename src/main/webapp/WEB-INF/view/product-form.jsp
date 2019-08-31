<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <div class="header-panel">
           <a href="${pageContext.request.contextPath}/">Return to Index</a>
        </div>
        <div class="add-product">
        <form:form action="processForm" modelAttribute="product">
            <hr>
            ID: <form:input path="id" />
            <br>
            Title: <form:input path="title" />
            <br>
            Cost: <form:input path="cost"/>
            <input type="submit" value="Submit" />
        </form:form>
        </div>
    </body>
</html>
