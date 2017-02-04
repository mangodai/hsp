<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    
<!DOCTYPE html>
<html>
<head>
<title>Forgot</title>
<base href="<%=basePath%>">
<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="Best Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } </script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- //js -->
<!-- cart -->
	<script src="js/simpleCart.min.js"> </script>
<!-- cart -->
<link rel="stylesheet" type="text/css" href="css/jquery-ui.css">
<!-- for bootstrap working -->
	<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<link href='css/fonts1.css' rel='stylesheet' type='text/css'>
<link href='css/fonts2.css' rel='stylesheet' type='text/css'>
<!-- animation-effect -->
<link href="css/animate.min.css" rel="stylesheet"> 
<script src="js/wow.min.js"></script>
<script>
 new WOW().init();
</script>
<!-- //animation-effect -->
</head>
<body>
<!-- header -->
<%@ include file="common/header.jsp" %>
<!-- //header -->

<!-- register -->
	<div class="register">
		<div class="container">
			<h3 class="animated wow zoomIn" data-wow-delay=".5s">Forgot Password</h3>
			<p class="est animated wow zoomIn" data-wow-delay=".5s">听说你忘记了密码,那么来试试这个</p>
			<div class="login-form-grids">
			<h6 class="animated wow slideInUp" data-wow-delay=".5s">${errors }</h6>
				<form class="animated wow slideInUp" data-wow-delay=".5s" action="<c:url value='/UserServlet' />" method="post">
					<input type="hidden" name="method" value="forgot"/>				
					<input type="email" name="email" placeholder="Email Address" >
					<input type="password" name="user_password" placeholder="Password" >
					<input type="password" placeholder="Password Confirmation"  >
					<input type="text" id="verify_code" placeholder="验证码" onblur="checkVerify()"><img alt="验证码" src="<c:url value='/UserServlet?method=getVerify' />">
					<input type="submit" value="sure">
				</form>
			</div>
			<div class="register-home animated wow slideInUp" data-wow-delay=".5s">
				<a href="index.jsp">Home</a>
			</div>
		</div>
	</div>
<!-- //register -->
<!-- footer -->
<%@ include file="common/footer.jsp" %>
<!-- //footer -->
</body>
</html>
 