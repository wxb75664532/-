<%@page import="java.util.ArrayList"%>
<%@page import="model.CustComment"%>
<%@page import="obj.User"%>
<%@page import="java.util.List"%>
<%@page import="obj.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户评论</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/amazeui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/core.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/menu.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/index.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/admin.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/typography.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/form.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/component.css" />
</head>
<body>
<%User user=(User)session.getAttribute("userInfo");
List<CustComment> custComments=request.getAttribute("comments")==null?new ArrayList<>():(List<CustComment>)request.getAttribute("comments");
%>
	<!-- Begin page -->
	<header class="am-topbar am-topbar-fixed-top">		
		<div class="am-topbar-left am-hide-sm-only">
               <p href="index.html" class="logo"><span>餐厅管理员</span><i class="zmdi zmdi-layers"></i></p>
           </div>

		<div class="contain">
			<ul class="am-nav am-navbar-nav am-navbar-left">
				<li><h4 class="page-title">餐桌信息</h4></li>
			</ul>
		</div>
	</header>
	<!-- end page -->
	
	
	<div class="admin">
			  <div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
			    <div class="am-offcanvas-bar admin-offcanvas-bar">
			    	<!-- User -->
					<div class="user-box am-hide-sm-only">
                        <h5><a href="#"><%=user.getUsername()%></a> </h5>
                    </div>
                    <!-- End User -->
            
					 <ul class="am-list admin-sidebar-list">
					    <li><a href="restIndex.jsp"><span class="am-icon-home"></span> 餐厅的信息</a></li>
					    <li><a href="carteSelectList.carteInfo"><span class="am-icon-home"></span> 菜单列表信息</a></li>
					    <li><a href="tableSelectList.tableInfo"><span class="am-icon-home"></span> 餐桌列表信息</a></li>
					    <li><a href="selectOrderInfo.reseInfo"><span class="am-icon-home"></span> 客户订单信息</a></li>
					    <li><a href="selectComment.comment"><span class="am-icon-home"></span> 客户评论信息</a></li>
					</ul>
			</div>
			  </div>
	<div class="content-page"> 
		<div class="content">
			
			<%int i=0;
			for(CustComment comment:custComments){ 
			
				if(i%2==0){
			%>
			<div class="am-g"><%} %>
				<!-- col start -->
				<div class="am-u-md-6">
					<div class="card-box">
						<form class="am-form am-form-horizontal">
						  
						<div class="am-form-group">
						    <label>订单号<input type="text"  value="<%=comment.getOrderid() %>" readonly="true"></label>
						    <label>用户ID<input type="text"  value="<%=comment.getCustid() %>" readonly="true"></label>
						  </div>
						<div class="am-form-group">
							<label>评价类型<input type="text"  value="<%=comment.getTy() %>" readonly="true"></label>
						    <label>评论时间<input type="text" value="<%=comment.getTime() %>"  readonly="true"></label>
						  </div>
						  <div class="am-form-group">
						       <label >详细评价<textarea  cols="100" rows="5" id="doc-ta-1" readonly="true"><%=comment.getSketch() %></textarea></label>
					      </div>
						</form>
					</div>
				</div>
				<!-- col end -->
			<%if(i%2==0){ %>
			</div><%} %>
			<!-- row end -->
			<%
				i++;
			}
			%>
	</div>
		
</div>
</div>

<!-- navbar -->
	<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
</body>
</html>