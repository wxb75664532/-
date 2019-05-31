<%@page import="obj.User"%>
<%@page import="obj.RestInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>餐厅列表信息</title>
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
	if(request.getAttribute("restInfos")==null)
	{
		%>
		<p>没有餐厅信息</p>
	<%} else {
		List<RestInfo> restInfos=(List<RestInfo>)request.getAttribute("restInfos");
		User user=(User)session.getAttribute("userInfo");

	%>
	
		<header class="am-topbar am-topbar-fixed-top">		
			<div class="am-topbar-left am-hide-sm-only">
                <a href="index.html" class="logo"><span>顾客</span><i class="zmdi zmdi-layers"></i></a>
            </div>
	
			<div class="contain">
				<ul class="am-nav am-navbar-nav am-navbar-left">

					<li><h4 class="page-title">餐厅信息</h4></li>
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
		                 <h5><a href="#"><%=user==null?null:user.getUsername() %></a> </h5>
		            </div>
		            <ul class="am-list admin-sidebar-list">
		              <li><a href="custIndex.jsp"><span class="am-icon-home"></span> 我的信息</a></li>
		              <li><a href="custSelectList.restInfo"><span class="am-icon-home"></span> 餐厅列表</a></li>
		              <li><a href="selectOrderInfo.reseInfo"><span class="am-icon-home"></span> 订单信息</a></li>
		              <li><a href="selectComment.comment"><span class="am-icon-home"></span> 评论信息</a></li>
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
							                <th class="table-title">餐厅ID</th><th class="table-title">餐厅名称</th>
							                <th class="table-type">地址</th>
							                <th class="table-author am-hide-sm-only">好评率</th>
							                <th class="table-date am-hide-sm-only">口味风格</th>
							                <th class="table-set">操作</th>
							              </tr>
						            </thead>
					              	<tbody>
					              	
					              		<%for(RestInfo restInfo:restInfos){%>
						              	<tr>
						                	<td><input type="checkbox" /></td>
						                	<td><%=restInfo.getRestid() %></td>
						                	<td><%=restInfo.getRestname() %></td>
						                	<td><%=restInfo.getAddress() %></td>
						                	<td class="am-hide-sm-only"><%=restInfo.getPraiserate() %></td>
						                	<td class="am-hide-sm-only"><%=restInfo.getCharacters() %></td>
							                <td>
							                	<form class="am-form" action="custSelectByRestId.restInfo" method="get">
								                  	<div class="am-btn-toolbar">
								                    	<div class="am-btn-group am-btn-group-xs">
								                    		<input type="hidden" name="restId" value="<%=restInfo.getRestid() %>">
								                    		<input type="hidden" name="restName" value="<%=restInfo.getRestname() %>">
								                    		<button class="am-btn am-btn-default am-btn-xs am-text-secondary"><span class="am-icon-pencil-square-o"></span>进入店铺</button>
								                    	</div>
								                  	</div>
							                  	</form>
						                	</td>
						              	</tr>
						              	<%} %>
					              	</tbody>
           						</table>
      					 </div>
    				</div>
				</div>
			</div>
		</div>
	</div>
<%} %>
		<!-- navbar -->
		<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
</body>
</html>