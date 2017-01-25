<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE html>
<html>
<head>
<title>addPlan</title>

<!-- for-mobile-apps -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Best Store Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
		function hideURLbar(){ window.scrollTo(0,1); } 

</script>
<!-- //for-mobile-apps -->
<link href="css/bootstrap.css" rel="stylesheet" type="text/css"
	media="all" />
<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
<!-- js -->
<script src="js/jquery.min.js"></script>
<!-- //js -->
<!-- cart -->
<script src="js/simpleCart.min.js"></script>
<!-- cart -->
<!-- for bootstrap working -->
<script type="text/javascript" src="js/bootstrap-3.1.1.min.js"></script>
<!-- //for bootstrap working -->
<link href='css/fonts1.css' rel='stylesheet' type='text/css'>
<link href='css/fonts2.css' rel='stylesheet' type='text/css'>
<!-- timer -->
<link rel="stylesheet" href="css/jquery.countdown.css" />
<!-- //timer -->
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
<%@ include file="common/header.jsp"%>
<!-- //header -->
<!-- plan -->
<div class="container">
	<h3 class="title animated wow zoomIn" data-wow-delay=".5s">增加客户安排</h3>
	<p class="est animated wow zoomIn" data-wow-delay=".5s">添加一个客户安排</p>
		<div class="table-responsive">
             <table class="table table-bordered">  
                 <thead>  
                     <tr class="success">  
                         <th>理疗名称</th>  
                         <th>理疗医师</th>  
                         <th>单项花费</th>
                         <th>加入购物车</th>  
                     </tr>  
                 </thead>  
                 <tbody>  
                 <c:forEach items="${cureList }" var="cure" >
                     <tr>  
                         <td>${cure.cure_name }</td>  
                         <td>${cure.cure_doctor }</td>  
                         <td>¥<span class="badge badge-danger">${cure.cure_cost }</span></td>  
                         <td><a href="<c:url value='/CartServlet?method=addCure&cure_id=${cure.cure_id} ' />"><button type="button" class="btn btn-success btn-sm" >加入购物车</button></a></td>  
                     </tr>  
                 </c:forEach>
                 </tbody>  
             </table>  
		</div>
</div>
<!-- //plan -->
<!-- footer -->
<%@ include file="common/footer.jsp"%>
<!-- //footer -->
</body>
</html>