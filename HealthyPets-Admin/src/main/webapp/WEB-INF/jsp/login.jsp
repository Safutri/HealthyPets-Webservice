<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>Admin HealthyPets</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
<!--===============================================================================================-->	
 <!--===============================================================================================-->
 <!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="LoginCSS/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="LoginCSS/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="LoginCSS/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="LoginCSS/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
 <!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="LoginCSS/css/util.css">
	<link rel="stylesheet" type="text/css" href="LoginCSS/css/main.css">
<!--===============================================================================================-->
</head>
<body>
	
	<div class="limiter">
		<div class="container-login100" style="background-image: url('LoginCSS/se.jpg');">
			<div>
				<img src="LoginCSS/ddda.png" alt="AVATAR">
			</div>
				<div class="wrap-login100 p-t-190 p-b-30">

				<form class="login100-form validate-form" action="/pemilik" method="POST">
					<span class="login100-form-title p-t-20 p-b-45">
						Welcome to HealthyPets	
					</span>
				
					<div class="wrap-input100 validate-input m-b-10" data-validate = "Email is required">
						<input class="input100" type="email" name="email" placeholder="Email">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-user"></i>
						</span>
					</div>

					<div class="wrap-input100 validate-input m-b-10" data-validate = "Password is required">
						<input class="input100" type="password" name="password" placeholder="Password">
						<span class="focus-input100"></span>
						<span class="symbol-input100">
							<i class="fa fa-lock"></i>
						</span>
					</div>

					<div class="container-login100-form-btn p-t-10">
						<button class="login100-form-btn" type="submit">Login</button> 
					</div>
 
 
				</form>
			</div>
		</div>
	</div> 
</body>
</html>