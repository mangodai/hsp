<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
	<div class="header">
		<div class="container">
			<div class="header-grid">
				<div class="header-grid-left animated wow slideInLeft" data-wow-delay=".5s">
					<ul>
						<li><i class="glyphicon glyphicon-envelope" aria-hidden="true"></i>965557340@qq.com</li>
						<li><i class="glyphicon glyphicon-earphone" aria-hidden="true"></i>182 <span>7023</span> 5552</li>
					<c:choose>
					<c:when test="${empty sessionScope.session_user }">
						<li><i class="glyphicon glyphicon-log-in" aria-hidden="true"></i><a href="login.jsp">Login</a></li>
						<li><i class="glyphicon glyphicon-book" aria-hidden="true"></i><a href="register.jsp">Register</a></li>
					</c:when>
					<c:otherwise>
						<li><i class="glyphicon glyphicon-user" aria-hidden="true"></i>${sessionScope.session_user.user_name }</li>
						<li><i class="glyphicon glyphicon-shopping-cart" aria-hidden="true"></i><a href="checkout.jsp">我的购物车</a></li>
						<li><i class="glyphicon glyphicon-log-out" aria-hidden="true"></i><a href="<c:url value='/UserServlet?method=quit' />" >退出登录</a></li>
					</c:otherwise>
					</c:choose>
					<c:if test="${sessionScope.session_user.user_privilege eq 'admin'}">
						<li><i class="glyphicon glyphicon-transfer" aria-hidden="true"></i><a href="<c:url value='/admin/index.jsp'/>">进入后台界面</a></li>
					</c:if>
					</ul>
				</div>
				<div class="header-grid-right animated wow slideInRight" data-wow-delay=".5s">
					<ul class="social-icons">
						<li><a href="#" class="facebook"></a></li>
						<li><a href="#" class="twitter"></a></li>
						<li><a href="#" class="g"></a></li>
						<li><a href="#" class="instagram"></a></li>
					</ul>
				</div>
				<div class="clearfix"> </div>
			</div>
			<div class="logo-nav">
				<div class="logo-nav-left animated wow zoomIn" data-wow-delay=".5s">
					<h1><a href="index.jsp">Hospital <span>By MangoDai</span></a></h1>
				</div>
				<div class="logo-nav-left1">
					<nav class="navbar navbar-default">
					<!-- Brand and toggle get grouped for better mobile display -->
					<div class="navbar-header nav_2">
						<button type="button" class="navbar-toggle collapsed navbar-toggle1" data-toggle="collapse" data-target="#bs-megadropdown-tabs">
							<span class="sr-only">Toggle navigation</span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
							<span class="icon-bar"></span>
						</button>
					</div>
					<div class="collapse navbar-collapse" id="bs-megadropdown-tabs">
						<ul class="nav navbar-nav">
							<li><a href="index.jsp">主页</a></li>	
							<!-- Mega Menu -->
						<!-- 	<li><a href="products.jsp">优秀技术</a></li> -->
							<li><a href="<c:url value='/OrderServlet?method=plan' />">查看安排</a></li>
							<li><a href="<c:url value='/CureServlet?method=findAll' /> " >添加安排</a></li>
							<li><a href="mail.jsp">联系我们</a></li>
						</ul>
					</div>
					</nav>
				</div>
<!-- 				<div class="logo-nav-right">
					<div class="search-box">
						<div id="sb-search" class="sb-search">
							<form>
								<input class="sb-search-input" placeholder="Enter your search term..." type="search" id="search">
								<input class="sb-search-submit" type="submit" value="">
								<span class="sb-icon-search"> </span>
							</form>
						</div>
					</div>
						search-scripts
						<script src="js/classie.js"></script>
						<script src="js/uisearch.js"></script>
							<script>
								new UISearch( document.getElementById( 'sb-search' ) );
							</script>
						//search-scripts
				</div> -->
				<div class="header-right">
					<div class="cart box_1">
						<a href="checkout.jsp">
							<img src="images/bag.png" alt="" />
						</a>
<!-- 						<p><a href="javascript:;" class="simpleCart_empty">Empty Cart</a></p> -->
						<div class="clearfix"> </div>
					</div>	
				</div>
				<div class="clearfix"> </div>
			</div>
		</div>
	</div>
	
<!-- breadcrumbs -->
	<div class="breadcrumbs">
		<div class="container">
			<ol class="breadcrumb breadcrumb1 animated wow slideInLeft" data-wow-delay=".5s">
				<li><a href="index.jsp"><span class="glyphicon glyphicon-home" aria-hidden="true"></span>Home</a></li>
				<li class="active"><script>document.write(document.title);</script></li>
			</ol>
		</div>
	</div>
<!-- //breadcrumbs -->