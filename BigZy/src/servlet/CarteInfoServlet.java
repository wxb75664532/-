package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.CarteInfoDao;
import obj.CarteInfo;
import obj.RestInfo;

public class CarteInfoServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getRequestURI();
		String name=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		Method method=null;
		try {
			method=getClass().getDeclaredMethod(name,HttpServletRequest.class,HttpServletResponse.class );
			method.invoke(this,req, resp);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String url=req.getRequestURI();
		String name=url.substring(url.lastIndexOf("/")+1, url.lastIndexOf("."));
		Method method=null;
		try {
			method=getClass().getDeclaredMethod(name,HttpServletRequest.class,HttpServletResponse.class );
			method.invoke(this,req, resp);
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}

	private void carteSelectList(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		
		if(request.getAttribute("ids")!=null)
		{
			List<Integer> carteIds=(List<Integer>) request.getAttribute("ids");
			String idString="";
			for(Integer id:carteIds)
			{
				idString+=id+" ";
			}
			request.setAttribute("notSuccessIDs", idString);
		}
		RestInfo restInfo=(RestInfo) request.getSession().getAttribute("restInfo");
		CarteInfoDao carteInfoDao=new CarteInfoDao();
		List<CarteInfo> carteInfos=carteInfoDao.selectLists(restInfo.getRestid());
		request.setAttribute("carteInfos", carteInfos);
		request.getRequestDispatcher("/restCarteInfo.jsp").forward(request, response);
	}
	
	private void deleteCartes(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		CarteInfoDao carteInfoDao=new CarteInfoDao();
		RestInfo restInfo=(RestInfo) request.getSession().getAttribute("restInfo");
		String carteIdString=request.getParameter("carteIds");
		String[] cartes=carteIdString.split(",");
		List<Integer> carteIds=new ArrayList<>();
		
		for(int i=0;i<cartes.length;i++)
		{
			carteIds.add(Integer.valueOf(cartes[i]));
		}
		
		List<Integer> ids=carteInfoDao.deleteLists(carteIds,restInfo.getRestid());
		request.setAttribute("ids", ids);
		request.getRequestDispatcher("carteSelectList.carteInfo").forward(request, response);
		
	}
	
	private void addCarteInfo(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException
	{
		CarteInfoDao carteInfoDao=new CarteInfoDao();
		PrintWriter out=response.getWriter();
		try {
			RestInfo restInfo=(RestInfo) request.getSession().getAttribute("restInfo");
			
			String foodName=request.getParameter("foodName");
			Integer unitPrice=Integer.valueOf(request.getParameter("unitPrice"));
			String category=request.getParameter("category");
			String intro=request.getParameter("intro");
			
			CarteInfo carteInfo=new CarteInfo(-1,foodName,unitPrice,category,intro,"无",restInfo.getRestid());
			boolean isSuccess=carteInfoDao.addCarteInfo(carteInfo);
			if(isSuccess) {
				out.println("<script>alert('添加成功');window.location.href='carteSelectList.carteInfo';</script>");
			}else {
				out.println("<script>alert('添加失败');window.location.href='carteSelectList.carteInfo';</script>");
			}
		}catch (ClassCastException e) {
			// TODO: handle exception
			out.println("<script>alert('填写参数有误，请重新填写');window.location.href='carteSelectList.carteInfo';</script>");
		}
	}
}
