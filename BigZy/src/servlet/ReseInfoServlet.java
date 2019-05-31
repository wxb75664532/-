package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mysql.fabric.xmlrpc.base.Data;

import dao.ReselnfoDao;
import dao.TableInfoDao;
import model.CustReseInfo;
import model.CustPeseicarte;
import obj.CustInfo;
import obj.ReseInfo;
import obj.RestInfo;
import obj.TableInfo;
import obj.User;

public class ReseInfoServlet extends HttpServlet{

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
		}catch(Exception e)
		{
        	e.printStackTrace();
		}
	}

	private void cancelOrderInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{

		User user=(User) req.getSession().getAttribute("userInfo");
		PrintWriter out = resp.getWriter();
		if(req.getParameter("orderId")!=null) {
			
			Integer orderid=Integer.valueOf(req.getParameter("orderId"));
			ReselnfoDao reselnfoDao=new ReselnfoDao();
			boolean isSuccess=reselnfoDao.updateState(orderid, "��ȡ��");
			if(isSuccess==true)
			{
				out.print("<script>alert('ȡ���ɹ�'); window.location.href='selectOrderInfo.reseInfo' </script>");
			}else {
				out.print("<script>alert('ȡ��ʧ��'); window.location.href='selectOrderInfo.reseInfo' </script>");
			}
		}else {
			out.print("<script>alert('ȡ��ʧ�ܣ�δ֪����'); window.location.href='selectOrderInfo.reseInfo' </script>");
		}
	}
	
	
	private void selectOrderInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		User user=(User) req.getSession().getAttribute("userInfo");
		boolean isCust=user.getUserType().equals("�˿�")?true:false;
		ReselnfoDao reselnfoDao=new ReselnfoDao();
		List<CustReseInfo> custReseInfos=new ArrayList<>();
		
		if(isCust) {
			CustInfo custInfo=(CustInfo) req.getSession().getAttribute("custInfo");
			custReseInfos=reselnfoDao.selectList(isCust, custInfo.getCustid());
		}else {
			RestInfo restInfo=(RestInfo) req.getSession().getAttribute("restInfo");
			custReseInfos=reselnfoDao.selectList(isCust, restInfo.getRestid());
		}
		for(CustReseInfo custReseInfo:custReseInfos)
		{
			if(custReseInfo.getState().equals("Ԥ����"))
			{
				Date date=new Date();
				boolean isCp=date.getTime()-custReseInfo.getTime().getTime()>7200000?true:false;
				if(isCp==true&&reselnfoDao.updateState(custReseInfo.getOrderid(), "�����"))
				{
					custReseInfo.setState("�����");
				}
			}
		}
		req.setAttribute("custReseInfos", custReseInfos);
		String url=isCust==true?"/custReseInfos.jsp":"/restReseInfos.jsp";
		req.getRequestDispatcher(url).forward(req, resp);
	}
	
	private void addReseInfo(HttpServletRequest req,HttpServletResponse resp) throws ServletException,IOException
	{
		PrintWriter out = resp.getWriter();
		TableInfoDao tableInfoDao=new TableInfoDao();
		ReselnfoDao reselnfoDao=new ReselnfoDao();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		try {
			Date date=sdf.parse(req.getParameter("date"));
			Integer peopleNum=Integer.valueOf(req.getParameter("peopleNum"));
			Integer restId=Integer.valueOf(req.getParameter("restId"));
			CustInfo user=(CustInfo) req.getSession().getAttribute("custInfo");
			String restName=req.getParameter("restName");
			String carteIdString=req.getParameter("carteids");
			
			List<TableInfo> tableInfos=tableInfoDao.selectListByTime(restId, date);
			if(tableInfos==null)
			{
				out.print("<script>alert('��ʱ���û�к���λ�� ������ѡ����һ��ʱ��');window.location.href='custSelectByRestId.restInfo?restId="+restId+"&restName="+restName+"&carteids="+carteIdString+"'</script>");
				out.flush();
				out.close();
				return ;
			}
			
			String[] carteIdStrings=carteIdString.split(",");
			List<Integer> carteIds=new ArrayList<>();
			for(int i=0;i<carteIdStrings.length;i++)
			{
				carteIds.add(Integer.valueOf(carteIdStrings[i]));
			}
			for(TableInfo tableInfo:tableInfos)
			{
				if(tableInfo.getCapacity()>=peopleNum)
				{
					
					ReseInfo reseInfo=new ReseInfo(0,user.getCustid(),restId,peopleNum,tableInfo.getTableid(),date,"Ԥ����");
					
					
					boolean isSuccess=reselnfoDao.addreseInfo(reseInfo,carteIds);
					if(isSuccess) {
						out.print("<script>alert('��Ӷ����ɹ�'); window.location.href='selectOrderInfo.reseInfo' </script>");
					}
					else out.print("<script>alert('δ֪����');window.location.href='custSelectList.restInfo'</script>");
					out.flush();
					out.close();
					return;
				}
			}

			out.print("<script>alert('��ʱ���û�к���λ�� ������ѡ����һ��ʱ��');window.location.href='custSelectByRestId.restInfo?restId="+restId+"&restName="+restName+"&carteids="+carteIdString+"'</script>");

		
		} catch (ParseException e) {
			// TODO �Զ����ɵ� catch ��
			e.printStackTrace();
		}catch (ClassCastException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO �Զ����ɵ� catch ��
			System.out.println("���ݲ�������");
		}
		
	}
	
}
