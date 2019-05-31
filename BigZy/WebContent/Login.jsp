<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/core.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/menu.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/amazeui.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/component.css" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/form.css" />
<title>Login</title>
</head>
<%
     	if(request.getAttribute("isLogin")!=null){
     		boolean isLogin=(boolean)request.getAttribute("isLogin");
     		if(isLogin==true){
     			String userType=(String)request.getAttribute("userType");
     			if(userType.equals("餐厅管理"))
     			response.sendRedirect("restIndex.jsp");
     			else response.sendRedirect("custIndex.jsp");
     		}
     	}
%>
<body>
	<div class="account-pages">
		<div class="wrapper-page">
			<div class="text-center">
                <a href="Login.jsp" class="logo"><span>餐厅预订系统</span></a>
            </div>
            
            <div class="m-t-40 card-box">
            	<div class="text-center">
                    <h4 class="text-uppercase font-bold m-b-0">Sign In</h4>
                </div>
                <div class="panel-body">
                	<form class="am-form" action="Login" method="post">
                		<div class="am-g">
                			<div class="am-form-group">
						      <input id="username" name="username" type="text" class="am-radius"  placeholder="Username">
						    </div>
						
						    <div  class="am-form-group form-horizontal m-t-20">
						      <input id="password" name="password" type="password" class="am-radius"  placeholder="Password">
						    </div>
						    
						    <div class="am-form-group ">
		                           	<select name="userType"> 
										<option value="顾客">顾客</option> 
										<option value="餐厅管理">餐厅管理</option> 
									</select>
		                        </div>
	                        
	                        <div class="am-form-group ">
	                        	<input type="submit" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;" value="登陆"><input type="button" onclick="gotoRegister()" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;" value="注册">
	                        	
	                        </div>
	                        
	                        <div class="am-form-group ">
	                        <%
						     	if(request.getAttribute("isLogin")!=null){
						     		boolean isLogin=(boolean)request.getAttribute("isLogin");
						     		if(isLogin==false){
						     			%>
						     			
	                        				<i id="tishi" class="fa fa-lock m-r-5" style="color:red;">账号密码错误</i> 
	                        			
						     			<%
						     		}
						     	}
	                        %>
	                        </div>
                		</div>
                	</form>
                </div>
            </div>
		</div>
	</div>
</body>
<script type="text/javascript">
	setTimeout(function(){
	document.getElementById("tishi").style.display="none";},2000);
	
	function gotoRegister()
	{
		window.location.href="Register.jsp";
	}
</script>
</html>

