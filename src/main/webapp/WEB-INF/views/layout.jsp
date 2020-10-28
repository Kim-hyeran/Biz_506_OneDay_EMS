<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="rootPath" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name='viewport' content='width=device-width, initial-scale=1'>
<title>Spring EMS</title>
<script src="https://kit.fontawesome.com/a076d05399.js"></script>
<style>
* {
    font-family: 'Roboto', sans-serif;
    color: #454545;
    box-sizing: border-box;
    margin: 0;
    padding: 0;
}
</style>
</head>
<body>
	<tiles:insertAttribute name="header"></tiles:insertAttribute>
	<tiles:insertAttribute name="nav"></tiles:insertAttribute>
	<section id="content">
		<tiles:insertAttribute name="content"></tiles:insertAttribute>
	</section>
	<tiles:insertAttribute name="footer"></tiles:insertAttribute>
</body>
</html>