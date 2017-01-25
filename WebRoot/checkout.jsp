<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html>
<head>
    <base href="<%=basePath%>">
<title>Checkout</title>
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
<!-- datetimepicker -->
<link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
<script src="js/jquery.js"></script>
<script src="js/jquery.datetimepicker.full.js"></script>
<!-- //datetimepicker -->
</head>
<body>
<!-- header -->
<%@ include file="common/header.jsp" %>
<!-- //header -->

<!-- checkout -->
	<div class="checkout">
		<div class="container">
			<h3 class="animated wow slideInLeft" data-wow-delay=".5s">你的购物车包含: <span>${fn:length(sessionScope.cart.cartItems) }个项目</span></h3>
			<div class="checkout-right animated wow slideInUp" data-wow-delay=".5s">
			<!-- 如果空 -->
			<c:choose>
			<c:when test="${empty sessionScope.cart or fn:length(sessionScope.cart.cartItems) eq 0}">
				<img src="<c:url value='/images/nullcart.jpg'/>" class="img-responsive" alt="Responsive image" />
			</c:when>
			<c:otherwise>
				<a href="<c:url value='/CartServlet?method=clear'/>" ><button type="button" class="btn btn-warning">清空购物车</button></a>			
				<table class="timetable_sub">
					<thead>
						<tr>
							<th>序号</th>	
							<th>产品</th>
							<th>数量</th>
							<th>产品名称</th>
							<th>医疗技师</th>
							<th>价格</th>
							<th>删除</th>
						</tr>
					</thead>

					<c:set var="i" value="1"/>
					<c:forEach items="${sessionScope.cart.cartItems}" var="temp" >
					<tr class="rem${i }">
						<td class="invert">${i }</td>
						<c:set var="i" value="${i+1 }"/>
						<td class="invert-image"><img src="${temp.cure.image }" width="140px" class="img-thumbnail img-responsive" alt="Responsive image" /></td>
						<td class="invert">
							 <div class="quantity"> 
<!-- 								<div class="quantity-select">                           
									<div class="entry value-minus">&nbsp;</div>
									<div class="entry value-plus active">&nbsp;</div>
								</div> -->
									<div class="entry value"><span>${temp.count }</span></div>
							</div>
						</td>
						<td class="invert">${temp.cure.cure_name }</td>
						<td class="invert">${temp.cure.cure_doctor }</td>
						<td class="invert">¥<span class="badge badge-danger">${temp.cure.cure_cost }</span></td> 
						<td class="invert"><a href=" <c:url value='/CartServlet?method=delete&cure_id=${temp.cure.cure_id }' /> "><button type="button" class="btn btn-danger">删除该项</button></a></td>
					</tr>
					</c:forEach>
				</table>
			<div class="checkout-left">	
				<div class="checkout-left-basket animated wow slideInLeft" data-wow-delay=".5s">
					<ul>
					<c:forEach items="${sessionScope.cart.cartItems}" var="temp" >
						<li>${temp.cure.cure_name }<i>-</i> <span>${temp.cure.cure_cost } </span></li>
					</c:forEach>
					</ul>
					<h4>${sessionScope.cart.total }</h4>
				</div>
				<div class="checkout-right-basket animated wow slideInRight" data-wow-delay=".5s">
				<form id='form' class="form-horizontal" role="form" action="<c:url value='/OrderServlet'/>" method="post">
					<input type="hidden" name="method" value="checkout">
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">tel</label>
						<div class="col-sm-7">
							<input size="20" type="tel" name="phone" id="inputTel" class="form-control" placeholder="你的电话" onblur = "checkTel('inputTel')" >
							<script type="text/javascript">
								function checkTel(str){
									var strTel = document.getElementById(str).value;
									var re= /^1[34578]\d{9}$/;
								    var result=strTel.match(re);
							        if(result == null){
							        	alert('电话号码错误');
							        	return;
							        }
								}
							</script>
						</div>
					</div>
					<div class="form-group">
						<label for="inputEmail3" class="col-sm-2 control-label">date</label>
						<div class="col-sm-7">
		              	<input type="datetime" name="reserveTime" value="${nowDate }" class="form-control" id="mydatetimepicker" data-date-format="yyyy/mm/dd HH:ii" />
		              	</div>
					<script>
						$('#mydatetimepicker').datetimepicker();
					</script>
					</div>
					<br/>
				</form>
					<a href="<c:url value='/CureServlet?method=findAll' />"><span class="glyphicon glyphicon-menu-left" aria-hidden="true"></span>继续添加</a>
					<a href="javascript:document.getElementById('form').submit();"><span class="glyphicon glyphicon-menu-right" aria-hidden="true"></span>前去结账</a>
				</div>
				
			</div>
			</c:otherwise>
			</c:choose>
			</div>
		</div>
	</div>
<!-- //checkout -->
<!-- footer -->
<%@ include file="common/footer.jsp" %>
<!-- //footer -->
</body>
</html>
    