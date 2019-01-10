<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form Daftar Pemilik Klinik Hewan</title>
</head>

<body>
	<h1><a href="/home">Home</a></h1> 
 
 <br><br>
				<table border="2">
				<tr>
					<td>Pemilik</td>  
					<td>Klinik</td> 
					<td>Alamat</td> 
					<td>Telp</td> 
					<td>Email</td> 
					<td>Perbarui</td> 
					 					 
				</tr>
			 	<c:forEach var="klinik" items="${klinik}">
				<tr> 
					<td>${klinik.pemakai.value.nama}</td>
					<td>${klinik.nama}</td>
					<td>${klinik.alamat}</td>
					<td>${klinik.telp}</td>
					<td>${klinik.email}</td> 
					<td><a href="/perbarui-klinik?id=${klinik.id}">Perbarui</a></td> 
					<tr>
				</c:forEach>
				</table>
 
 
	<c:choose>
		<c:when test="${not empty emailG}">
				<p>${emailG}</p>
		</c:when>
	</c:choose>
	<c:choose>
		<c:when test="${not empty googleLogoutURL}">
			<a href="${googleLogoutURL}">Keluar dari App (Google)</a>
		</c:when>
		<c:otherwise>
				<a href="/logout">Logout</a>
		</c:otherwise>
	</c:choose>
</body>
</html>