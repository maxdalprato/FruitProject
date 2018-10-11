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
<body>
<div>
<br>
<c:forEach var="eachItem" items="${listFruit}">

<center>Name of fruit: ${eachItem.fruitName } </center>
<center> Cost of fruit: ${eachItem.price } </center> <br>



		
<center>	<sf:form action="addItemToBasket/${eachItem.fruitName}">
   	<input type="submit" name="commit" value="Add" />
   	</sf:form>
   	<sf:form action="removeItemFromBasket/${eachItem.fruitName}">
   	<input type="submit" name="commit" value="Remove" />
   	</sf:form> </center>
   	<br>
		

</c:forEach>

</div>
</body>
</html>