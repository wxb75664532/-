package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CommentDao;
import model.CustComment;
import obj.Comment;
import obj.CustInfo;
import obj.RestInfo;
import obj.User;

public class CommentServlet extends HttpServlet{

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
	
	
	private void displayCommentArea(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		req.setAttribute("isComment", true);
		if(req.getParameter("orderId")!=null)
		{
			Integer orderId=Integer.valueOf(req.getParameter("orderId"));
			req.setAttribute("commentOrderId", orderId);
			req.getRequestDispatcher("selectOrderInfo.reseInfo").forward(req, resp);
		}
	}
	
	private void deleteCommentServlet(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{

		User user=(User) req.getSession().getAttribute("userInfo");
		PrintWriter out = resp.getWriter();
		if(req.getParameter("orderId")!=null){
			CommentDao commentDao=new CommentDao();
			Integer id=Integer.valueOf(req.getParameter("orderId"));
			boolean isSuccess=commentDao.removeComment(id);
			if(isSuccess==true)
			{
				out.print("<script>alert('É¾³ý³É¹¦£¡'); window.location.href='selectComment.comment' </script>");
			}else {
				out.print("<script>alert('É¾³ýÊ§°Ü£¡'); window.location.href='selectComment.comment' </script>");
			}
		}
	}
	
	private void selectComment(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{

		User user=(User) request.getSession().getAttribute("userInfo");
		boolean isCust=user.getUserType().equals("¹Ë¿Í")?true:false;
		String url="";
		url=isCust==true?"/custDisplayComments.jsp":"/restComments.jsp";
		CommentDao commentDao=new CommentDao();
		List<CustComment> comments=new ArrayList<>();
		
		if(isCust)
		{
			CustInfo custInfo=(CustInfo) request.getSession().getAttribute("custInfo");
			comments=commentDao.selectList(isCust, custInfo.getCustid());
		}
		else {
			RestInfo restInfo=(RestInfo)request.getSession().getAttribute("restInfo");
			comments=commentDao.selectList(isCust, restInfo.getRestid());
		}
		request.setAttribute("comments", comments);
		request.getRequestDispatcher(url).forward(request, response);
	}
	
	private void addComment(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException
	{

		User user=(User) request.getSession().getAttribute("userInfo");
		PrintWriter out = response.getWriter();
		if(request.getParameter("orderId")==null||request.getParameter("orderId").toString().equals("null"))
		{
			out.print("<script>alert('Ìí¼ÓÊ§°Ü£¬Î´Öª´íÎó'); window.location.href='selectComment.comment' </script>");
		}
		String type=request.getParameter("commentType");
		String sketch=request.getParameter("sketch");
		Integer orderid=Integer.valueOf(request.getParameter("orderId"));
		Date date=new Date();
		Comment comment=new Comment();
		comment.setOrderId(orderid);
		comment.setSketch(sketch);
		comment.setTime(date);
		comment.setTy(type);
		
		CommentDao commentDao=new CommentDao();
		boolean isSuccess=commentDao.addComment(comment);
		if(isSuccess==true)
		{
			out.print("<script>alert('Ìí¼Ó³É¹¦'); window.location.href='selectComment.comment' </script>");
		}else {
			out.print("<script>alert('Ìí¼ÓÊ§°Ü£¬Î´Öª´íÎó'); window.location.href='selectComment.comment' </script>");
		}
	}
	
}
