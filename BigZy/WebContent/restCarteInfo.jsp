<%@page import="obj.CarteInfo"%>
<%@page import="java.util.ArrayList"%>
<%@page import="obj.TableInfo"%>
<%@page import="java.util.List"%>
<%@page import="obj.RestInfo"%>
<%@page import="obj.CustInfo"%>
<%@page import="obj.User"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>餐厅菜单信息</title>
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
if(request.getAttribute("notSuccessIDs")!=null)
{
	String ids=(String)request.getAttribute("notSuccessIDs");
	if(ids.length()==0){
		%><script>alert("全部删除成功");</script><%
	}else {
		String message="删除编号为:"+request.getAttribute("notSuccessIDs")+"失败";
		%><script>alert("<%=message%>");</script><%
	}
}
%>

	<!-- Begin page -->
	<header class="am-topbar am-topbar-fixed-top">		
		<div class="am-topbar-left am-hide-sm-only">
               <p href="index.html" class="logo"><span>餐厅管理员</span><i class="zmdi zmdi-layers"></i></p>
           </div>

		<div class="contain">
			<ul class="am-nav am-navbar-nav am-navbar-left">
				<li><h4 class="page-title">菜单信息</h4></li>
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
		<%
			List<CarteInfo> carteInfos=request.getAttribute("carteInfos")==null?new ArrayList<>():(List<CarteInfo>)request.getAttribute("carteInfos");
		%>
		<!-- Start right Content here -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="card-box">
					<!-- Row start -->
					<form class="am-form" name="deleteCartes">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6">
				          <div class="am-btn-toolbar">
				            <div class="am-btn-group am-btn-group-xs">
				              <button type="button" onclick="ShowAddDiv()" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
				              <button type="button" onclick="ShowDeleteCartes()" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
				            </div>
				          </div>
				        </div>
				    </div>
					  <!-- Row end -->
					  
					  <!-- Row start -->
				   <div class="am-g">
        			 <div class="am-u-sm-12">
            			   	 <table class="am-table am-table-striped am-table-hover table-main">
	              				 <thead>
	              					<tr>
	                					<th class="table-check"><input type="checkbox"/></th>
	                					<th class="table-id">ID</th>
	                					<th class="table-title">菜品名称</th>
	                					<th class="table-type">单价</th>
	                					<th class="table-type">菜品种类</th>
	                					<th class="table-title">菜品介绍</th>
	                					<th class="table-type">图片</th>
	              					</tr>
	             				 </thead>
	             				 
	             				 <%for(CarteInfo carteInfo:carteInfos) {%>
	              				 <tbody>
	              					<tr>
	                					<td><input type="checkbox" name="carteIds" value="<%=carteInfo.getId() %>" /></td>
	                					<td><%=carteInfo.getId() %></td>
	                					<td><%=carteInfo.getFoodName() %></td>
	                					<td><%=carteInfo.getUnitPrice() %></td>
	                					<td><%=carteInfo.getCategory() %></td>
	                					<td><%=carteInfo.getIntro() %></td>
	                					<td><%=carteInfo.getPicture() %></td>
	              					</tr>
	              				</tbody>
	              				
	              				<%} %>
              				</table>
        			</div>
				</div>
				
				</form>
			</div>
			
			
			<!-- col start -->
				<div class="am-u-md-6" id="addCarteDiv" style="display:none;">
					<div class="card-box">
						<h4 class="header-title m-t-0 m-b-30">水平排列</h4>
						<form class="am-form am-form-horizontal" name="addCarteForm">
						 <div class="am-form-group">
						    <label for="doc-ipt-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">菜品名称</label>
						    <div class="am-u-sm-9">
						      <input type="text" name="foodName" id="foodName">
						    </div>
						  </div>
						
						  <div class="am-form-group">
						    <label for="doc-ipt-pwd-2" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">单价</label>
						    <div class="am-u-sm-9">
						      <input type="text" name="unitPrice" id="unitPrice">
						    </div>
						  </div>
						  
						  <div class="am-form-group">
						    <label for="doc-ipt-pwd-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">菜品种类</label>
						    <div class="am-u-sm-9">
						      <input type="text" name="category" id="category">
						    </div>
						  </div>
							<div class="am-form-group">
							    <label for="doc-ipt-pwd-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">菜品介绍</label>
							    <div class="am-u-sm-9">
							      <input type="text" name="intro" id="intro">
							    </div>
							  </div>
						
						  <div class="am-form-group">
						    <div class="am-u-sm-10 am-u-sm-offset-3">
						      <button type="button" onclick="addCarte()" class="am-btn am-btn-default">添加</button>
						    </div>
						  </div>
						</form>
					</div>
				</div>
			
			
		</div>
	</div>
</div>
	<!-- navbar -->
	<a href="admin-offcanvas" class="am-icon-btn am-icon-th-list am-show-sm-only admin-menu" data-am-offcanvas="{target: '#admin-offcanvas'}"><!--<i class="fa fa-bars" aria-hidden="true"></i>--></a>
	
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/jquery-2.1.0.js" ></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resource/js/amazeui.min.js"></script>
	<script type="text/javascript">
		function ShowDeleteCartes()
		{
			var carteIds=document.getElementsByName("carteIds");
			var cartes=new Array();
			
			for(var i=0;i<carteIds.length;i++)
			{
				if(carteIds[i].checked)
				{
					cartes.push(carteIds[i].value.toString());
				}
			}
			
			if(cartes.length==0)
			{
				alert("请选择需要删除的菜品");
				return ;
			}
			deleteCartes.action="deleteCartes.carteInfo?carteIds="+cartes.toString();
			deleteCartes.method="post";
			deleteCartes.submit();
			
		}
		
		function ShowAddDiv()
		{
			document.getElementById("addCarteDiv").style.display=document.getElementById("addCarteDiv").style.display=="none"?"block":"none";
		}

		function addCarte()
		{
			var foodname=document.getElementById("foodName");
			var unitPrice=document.getElementById("unitPrice");
			var category=document.getElementById("category");
			var intro=document.getElementById("intro");
			
			if(foodname.value=="")
			{
				alert("食品名称不能为空");
				document.getElementById("foodName").focus();
			}
			else if(unitPrice.value=="")
			{
				alert("单价不能为空");
				document.getElementById("unitPrice").focus();
			}
			else if(category.value=="")
			{
				alert("种类不能为空");
				document.getElementById("category").focus();
			}else if(intro.value=="")
			{
				alert("介绍不能为空");
				document.getElementById("intro").focus();
			}else{
				addCarteForm.action="addCarteInfo.carteInfo";
				addCarteForm.method="post";
				addCarteForm.submit();
			}
		}
		
	</script>

</body>

</html>