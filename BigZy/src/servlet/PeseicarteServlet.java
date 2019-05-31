package servlet;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.PeseicarteDao;
import model.CustPeseicarte;
import obj.User;

public class PeseicarteServlet extends HttpServlet{

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
		
	}
	
	private void selectPeseicarteServlet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException 
	{
		if(req.getParameter("orderId")!=null) {
			
			Integer orderid=Integer.valueOf(req.getParameter("orderId"));
			PeseicarteDao peseicarteDao=new PeseicarteDao();
			List<CustPeseicarte> peseicartes=peseicarteDao.selectList(orderid);
			req.setAttribute("peseicartes", peseicartes);
			
			req.getRequestDispatcher("selectOrderInfo.reseInfo").forward(req, resp);
		}
	}
	
	
}
