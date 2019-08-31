<!DOCTYPE html>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/main.css" />
    </head>

    <body>
        <h1>Welcome page</h1>
        <h2>Index:</h2>
        <br>
        <a href="${pageContext.request.contextPath}/showSimpleForm">Show Simple Form Page</a>
        <br>
        <a href="${pageContext.request.contextPath}/students/showForm">Show Students Form Page</a>
        <br>
        <a href="${pageContext.request.contextPath}/product/addProduct">Add new Product!</a>
        <br>
        <a href="${pageContext.request.contextPath}/product/findById">Find product by id!</a>
        <br>
        <a href="${pageContext.request.contextPath}/product/getAllProduct">Get all product!</a>
    </body>
</html>