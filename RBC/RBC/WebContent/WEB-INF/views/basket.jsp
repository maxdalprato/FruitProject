<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<style>
div {

    margin-left: 20%;
    margin-right: 20%;
    border-style: double;
}

</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body><div>
<center><h2>You're Basket:</h2>
<c:forEach var="eachItem" items="${basketFruit}">

Fruit: ${eachItem.key.fruitName}
<br> 
Price of fruit: ${eachItem.key.price}
<br>
Quantity: ${eachItem.value} 
<br>

<br>
</c:forEach>

Total Price: ${totalPrice} <br><br>
<center>	<B>${requestScope.message}</B> </center>
<a href='${pageContext.request.contextPath}/listFruit'>Go back to fruits</a> <br><br>

<a href='${pageContext.request.contextPath}/checkOut'>CheckOut</a> <br><br>
</div>
</body>
</html>