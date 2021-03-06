<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page session="true" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Ubah Pemilik Klinik Hewan</title>
</head>

<body>
	<h1><a href="/home">Home</a></h1> 
	 
	<form action="/perbarui-klinik" method="post">
	<br> 
		
	<label>Nama</label>
	<input name="nama" value="${klinik.nama}">
	<br> 
	  
	<label>Alamat</label>
	<input name="alamat" value="${klinik.alamat}">
	<br>
	
	<label>Email</label>
	<input name="email" value="${klinik.email}">
	<br> 
	
	<label>No.Izin Praktik</label>
	<input name="identitas" value="${klinik.identitas}">
	<br> 
	
	<label>Telp</label>
	<input name="telp" value="${klinik.telp}">
	<br> 
	
	<button type="submit">Perbarui</button>
	</form>
	 
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