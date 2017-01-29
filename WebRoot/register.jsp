<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

    
<!DOCTYPE html>
<html>
<head>
<title>Register</title>
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
			<h3 class="animated wow zoomIn" data-wow-delay=".5s">Register Here</h3>
			<p class="est animated wow zoomIn" data-wow-delay=".5s">通过这里,你可以注册一个账号,注意你的邮箱号是唯一的</p>
			<div class="login-form-grids">
				<h6 class="animated wow slideInUp" data-wow-delay=".5s">描述注册登录信息</h6>
				<form class="animated wow slideInUp" data-wow-delay=".5s" action="<c:url value='/UserServlet'/>" method="post"  onsubmit="return checkall()">
					<input type="hidden" name="method" value="addUser"/>
					<input type="text" id="user_name" name="user_name" placeholder="Your Name..." >${errors.user_name }
					<input type="email" id="email" name="email" placeholder="Email Address" >${errors.email }
					<input type="password" id="user_password" name="user_password" placeholder="Password"  >${errors.user_password }
					<input type="password" placeholder="Password Confirmation"  >
					<script type="text/javascript">
					function checkall(){
						var name = document.getElementById("user_name");
						var email = document.getElementById("email");
						var password = document.getElementById("user_password");
						if(name.value=="" ||email.value=="" || password.value==""){
						    alert("不可为空");
						    return false;
						}
					}
					</script>
					<div class="register-check-box">
						<div class="check">
							<label class="checkbox"><input type="checkbox" name="checkbox"><i> </i>I accept the terms and conditions</label>
						</div>
					</div>
					<input type="submit" value="Register">
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
 