<%@page import="obj.CustInfo"%>
<%@page import="obj.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>WelCome</title>
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

	
	<% 
	User user=(User)session.getAttribute("userInfo");
	CustInfo custInfo=(CustInfo)session.getAttribute("custInfo");
	%>
		
	<!-- Begin page -->
	<header class="am-topbar am-topbar-fixed-top">		
		<div class="am-topbar-left am-hide-sm-only">
               <p href="index.html" class="logo"><span>顾客</span><i class="zmdi zmdi-layers"></i></p>
           </div>

		<div class="contain">
			<ul class="am-nav am-navbar-nav am-navbar-left">
				<li><h4 class="page-title">我的信息</h4></li>
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
					    <li><a href="custIndex.jsp"><span class="am-icon-home"></span> 我的信息</a></li>
					    <li><a href="custSelectList.restInfo"><span class="am-icon-home"></span> 餐厅列表</a></li>
					    <li><a href="selectOrderInfo.reseInfo"><span class="am-icon-home"></span> 订单信息</a></li>
					    <li><a href="selectComment.comment"><span class="am-icon-home"></span> 评论信息</a></li>
					</ul>
			</div>
			  </div>
	<div class="content-page">
		<!-- Row start -->
					<div class="am-u-sm-12">
						<div class="card-box">
							
							
							<h4 class="header-title m-t-0 m-b-30">账户信息</h4>
							<div class="am-g">
								<div class="am-u-md-6">
									<form class="am-form am-text-sm" >
										<div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">账户名</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=user.getUsername() %> disabled>
											</div>
									    </div>
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">电子邮箱</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=user.getMailBox() %> disabled>
											</div>
									    </div>
									</form>
								</div>
								
								<div class="am-u-md-6">
									<form class="am-form am-text-sm">
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">用户类型
												</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=user.getUserType() %> disabled>
											</div>
									    </div>
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">密码</label>
									      		<input class="am-form-field am-u-md-10" type="password" id="doc-ds-ipt-1"  placeholder=<%=user.getPassword() %> disabled>
											</div>
									    </div>
									</form>
								</div>
							</div>
						</div>
					</div>
		<!-- Row end -->
		<!-- Row start -->
					<div class="am-u-sm-12">
						<div class="card-box">
							
							
							<h4 class="header-title m-t-0 m-b-30">基本信息</h4>
							<div class="am-g">
								<div class="am-u-md-6">
									<form class="am-form am-text-sm" >
										<div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">我的名字</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=custInfo.getCustname() %> disabled>
											</div>
									    </div>
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">电话号码</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=custInfo.getNumber() %> disabled>
											</div>
									    </div>
									    
									</form>
								</div>
								
								<div class="am-u-md-6">
									<form class="am-form am-text-sm">
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">口味</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=custInfo.getTaste() %> disabled>
											</div>
									    </div>
									    
									    <div class="am-form-group">
											<div class="am-g">
												<label class="am-u-md-2 am-md-text-right" for="doc-ds-ipt-1">地址</label>
									      		<input class="am-form-field am-u-md-10" type="text" id="doc-ds-ipt-1"  placeholder=<%=custInfo.getAddress() %> disabled>
											</div>
									    </div>
									</form>
								</div>
							</div>
						</div>
					</div>
		<!-- Row end -->
	</div>
	<!-- end right Content here -->
	
	<!-- navbar -->
	<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
</body>
</html>