<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>顾客信息</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/core.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/menu.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/amazeui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/component.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/form.css" />
</head>
<body>
<%
	if(request.getAttribute("email")==null)
	{
		%>
		<script type="text/javascript">
				alert("请先通过邮箱获取验证码");
				window.location.href="Register.jsp";
		</script>
		<%
	}
%>
<div class="wrapper-page">
	            <div class="text-center">
	                <a href="Login.jsp" class="logo"><span>顾客注册信息</span></a>
	            </div>
	            <div class="m-t-40 card-box">
	            	<div class="text-center">
	                    <h4 class="text-uppercase font-bold m-b-0">完善信息,否则注册失败</h4>
	                </div>
	                <div class="panel-body">
	                	<form class="am-form" name="registerForm">
	                		<div class="am-g">
	                			<div class="am-form-group">
							      <input type="text" class="am-radius" id="username" name="username"  placeholder="Username">
							    </div>
							
							    <div class="am-form-group form-horizontal m-t-20">
							      <input type="password" class="am-radius" id="password" name="password"  placeholder="Password">
							    </div>
							    
							    <div class="am-form-group">
							      <input type="text" class="am-radius" id="custName" name="custName"  placeholder="custName">
							    </div>
							    
							    <div class="am-form-group">
							      <input type="text" class="am-radius" id="address" name="address"  placeholder="Address">
							    </div>
		                        
		                        <div class="am-form-group"> 
							      <input type="text" class="am-radius" id="number" name="number"  placeholder="number">
							    </div>
		                        
		                        <div class="am-form-group"> 
							      <input type="text" class="am-radius" id="taste" name="taste"  placeholder="taste">
							    </div>
		                        
		                        <div class="am-form-group">
							      <input type="text" class="am-radius" id="email" name="email"  value="<%=request.getAttribute("email") %>" readonly>
							    </div>
		                        <div class="am-form-group">
							      <input type="text" class="am-radius" id="userType" name="userType" value="<%=request.getAttribute("userType") %>" readonly>
							    </div>
		                        <div class="am-form-group ">
		                        	<button type="button" onclick="register()" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;">注册</button><button type="button" onclick="returns()" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;">返回</button>
		                        </div>
	                		</div>

	                	</form>
							
	                </div>
	            </div>
			</div>
			
			<script type="text/javascript">
				function register()
				{
					var username=document.getElementById("username");
					var password=document.getElementById("password");
					var custName=document.getElementById("custName");
					var address=document.getElementById("address");
					var number=document.getElementById("number");
					var taste=document.getElementById("taste");
					if(username==""||password==""||custName==""||address==""||number==""||taste=="")
					{
						alert("输入不能为空");
						return ;
					}
					registerForm.action="custInfo.register";
					registerForm.method="post";
					registerForm.submit();
					
				}
				function returns(){
					<% session.setAttribute("verification", ""); %>
					window.location.href="Register.jsp";
				}
			</script>
</body>
</html>