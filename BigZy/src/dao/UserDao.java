package dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import obj.CustInfo;
import obj.RestInfo;
import obj.User;


public class UserDao extends JdbcConnactor{
	
	public UserDao() {
		super();
	}
	
	public boolean register(User user,Object info) throws SQLException
	{
		RestInfoDao restInfoDao=new RestInfoDao();
		CustInfoDao custInfoDao=new CustInfoDao();
		super.getConnactor();
		try {
			connection.setAutoCommit(false);
			preparedStatement = (PreparedStatement) connection.prepareStatement("insert  into user(username,usertype,password,mailbox) values(?,?,?,?)");
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getUserType());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getMailBox());
			int i=preparedStatement.executeUpdate();
			if(i==1) {
				if(user.getUserType().equals("餐厅管理")&&restInfoDao.addRestInfo((RestInfo)info))
				{
					connection.commit();
					return true;
				}
				else if(user.getUserType().equals("顾客")&&custInfoDao.addCustInfo((CustInfo)info))
				{
					connection.commit();
					return true;
				}
				else {
					connection.rollback();
					return false;
				}
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
	
	public User login(String username,String password,String userType)
	{
		
		super.getConnactor();
		try {
			preparedStatement = (PreparedStatement) connection.prepareStatement("select * from user where username=? and password=? and usertype=?");
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			preparedStatement.setString(3, userType);
			resultSet=preparedStatement.executeQuery();
			User user=null;
			if(resultSet.next())
			{
				String mString=resultSet.getString("mailbox");
				user=new User(username,userType,password,mString);
			}
			return user;
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
			return null;
		}
		finally {
			super.closed();
		}
	}
}
