<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>

<html>
    <body>
        <div class="header-panel">
            <a href="${pageContext.request.contextPath}/">Return to Index</a>
        </div>
        <div class="all-product">
            <h2>Product list:</h2>
            <%--@elvariable id="productList" type="java.util.List"--%>
            <c:forEach var="product" items="${productList}">
                <li>
                    Product id: ${product.id}
                    Product title: ${product.title}
                    Product cost: ${product.cost}
                </li>
            </c:forEach>
        </div>
    </body>
</html>