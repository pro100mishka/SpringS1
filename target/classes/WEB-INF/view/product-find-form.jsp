<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>
    <body>
        <div class="header-panel">
            <a href="${pageContext.request.contextPath}/">Return to Index</a>
        </div>
        <hr>
        <div class="add-product">
            <form action="findByIdMethod" method="GET">
                <input name="id" placeholder="Enter product id!" />
                <input type="submit" />
            </form>
        </div>
    </body>
</html>
