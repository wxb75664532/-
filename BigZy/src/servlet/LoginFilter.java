package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import obj.User;

public class LoginFilter implements Filter{


	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain arg2)
			throws IOException, ServletException {
		// TODO 自动生成的方法存根
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8"); 
		response.setCharacterEncoding("utf-8"); 
		HttpServletRequest req=(HttpServletRequest)request;
		HttpServletResponse resp=(HttpServletResponse)response;
		PrintWriter out=resp.getWriter();
		User user=(User)req.getSession().getAttribute("userInfo");
		String servletName=req.getServletPath();
		
		String[] urls = {"/json",".js",".css",".ico",".jpg",".png","Login","Register","register"};
	    boolean flag = true;
	    for (String str : urls) {
	        if (servletName.indexOf(str) != -1) {
	            flag =false;
	            break;
	        }
	    }
		if(flag&&user==null) {
			out.print("<script>alert('您还没有登录，请登录...'); window.location='Login.jsp' </script>");
		}
		else{
			arg2.doFilter(req, resp);
		}
	}
}
