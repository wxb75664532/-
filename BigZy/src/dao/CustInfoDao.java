package dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import obj.CustInfo;
import obj.RestInfo;

public class CustInfoDao extends JdbcConnactor{
	
	
	public CustInfoDao() {
		super();
		
		// TODO 自动生成的构造函数存根
	}
	
	
	public boolean addCustInfo(CustInfo custInfo) throws SQLException
	{
		if(custInfo.getCustname()==null||custInfo.getUsername()==null)
		{
			return false;
		}
		super.getConnactor();
		try {
			connection.setAutoCommit(false);
			preparedStatement = (PreparedStatement) connection.prepareStatement("insert  into custinfo(custname,number,address,taste,username) values(?,?,?,?,?)");
			preparedStatement.setString(1,custInfo.getCustname());
			preparedStatement.setString(2, custInfo.getNumber());
			preparedStatement.setString(3,custInfo.getAddress());
			preparedStatement.setString(4, custInfo.getTaste());
			preparedStatement.setString(5, custInfo.getUsername());
			int result=preparedStatement.executeUpdate();
			if(result>0)
			{
				connection.commit();
				return true;
			}
			else return false;
		} catch (SQLException e) {
			connection.rollback();
			return false;
		}
		finally {
			connection.setAutoCommit(true);
			super.closed();
		}
	}
	
	public CustInfo selectByUserName(String username)
	{
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("select * from custinfo where username=?");
			preparedStatement.setString(1, username);
			resultSet=preparedStatement.executeQuery();
			if(resultSet.next())
			{
				Integer custid=resultSet.getInt("custid");
				String custname=resultSet.getString("custname");
				String number=resultSet.getString("number");
				String  address=resultSet.getString("address");
				String taste=resultSet.getString("taste");
				String usernamme=resultSet.getString("username");
				CustInfo custInfo=new CustInfo(custid,custname,number,address,taste,username);
				return custInfo;
			}
			
			return null;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
}
