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


	<form action="/pemilik" method="post">
	<br> 
		
	<label>Nama Pemilik</label>
	<label>${pemilik.nama}</label>
	<br> 
	 
	
	<label>Alamat</label>
	<label>${pemilik.alamat}</label>
	<br>
	
	<label>Email</label>
	<label>${pemilik.email}</label>
	<br> 
	</form>


 <br><br>
				<table border="2">
				<tr>
					<td>Pemilik</td>  
					<td>Klinik</td> 
					<td>Alamat</td> 
					<td>Telp</td> 
					<td>Email</td> 	 	 
				</tr>
			 	<c:forEach var="klinik" items="${klinik}">
				<tr> 
					<td>${klinik.pemakai.value.nama}</td>  
					<td>${klinik.nama}</td>
					<td>${klinik.alamat}</td>
					<td>${klinik.telp}</td>
					<td>${klinik.email}</td> 
					<tr>
				</c:forEach>
				</table>


	<form action="/klinik" method="post">
			<br> 
			<input name="id" type=hidden value="${pemilik.id}">
			
			<label>Nama Klinik</label>
			<input name="nama">
			<br>
			
			<label>Alamat</label>
			<input name="alamat">
			<br> 
			
			<label>Email</label>
			<input name="email">
			<br> 
			
			<label>Telp</label>
			<input name="telp">
			<br> 
			
			<label>No.Izin Praktik</label>
			<input name="no_praktik">
			<br>
		  
			<button type="submit">Tambah</button>
	</form>
	
	
<!--  	<c:forEach items="${klinik}" var="klinik">
	<p>Nama Klinik: ${klinik.nama}</p>
	<p>Nama Pemilik: ${klinik.pemakai.value.nama}</p>
	</c:forEach>-->
	 
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