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
<title>plan</title>

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
<div class="typo">
<div class="container">
	<h3 class="title animated wow zoomIn" data-wow-delay=".5s">查看计划</h3>
	<p class="est animated wow zoomIn" data-wow-delay=".5s">在这里你可以看到最近的计划,也可以翻页哦</p>
	<div class="bs-docs-example animated wow fadeInUp"
		data-wow-duration="1000ms" data-wow-delay="500ms">
		<table class="table table-striped">
			<thead>
				<tr>
					<th>序号</th>
					<th>下单时间</th>
					<th>消费金额</th>
					<th>联系电话</th>
					<th>业务员</th>
				</tr>
			</thead>
			<tbody>
			<%--c:set var="i" value="0" /> --%>
			<c:forEach items="${pageBean.list}" var="tmp" varStatus="i" >
				<tr>
					<td>${tmp.oid }</td>
					<td>${tmp.ordertime }</td>
					<td>${tmp.total }</td>
					<td>${tmp.tel }</td>
					<td>${tmp.user_id }</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
	</div>
	<nav style="text-align: center">
	<ul class="pagination pagination-lg">
		<li><a href="<c:url value='/OrderServlet?method=planPage&nextPage=1' />">首页</a></li>
		<c:choose>
			<c:when test="${pageBean.nowPage eq 1 }">
				<li class="disabled"><a href="#"><i class="fa fa-angle-left">&laquo;</i></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value='/OrderServlet?method=planPage&nextPage=${pageBean.nowPage-1 }'/>"><i class="fa fa-angle-left">&laquo;</i></a></li>
			</c:otherwise>
		</c:choose>
		<c:forEach begin="${pageBean.beginPage }" end="${pageBean.endPage }" step="1" var="i">
		<c:choose>
			<c:when test="${pageBean.nowPage eq  i}">
				<li class="active"><a href="#">${i }</a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value='/OrderServlet?method=planPage&nextPage=${i }'/>">${i }</a></li>
			</c:otherwise>
		</c:choose>
		</c:forEach>
		<c:choose>
			<c:when test="${pageBean.nowPage eq pageBean.allPage }">
				<li class="disabled"><a href="#"><i class="fa fa-angle-left">&raquo;</i></a></li>
			</c:when>
			<c:otherwise>
				<li><a href="<c:url value='/OrderServlet?method=planPage&nextPage=${pageBean.nowPage+1 }'/>"><i class="fa fa-angle-left">&raquo;</i></a></li>
			</c:otherwise>
		</c:choose>
		<li><a href="<c:url value='/OrderServlet?method=planPage&nextPage=${pageBean.allPage }' />">尾页</a></li>
	</ul>
	</nav>
</div>
</div>
<!-- //plan -->
<!-- footer -->
<%@ include file="common/footer.jsp"%>
<!-- //footer -->
</body>
</html>