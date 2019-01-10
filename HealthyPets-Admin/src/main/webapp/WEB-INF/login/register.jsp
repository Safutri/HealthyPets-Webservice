<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>REGISTER ADMIN</title>
</head>
	<form action="/register" method="post">
	<label>Nama </label>
	<input name="nama">
	<br>
	<label>Email </label>
	<input name="email">
	<br>
	<label>Password </label>
	<input name="password" type="password">
	<br>
	<label>Username </label><input name="username">
	<br>
	<button type="submit">Daftar</button>
	</form>
	<p>Sudah punya akun: <a href="/">Masuk</a></p>
</body>
</html>