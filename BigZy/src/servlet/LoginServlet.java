package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CustInfoDao;
import dao.RestInfoDao;
import dao.UserDao;
import obj.CustInfo;
import obj.ReseInfo;
import obj.RestInfo;
import obj.User;

public class LoginServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserDao userDao=new UserDao();
		req.setCharacterEncoding("utf8");
		String username =req.getParameter("username");
		String password=req.getParameter("password");
		String userType=req.getParameter("userType");
		User user=userDao.login(username, password,userType);
		
		boolean isLogin=user==null?false:true;
		req.setAttribute("isLogin", isLogin);
		
		
		if(isLogin==true) {
			req.setAttribute("userType", userType);
			req.getSession().setAttribute("userInfo", user);
			if(userType.equals("¹Ë¿Í"))
			{
				CustInfoDao custInfoDao=new CustInfoDao();
				CustInfo custInfo=custInfoDao.selectByUserName(username);
				req.getSession().setAttribute("custInfo", custInfo);
			}else {
				RestInfoDao restInfoDao=new RestInfoDao();
				RestInfo restInfo=restInfoDao.selectByUserName(username);
				req.getSession().setAttribute("restInfo", restInfo);
			}
		}
		req.getRequestDispatcher("/Login.jsp").forward(req, resp);
	}
	
}
