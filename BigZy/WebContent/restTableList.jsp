<%@page import="java.util.ArrayList"%>
<%@page import="obj.TableInfo"%>
<%@page import="java.util.List"%>
<%@page import="obj.RestInfo"%>
<%@page import="obj.CustInfo"%>
<%@page import="obj.User"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>餐厅餐桌信息</title>
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
List<TableInfo> tableInfos=request.getAttribute("tableList")==null?new ArrayList<>():(List<TableInfo>)request.getAttribute("tableList");
if(request.getAttribute("isFaild")!=null)
{
	
	List<Integer> ids=(List<Integer>)request.getAttribute("isFaild");
	if(ids.size()>0)
	{
		
		StringBuilder message=new StringBuilder();
		for(int i=0;i<ids.size();i++)
		{
			for(TableInfo tableInfo:tableInfos){
				if(ids.get(i)==tableInfo.getId())
				message.append(tableInfo.getTableid().toString()+" ");
			}
		}
		message.append("号餐桌已有预订，无法移除");
		%>
		<script>alert("<%=message.toString() %>");</script>
		<%
	}
	else {
		%>
		<script>alert("已全部删除");</script>
		<%
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

		<!-- Start right Content here -->
		<div class="content-page">
			<!-- Start content -->
			<div class="content">
				<div class="card-box">
					<!-- Row start -->
					<form class="am-form" name="tableDeal">
					<div class="am-g">
						<div class="am-u-sm-12 am-u-md-6">
				          <div class="am-btn-toolbar">
				            <div class="am-btn-group am-btn-group-xs">
				              <button type="button" onclick="showAddDiv()"  class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
				              <button type="button" onclick="ShowDeleteTable()" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
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
	                					<th class="table-title">ID</th>
	                					<th class="table-title">容纳人数</th>
	                					<th class="table-title">位置</th>
	              					</tr>
	             				 </thead>
	             				 
	             				 <%for(TableInfo tableInfo:tableInfos) {%>
	              				 <tbody>
	              					<tr>
	                					<td><input type="checkbox" name="tableIds" value="<%=tableInfo.getId() %>" /></td>
	                					<td><%=tableInfo.getTableid() %></td>
	                					<td><%=tableInfo.getCapacity() %></td>
	                					<td><%=tableInfo.getPosition() %></td>
	              					</tr>
	              				</tbody>
	              				
	              				<%} %>
              				</table>
          				
        			</div>
				</div>
				
				</form>
			</div>
			
			<div class="am-u-md-6" id="addTable" style="display:none;">
				<div class="card-box">
						<h4 class="header-title m-t-0 m-b-30">餐桌基本信息</h4>
						<form class="am-form am-form-horizontal" name="addTableForm">
						 <div class="am-form-group">
						    <label for="doc-ipt-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">餐桌编号</label>
						    <div class="am-u-sm-9">
						      <input type="text" id="tableid" name="tableid">
						    </div>
						  </div>
						
						  <div class="am-form-group">
						    <label for="doc-ipt-pwd-2" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">容纳人数</label>
						    <div class="am-u-sm-9">
						      <input type="text" id="peopleNum" name="peopleNum">
						    </div>
						  </div>
						  
						  <div class="am-form-group">
						    <label for="doc-ipt-pwd-3" class="am-u-sm-3 am-form-label am-text-right am-padding-right-0">餐桌位置</label>
						    <div class="am-u-sm-9">
						      <input type="text" id="position" name="position">
						    </div>
						  </div>
						
						  <div class="am-form-group">
						    <div class="am-u-sm-10 am-u-sm-offset-3">
						      <button type="button" onclick="addTable()" class="am-btn am-btn-default">添加</button>
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
</body>
<script type="text/javascript">
	function ShowDeleteTable()
	{
		var tableObject=document.getElementsByName("tableIds");
		var tableIds=new Array();
		
		for(var i=0;i<tableObject.length;i++)
		{
			if(tableObject[i].checked)
			{
				tableIds.push(tableObject[i].value.toString());
			}
		}
		if(tableIds.length==0)
		{
			alert("请选择要删除的餐桌");
			return ;
		}
		tableDeal.action="deleteTables.tableInfo?tableIds="+tableIds.toString();
		tableDeal.method="post";
		tableDeal.submit();
	}

	
	function showAddDiv()
	{
		document.getElementById('addTable').style.display=document.getElementById('addTable').style.display=='none'?"block":"none";
	}
	
	function addTable()
	{
		var tableid=document.getElementById("tableid");
		var peopleNum=document.getElementById("peopleNum");
		var position=document.getElementById("position");
		if(tableid.value==""||peopleNum.value==""||position.value=="")
		{
			alert("每个参数不能为空");
			document.getElementById("tableid").focus();
			return ;
		}
		<% 
		if(tableInfos.size()!=0){
			for(TableInfo tableindo:tableInfos)
			{
				%>
					var id=<%=tableindo.getTableid() %>;
					if(id==tableid.value)
					{
						alert("该餐桌编号已存在");
						return ;
					}
				<%
			}
		}
		%>
		
		addTableForm.action="addTableInfo.tableInfo";
		addTableForm.method="post";
		addTableForm.submit();
		
	}

</script>

</html>