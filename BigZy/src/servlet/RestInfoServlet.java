package servlet;

import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarteInfoDao;
import dao.CommentDao;
import dao.CustInfoDao;
import dao.RestInfoDao;
import model.CustComment;
import obj.CarteInfo;
import obj.Comment;
import obj.CustInfo;
import obj.RestInfo;

public class RestInfoServlet extends HttpServlet{

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
	
	private void custSelectList(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		RestInfoDao restInfoDao=new RestInfoDao();
		List<RestInfo> restInfos=restInfoDao.selectRestInfo();
		req.setAttribute("restInfos",restInfos);
		req.getRequestDispatcher("/custRestInfos.jsp").forward(req, resp);
		
	}
	
	private void custSelectByRestId(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		
		CarteInfoDao carteInfoDao=new CarteInfoDao();
		CommentDao commentDao=new CommentDao();
		Integer restId=Integer.valueOf(req.getParameter("restId"));
		List<CarteInfo> carteInfos=carteInfoDao.selectLists(restId);
		List<CustComment> comments=commentDao.selectList(false, restId);
		String restName=req.getParameter("restName");
		req.setAttribute("custComments", comments);
		req.setAttribute("carteInfos", carteInfos);
		req.setAttribute("restName", restName);
		req.setAttribute("restId", restId);
		if(req.getParameter("carteids")!=null)
		{
			String carteIds=req.getParameter("carteids");
			req.setAttribute("carteids", carteIds);
		}
		
		req.getRequestDispatcher("/custRestInfoById.jsp").forward(req, resp);
		
	}
	
}
