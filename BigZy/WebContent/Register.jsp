<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/core.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/menu.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/amazeui.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/component.css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/resource/css/page/form.css" />
</head>
<body>
<%
	if(request.getAttribute("isSuccess")!=null)
	{
		boolean isT=(boolean) request.getAttribute("isSuccess");
		if(isT)
		{
			%>
			<script>
			alert("发送邮件成功，请及时查看");
			window.onload=(function(){
				var wait = 60;
				var button=document.getElementById("sendCode");
				(function time(o) {
			 			if (wait == 0) {
			              o.disabled=false;
			              o.innerHTML = "免费获取验证码";
			              alert("1");
			              wait = 60;
			         	} else {
			             o.disabled=true;
			             o.innerHTML = wait + "秒后可以重新发送";
			             wait--;
			             setTimeout(function() {
			                 time(o)
			             }, 1000)
			         }
				})(button);
			});
			</script>
			<%
		}
		else{
			%>
			<script>alert("发送邮件失败，请检查网络原因")</script>
			<%
		}
	}
%>

		<div class="account-pages">
			<div class="wrapper-page">
				<div class="text-center">
	                <a href="Login.jsp" class="logo"><span>餐厅预订系统</span></a>
	            </div>
	            
	            <div class="m-t-40 card-box">
	            	<div class="text-center">
	                    <h4 class="text-uppercase font-bold m-b-0">使用邮箱获取验证码</h4>
	                </div>
	                <div class="panel-body">
	                		<form class="am-form" name="sendForm">
	                			<div class="am-form-group">
							      <input type="email" class="am-radius" name="email" id="email"  placeholder="邮箱" value="<%=request.getAttribute("email")==null?"":request.getAttribute("email").toString() %>">
							    </div>
								<div class="am-form-group">
							    	<button type="button" onclick="sendMessage()" id="sendCode" class="am-btn am-btn-primary am-radius" style="width: 100%;height: 100%;">免费获取验证码</button>
								</div>
							</form>

							<form class="am-form" name="registerForm">
								<div class="am-form-group">
								      <select name="userType">
								        <option value="顾客">顾客</option>
								        <option value="餐厅管理">餐厅管理</option>
								      </select>
						    	</div>
								
								<div class="am-form-group">
							      <input type="text" class="am-radius" id="code" name="code"  placeholder="验证码">
							    </div>
		                        <div class="am-form-group ">
		                        	<input type="hidden" name="emailx"  value="<%=request.getAttribute("email")==null?"":request.getAttribute("email").toString() %>">
		                        	<button type="button" onclick="register()" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;">注册</button><button type="button" class="am-btn am-btn-primary am-radius" style="width: 50%;height: 100%;">返回</button>
		                        </div>
							</form>
							
	                </div>
	            </div>
			</div>
		</div>
		<script type="text/javascript">
		
		
		function sendMessage()
		{
			var email=document.getElementById("email");
			if(email.value=="")
			{
				alert("邮箱不能为空");
				return ;
			}
			var email_reg=/^([a-zA-Z]|[0-9])(\w|\-)+@[a-zA-Z0-9]+\.([a-zA-Z]{2,4})$/;
			if(!email_reg.test(email.value))
			{
				alert("请输入正确的邮箱地址");
				return ;
			}
			sendForm.action="getCode.register";
			sendForm.method="get";
			sendForm.submit();
		}
		
		function register()
		{
			var code=document.getElementById("code");
			if(code.value=="")
			{
				alert("验证码不能为空");
				return ;
			}
			
			var sendcode="<%=session.getAttribute("verification") %>";
			if(code.value!=sendcode)
			{
				alert("验证码不正确");
				return ;
			}
			
			registerForm.action="toAddInfo.register";
			registerForm.method="get";
			registerForm.submit();
			
		}
		</script>
	</body>
</html>