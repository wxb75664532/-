package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.TableInfoDao;
import obj.RestInfo;
import obj.TableInfo;

public class TableInfoServlet extends HttpServlet{

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
	
	private void tableSelectList(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
		RestInfo restInfo=(RestInfo) req.getSession().getAttribute("restInfo");
		if(req.getAttribute("isFaild")!=null)
		{
			List<Integer> isFaild=(List<Integer>) req.getAttribute("isFaild");
			req.setAttribute("isFaild", isFaild);
		}
		TableInfoDao tableInfoDao=new TableInfoDao();
		List<TableInfo> tableInfos=tableInfoDao.selectList(restInfo.getRestid());
		req.setAttribute("tableList", tableInfos);
		req.getRequestDispatcher("/restTableList.jsp").forward(req, resp);
	}
	
	private void deleteTables(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {
	
		RestInfo restInfo=(RestInfo) req.getSession().getAttribute("restInfo");
		TableInfoDao tableInfoDao=new TableInfoDao();
		String tableString=req.getParameter("tableIds");
		String[] tables=tableString.split(",");
		List<Integer> tableIds=new ArrayList<>();
		for(int i=0;i<tables.length;i++)
		{
			tableIds.add(Integer.valueOf(tables[i]));
		}
		List<Integer> isFaild=tableInfoDao.deleteTable(restInfo.getRestid(), tableIds);
		req.setAttribute("isFaild", isFaild);
		req.getRequestDispatcher("tableSelectList.tableInfo").forward(req, resp);
	}
	
	private void addTableInfo(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException {

		PrintWriter out=resp.getWriter();
		TableInfoDao tableInfoDao=new TableInfoDao();
		try {
			Integer tableid=Integer.valueOf(req.getParameter("tableid"));
			Integer people=Integer.valueOf(req.getParameter("peopleNum"));
			String position=req.getParameter("position");
			RestInfo restInfo=(RestInfo) req.getSession().getAttribute("restInfo");
			
			TableInfo tableInfo=new TableInfo(-1,tableid,people,position,restInfo.getRestid());
			boolean isSuccess=tableInfoDao.addTable(tableInfo);
			
			if(isSuccess==true)
			{
				out.println("<script>alert('添加成功');window.location.href='tableSelectList.tableInfo'</script>");
			}else {
				out.println("<script>alert('添加失败');window.location.href='tableSelectList.tableInfo'</script>");
			}
			
		}catch (ClassCastException e) {
			// TODO: handle exception
			out.println("<script>alert('参数错误');window.location.href='tableSelectList.tableInfo'</script>");
		}
	}
	
}
