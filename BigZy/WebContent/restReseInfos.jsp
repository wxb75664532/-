<%@page import="model.CustPeseicarte"%>
<%@page import="java.util.Date"%>
<%@page import="model.CustReseInfo"%>
<%@page import="obj.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅订单信息信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/amazeui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/font-awesome.min.css">
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/core.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/menu.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/index.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/admin.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/typography.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/form.css" />
</head>
<body>

<%
	User user=(User)session.getAttribute("userInfo");
	List<CustReseInfo> custReseInfos=request.getAttribute("custReseInfos")==null?new ArrayList<>():(List<CustReseInfo>)request.getAttribute("custReseInfos");
%>
	
		<header class="am-topbar am-topbar-fixed-top">		
			<div class="am-topbar-left am-hide-sm-only">
                <a href="index.html" class="logo"><span>餐厅管理员</span><i class="zmdi zmdi-layers"></i></a>
            </div>
	
			<div class="contain">
				<ul class="am-nav am-navbar-nav am-navbar-left">

					<li><h4 class="page-title">订单信息</h4></li>
				</ul>
				
				<ul class="am-nav am-navbar-nav am-navbar-right">
					<li class="inform"><i class="am-icon-bell-o" aria-hidden="true"></i></li>
					<li class="hidden-xs am-hide-sm-only">
                        <form role="search" class="app-search">
                            <input type="text" placeholder="Search..." class="form-control">
                            <a href=""><img src="${pageContext.request.contextPath}/resource/img/search.png"></a>
                        </form>
                    </li>
				</ul>
			</div>
		</header>
		<!-- end page -->
		
		
		<div class="admin">
			<div class="admin-sidebar am-offcanvas  am-padding-0" id="admin-offcanvas">
	          	<div class="am-offcanvas-bar admin-offcanvas-bar">
		            <!-- User -->
		          	<div class="user-box am-hide-sm-only">
		                 <h5><a href="#"><%=user==null?null:user.getUsername()%></a> </h5>
		            </div>
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
			<!-- Start content -->
			<div class="content">
				<div class="card-box">
				  	<div class="am-g">
       					<div class="am-u-sm-12">
         					
           						<table class="am-table am-table-striped am-table-hover table-main">
             						<thead>
							              <tr>
							                <th class="table-check"><input type="checkbox"></th>
							                <th class="table-type">订单ID</th>
							                <th class="table-author am-hide-sm-only">桌号</th>
							                <th class="table-author am-hide-sm-only">人数</th>
							                <th class="table-date am-hide-sm-only">时间</th>
							                <th class="table-type">状态</th>
							                <th class="table-set">操作</th>
							              </tr>
						            </thead>
					              	<tbody>
					              		<%
					              			for(CustReseInfo custReseInfo:custReseInfos){
					              		%>
						              	<form class="am-form" action="selectPeseicarteServlet.peseicarte" method="get">
						              	<tr>
						                	<td><input type="checkbox" /></td>
						                	<td><%=custReseInfo.getOrderid()%></td>
						                	<td class="am-hide-sm-only"><%=custReseInfo.getTableid()%></td>
						                	<td class="am-hide-sm-only"><%=custReseInfo.getPeoplenum()%></td>
						                	<td><%=custReseInfo.getTime()%></td>
						                	<td><%=custReseInfo.getState()%></td>
						                	
							                <td>
						                    	<div class="am-btn-group am-btn-group-xs">
						                    			<input type="hidden"   name="orderId" value="<%=custReseInfo.getOrderid()%>">
						                    			<button type="submit" class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span>菜单</button>
						                    	</div>
						                    	
						                	</td>
						              	</tr>
						              	</form>
						              	<%}%>
					              	</tbody>
           						</table>
         					
      					 </div>
    				</div>
				</div>
				<%
					List<CustPeseicarte> peseicartes=(List<CustPeseicarte>)request.getAttribute("peseicartes");
							if(peseicartes!=null){
				%>	
				<div class="card-box">
					<!-- col start -->
					<table class="am-table">
					    <thead>
					        <tr>
					            <th>食品名称</th>
					            <th>单价</th>
					            <th>备注</th>
					        </tr>
					    </thead>
					    <tbody>
					    <%
					    	for(CustPeseicarte peseicarte:peseicartes){
					    %>
				        <tr>
				            <td><%=peseicarte.getFoodName() %></td>
				            <td><%=peseicarte.getUnitPrice() %></td>
				            <td>无</td>
				        </tr>
				        <%} %>
					    </tbody>
					</table>
					<!-- col end -->
				</div>
				<%} %>
			</div>
		</div>
	</div>
	
		<!-- navbar -->
		<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
</body>
</html>