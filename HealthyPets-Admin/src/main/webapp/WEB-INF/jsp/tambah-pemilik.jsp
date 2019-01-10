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
					<td>Nama</td>  
					<td>Email</td> 
					<td>Alamat</td>
					<td>No. Identitas (KTP)</td>
					<td>Telp</td>
					<td>Action</td> 
					 
				</tr>
			 	<c:forEach var="pemilik" items="${pemilik}">
				<tr>
					<td><a href="/klinik?id=${pemilik.id}">${pemilik.nama}</a></td>  
					<td>${pemilik.email}</td>
					<td>${pemilik.alamat}</td> 
					<td>${pemilik.identitas}</td> 
					<td>${pemilik.telp}</td> 
					 <td><a href="/perbarui?id=${pemilik.id}">Perbarui</a></td> 
					<tr>
				</c:forEach>
				</table>


	<form action="/pemilik" method="post">
	<br> 
		
	<label>Nama Pemilik</label>
	<input name="nama">
	<br> 
	 
	
	<label>Alamat</label>
	<input name="alamat">
	<br>
	
	<label>Email</label>
	<input name="email">
	<br>
	
	<label>No.Identitas(KTP)</label>
	<input name="identitas">
	<br>
	
	<label>Telp</label>
	<input name="telp">
	<br> 
	<button type="submit">Tambah</button>
	</form>
	

	
	
	
	<c:forEach items="${klinik}" var="klinik">
	<p>Nama Klinik: ${klinik.nama}</p>
	<p>Nama Pemilik: ${klinik.pemakai.value.nama}</p>
	</c:forEach>
	<br>
	<br>
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