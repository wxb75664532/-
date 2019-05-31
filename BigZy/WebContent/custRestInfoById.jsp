
<%@page import="java.util.ArrayList"%>
<%@page import="obj.User"%>
<%@page import="model.CustComment"%>
<%@page import="obj.CarteInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
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
<%User user=(User)session.getAttribute("userInfo");
	String carteIds;
	if(request.getAttribute("carteids")!=null)
	{
		carteIds=(String)request.getAttribute("carteids");
		%><p><%=carteIds.toString() %></p> <%
	}else{

		carteIds="";
	}
%>
		<header class="am-topbar am-topbar-fixed-top">		
			<div class="am-topbar-left am-hide-sm-only">
                <a href="index.html" class="logo"><span>顾客</span><i class="zmdi zmdi-layers"></i></a>
            </div>
	
			<div class="contain">
				<ul class="am-nav am-navbar-nav am-navbar-left">

					<li><h4 class="page-title"><%=request.getAttribute("restName") %></h4></li>
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
				<div class="am-u-sm-12">
					<div class="card-box">
						<h4 class="header-title m-t-0 m-b-30">菜单</h4>
					  	<div class="am-g">
         					<%if(request.getAttribute("carteInfos")==null) {
         						%><h4 class="page-title">没有菜单信息</h4>
         					<%}else { 
         						List<CarteInfo> carteInfos=(List<CarteInfo>)request.getAttribute("carteInfos");
         					%>
           						<table class="am-table am-table-striped am-table-hover table-main">
             						<thead>
							              <tr>
							                <th class="table-check"><input type="checkbox"></th>
							                <th class="table-title">菜品名称</th><th class="table-title">菜品价格</th>
							                <th class="table-type">菜品种类</th>
							                <th class="table-title">菜品简介</th>
							                <th class="table-title">菜品图片</th>
							              </tr>
						            </thead>
					              	<tbody>
					              	
					              		<%for(CarteInfo carteInfo:carteInfos){%>
						              	<tr>
						                	<td><input type="checkbox" name="carteInfo" value="<%=carteInfo.getId() %>"  <%if(carteIds.contains((carteInfo.getId().toString()))){%>checked<%} %>  /></td>
						                	<td><%=carteInfo.getFoodName() %></td>
						                	<td><%=carteInfo.getUnitPrice() %></td>
						                	<td><%=carteInfo.getCategory() %></td>
						                	<td class="am-hide-sm-only"><%=carteInfo.getIntro() %></td>
						                	<td class="am-hide-sm-only"><%=carteInfo.getPicture() %></td>
						              	</tr>
						              	<%} %>
					              	</tbody>
           						</table>
           						<%} %>
      					 </div>
    				</div>
				</div>
				
				<div class="am-u-md-6">
					<div class="card-box">
						<h4 class="header-title m-t-0 m-b-30">预计就餐时间</h4>
						<form class="am-form am-form-horizontal"  name="reseInfoForm">
						  <div class="am-form-group">
						    <label for="doc-ipt-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">年月日</label>
						    <div class="am-u-sm-9">
						      <input id="date" name="date" type="text" class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',minDate:'%y-%M-%d {%H+2}:%m',maxDate:'%y-%M-{%d+7}',minTime:'08:00:00',maxTime:'22:30:00'})"/>
						    </div>
						  </div>
						  <div class="am-form-group">
						    <label for="doc-ipt-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">就餐人数</label>
						    <div class="am-u-sm-9">
						      <input type="text" id="peopleNum" name="peopleNum" >
						    </div>
						  </div>
						  <div class="am-form-group">
						    <div class="am-u-sm-10 am-u-sm-offset-3">
						      <input type="hidden" id="restId" name="restId" value="<%=request.getAttribute("restId") %>" />
						       <input type="hidden" id="restId" name="restName" value="<%=request.getAttribute("restName") %>" />
						      <input type="button" onclick="validate()" class="am-btn am-btn-default" value="创建订单"/>
						    </div>
						  </div>
						</form>
					</div>
				</div>
				<div class="am-u-sm-12">
					<div class="card-box">
					<h4 class="header-title m-t-0 m-b-30">所有评论</h4>
					  	<div class="am-g">
       					
         					<%if(request.getAttribute("custComments")==null) {
         						%><h4 class="page-title">没有评论信息</h4>
         					<%}else { 
         						List<CustComment> custComments=(List<CustComment>)request.getAttribute("custComments");
         					%>
           						<table class="am-table am-table-striped am-table-hover table-main">
             						<thead>
							              <tr>
							                
							                <th class="table-title">评论用户ID</th>
							                <th class="table-type">评论风格</th>
							                <th class="table-title">评论内容</th>
							                <th class="table-title">评论时间</th>
							              </tr>
						            </thead>
					              	<tbody>
					              	
					              		<%for(CustComment custComment:custComments){%>
						              	<tr>
						                	
						                	<td><%=custComment.getCustid() %></td>
						                	<td><%=custComment.getTy() %></td>
						                	<td><%=custComment.getSketch() %></td>
						                	<td class="am-hide-sm-only"><%=custComment.getTime() %></td>
						              	</tr>
						              	<%} %>
					              	</tbody>
           						</table>
           						<%} %>
      					 </div>
    				</div>
				</div>
				
			</div>
		</div>
	</div>
		<!-- navbar -->
		<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
		
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
		<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
		<script language="javascript" type="text/javascript" src="${pageContext.request.contextPath}/My97DatePicker/WdatePicker.js"></script>

		
</body>
<script type="text/javascript">
			function validate()
			{
				
				var date=document.getElementById("date");
				var peopleNum=document.getElementById("peopleNum");
				var obj=document.getElementsByName("carteInfo");
				var carteids=new Array();

				for(var i=0;i<obj.length;i++)
				{
					if(obj[i].checked)
					{
						carteids.push(obj[i].value.toString());
					}
				}
				if(date.value=="")
				{
					alert("就餐时间不能为空");
					return;
				}
				else if(peopleNum.value=="")
				{
					alert("就餐人数不能为空");
					return;
				}
				else if(carteids.length==0)
				{
					alert("请选择菜品");
					return;
				}
				else {
					reseInfoForm.action="addReseInfo.reseInfo?carteids="+carteids.toString();
					reseInfoForm.method="post";
					reseInfoForm.submit();
				}
			}
</script>
</html>