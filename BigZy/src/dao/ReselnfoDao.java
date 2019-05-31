package dao;

import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.CustReseInfo;
import obj.ReseInfo;

public class ReselnfoDao extends JdbcConnactor{
	public ReselnfoDao() {
		super();
	}
	
	public List<CustReseInfo> selectList(Boolean isCust,Integer id)
	{
		List<CustReseInfo> custReseInfos=new ArrayList<>();
		super.getConnactor();
		try {
			if(isCust==true)
			preparedStatement=(PreparedStatement)connection.prepareStatement("select orderid,reseinfo.restid,reseinfo.peoplenum,tableid,time,state,restinfo.restname,restinfo.address " + 
					"from reseinfo,restinfo " + 
					"where reseinfo.restid=restinfo.restid and custid=?");
			else preparedStatement=(PreparedStatement)connection.prepareStatement("select orderid,reseinfo.restid,reseinfo.peoplenum,tableid,time,state,restinfo.restname,restinfo.address " + 
					"from reseinfo,restinfo " + 
					"where reseinfo.restid=restinfo.restid and reseinfo.restid=?");
			
			preparedStatement.setInt(1, id);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Integer orderid=resultSet.getInt("orderid");
				Integer restid=resultSet.getInt("restid");
				Integer peoplenum=resultSet.getInt("peoplenum");
				Integer tableid=resultSet.getInt("tableid");
				Date time=resultSet.getTimestamp("time");
				String state=resultSet.getString("state");
				String restname=resultSet.getString("restname");
				String address=resultSet.getString("address");
				CustReseInfo custReseInfo=new CustReseInfo(orderid,restid,peoplenum,tableid,time,state,restname,address);
				custReseInfos.add(custReseInfo);
			}
			return custReseInfos;
		}catch (SQLException e) {
			e.printStackTrace();
			// TODO: handle exception
			return null;
		}finally {
			super.closed();
		}
	}
	
	public boolean updateState(Integer orderid,String state)
	{
		super.getConnactor();
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("update ignore reseinfo set state=? where orderid=?");

			preparedStatement.setString(1,state);
			preparedStatement.setInt(2, orderid);
			int result=preparedStatement.executeUpdate();
			if(result>0)return true;
			else return false;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return false;
		}
		finally {
			super.closed();
		}
	}
	public boolean addreseInfo(ReseInfo reseInfo,List<Integer> carteids) throws SQLException
	{
		PeseicarteDao peseicarteDao=new PeseicarteDao();
		super.getConnactor();
		connection.setAutoCommit(false);
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("insert into reseinfo(custid,restid,peoplenum,tableid,time,state) values(?,?,?,?,?,?)",1);
			preparedStatement.setInt(1,reseInfo.getCustid());
			preparedStatement.setInt(2, reseInfo.getRestid());
			preparedStatement.setInt(3, reseInfo.getPeoplenum());
			preparedStatement.setInt(4, reseInfo.getTableid());
			preparedStatement.setTimestamp(5, new Timestamp(reseInfo.getTime().getTime()));
			preparedStatement.setString(6, reseInfo.getState());
			int x=preparedStatement.executeUpdate();
			
			if(x>0) {
				resultSet=preparedStatement.getGeneratedKeys();
				if(resultSet.next())
				{
					Integer orderid=resultSet.getInt(1);

					peseicarteDao.addFood(orderid, carteids);
					connection.commit();
					connection.setAutoCommit(true);
					return true;
				}
			}
			return false;
			
		} catch (Exception e) {
			// TODO 自动生成的 catch 块
			connection.rollback();
			connection.setAutoCommit(true);
			return false;
		}
		finally {
			super.closed();
		}
	}
}
