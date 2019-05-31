package dao;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.PreparedStatement;

public class JdbcConnactor<PrepareStatement> {
	
	protected Connection connection;
	protected PreparedStatement preparedStatement;
	protected ResultSet resultSet;
	public JdbcConnactor() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	public void getConnactor()
	{
		try {
			DriverManager.registerDriver(new Driver());
			
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/rest_order?"
					+ "characterEncoding=utf8&serverTimezone=UTC&useSSL=false","root","19961111");
		} catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
	public void closed()
	{
		
			try {
				if(null!=resultSet)
				resultSet.close();
				if(null!=preparedStatement)preparedStatement.close();
				if(null!=connection)connection.close();
			} catch (SQLException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		
	}
	
	
}
