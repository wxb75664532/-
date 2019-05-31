package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDao;
import obj.CustInfo;
import obj.RestInfo;
import obj.User;
import tool.MailUtil;


///
///
///从request中传递username，password，mailBox进来
///
public class RegisterServlet extends  HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String url =req.getRequestURI();
		String methodName=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		Method method=null;
		try {
			
			method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url =req.getRequestURI();
		String methodName=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		Method method=null;
		try {
			
			method=getClass().getDeclaredMethod(methodName, HttpServletRequest.class,HttpServletResponse.class);
			method.invoke(this,req, resp);
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	
	private void custInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String custName=req.getParameter("custName");
		String address=req.getParameter("address");
		String number=req.getParameter("number");
		String taste=req.getParameter("taste");
		String email=req.getParameter("email");
		String userType=req.getParameter("userType");
		
		User user=new User(username,userType,password,email);
		CustInfo custInfo=new CustInfo(-1,custName,number,address,taste,username);
		UserDao userDao=new UserDao();
		try {
			if(userDao.register(user, custInfo))
			{
				out.println("<script>alert('注册成功');window.location.href='Login.jsp'</script>");
			}
			else {
				out.println("<script>alert('用户名已存在');window.location.href='toAddInfo.register?email="+email+"&userType="+userType+"'</script>");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			
			out.println("<script>alert('出现异常错误');window.location.href='Register.jsp'</script>");
		}
	}
	
	private void restInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out=resp.getWriter();
		
		String username=req.getParameter("username");
		String password=req.getParameter("password");
		String restName=req.getParameter("restName");
		String address=req.getParameter("address");
		String character=req.getParameter("character");
		String email=req.getParameter("email");
		String userType=req.getParameter("userType");
		
		User user=new User(username,userType,password,email);
		RestInfo restInfo=new RestInfo(-1,restName,address,0f,character,username);
		UserDao userDao=new UserDao();
		try {
			if(userDao.register(user, restInfo))
			{
				out.println("<script>alert('注册成功');window.location.href='Login.jsp'</script>");
			}
			else {
				out.println("<script>alert('用户名已存在');window.location.href='toAddInfo.register?email="+email+"&userType="+userType+"'</script>");
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			
			out.println("<script>alert('出现异常错误');window.location.href='Register.jsp'</script>");
		}
	}
	
	
	private void getCode(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email=req.getParameter("email");
		Random random=new Random();
		String count=String.valueOf(random.nextInt(100000)+10000);
		Boolean isSuccess=true;
		//Boolean isSuccess=MailUtil.send(email, count);
		//if(isSuccess)req.getSession().setAttribute("verification",count);
		req.getSession().setAttribute("verification",20001);
		req.setAttribute("isSuccess", isSuccess);
		req.setAttribute("email",email);
		req.getRequestDispatcher("/Register.jsp").forward(req, resp);
	}
	
	private void toAddInfo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		
		String userType=req.getParameter("userType");
		String email=req.getParameter("emailx");
		req.setAttribute("email", email);
		req.setAttribute("userType", userType);
		if(userType.equals("顾客"))
		{
			req.getRequestDispatcher("/RegisterCustInfo.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/RegisterRestInfo.jsp").forward(req, resp);
		}
		
	}
}
