<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>LOGIN ADMIN</title>
</head>
	<form action="/" method="post">
	
	<label>Email </label>
	<input name="email">
	<br>
	<label>Password </label>
	<input name="password" type="password">
	<br>
	<button type="submit">Masuk</button>
	</form>
	<p>Belum punya akun: <a href="/register">Daftar</a></p> 
	<a href="${googleLoginURL}" class="text-center" id="googleLogin">Login Dengan Akun Google</a>
</body>
</html>