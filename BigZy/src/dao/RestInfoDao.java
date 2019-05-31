package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import obj.CustInfo;
import obj.RestInfo;

public class RestInfoDao extends JdbcConnactor{

	public RestInfoDao() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public boolean addRestInfo(RestInfo restInfo) throws SQLException
	{
		if(restInfo.getRestname()==null||restInfo.getAddress()==null||restInfo.getCharacters()==null||
				restInfo.getUsername()==null)return false;
		
		super.getConnactor();
		try {
			connection.setAutoCommit(false);
			preparedStatement = (PreparedStatement) connection.prepareStatement("insert into restInfo(restname,address,praiserate,characters,username) values(?,?,?,?,?)");

			preparedStatement.setString(1,restInfo.getRestname());
			preparedStatement.setString(2, restInfo.getAddress());
			preparedStatement.setFloat(3,restInfo.getPraiserate());
			preparedStatement.setString(4, restInfo.getCharacters());
			preparedStatement.setString(5, restInfo.getUsername());
			int result=preparedStatement.executeUpdate();
			if(result>0) {
				connection.commit();
				return true;
			}
			else
			{
				return false;
			}
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			connection.rollback();
			return false;
		}
		finally {
			connection.setAutoCommit(true);
			super.closed();
		}
	}
	
	
	
	public RestInfo selectByUserName(String username)
	{
		super.getConnactor();
		RestInfo restInfo=null;
		List<RestInfo> restInfos=new ArrayList<>();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("select * from restinfo where username=?");
			preparedStatement.setString(1,username);
			
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Integer restId=resultSet.getInt("restid");
				String restname=resultSet.getString("restname");
				String address=resultSet.getString("address");
				Float praiserate=resultSet.getFloat("praiserate");
				String characters=resultSet.getString("characters");
				restInfo=new RestInfo(restId,restname,address,praiserate,characters,username);

			}
			return restInfo;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
	
	public List<RestInfo> selectRestInfo()
	{
		super.getConnactor();
		List<RestInfo> restInfos=new ArrayList<>();
		try {
			
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from restinfo");
			resultSet=preparedStatement.executeQuery();
			if(resultSet.wasNull())return null;
			while(resultSet.next())
			{
				Integer restId=resultSet.getInt("restid");
				String restname=resultSet.getString("restname");
				String address=resultSet.getString("address");
				Float praiserate=resultSet.getFloat("praiserate");
				String characters=resultSet.getString("characters");
				String username=resultSet.getString("username");
				RestInfo restInfo=new RestInfo(restId,restname,address,praiserate,characters,username);
				restInfos.add(restInfo);
			}
			return restInfos;
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
}
