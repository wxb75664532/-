package dao;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import obj.CarteInfo;
import obj.TableInfo;

public class TableInfoDao extends JdbcConnactor{

	public TableInfoDao() {
		super();
		// TODO 自动生成的构造函数存根
	}  
	
	public List<TableInfo> selectList(Integer restId){
		List<TableInfo> tableInfos=new ArrayList<>();
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("select * from tableinfo where restid=?");
			preparedStatement.setInt(1, restId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Integer id=resultSet.getInt("id");
				Integer tableid=resultSet.getInt("tableid");
				Integer capacity=resultSet.getInt("capacity");
				String position=resultSet.getString("position");
				TableInfo tableInfo=new TableInfo(id,tableid,capacity,position,restId);
				tableInfos.add(tableInfo);
			}
			return tableInfos;
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
	
	public boolean addTable(TableInfo tableInfo) {
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("insert ignore into tableinfo(tableid,capacity,position,restid) values(?,?,?,?)");
			preparedStatement.setInt(1, tableInfo.getTableid());
			preparedStatement.setInt(2,tableInfo.getCapacity());
			preparedStatement.setString(3, tableInfo.getPosition());
			preparedStatement.setInt(4, tableInfo.getRestid());
			Integer result=preparedStatement.executeUpdate();
			if(result>0)return true;
			else return false;
		
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}finally {
			super.closed();
		}
	}
	public List<Integer> deleteTable(Integer restid,List<Integer> tableids)
	{
		super.getConnactor();
		Date date=new Date();
		List<Integer> ids=new ArrayList<>();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("delete ignore from tableinfo where id=? and id in(select n.id from(" + 
					"select id from tableinfo where  restid=? and tableid not in(" + 
					"select tableid from reseinfo where restid=? and (state ='预定中'  and (? < TIMESTAMPADD(HOUR,2,time)))) ) as n)");
			
			for(Integer id:tableids) {
				preparedStatement.setInt(1,id);
				preparedStatement.setInt(2,restid);
				preparedStatement.setInt(3,restid);
				preparedStatement.setTimestamp(4, new Timestamp(date.getTime()));
				
				preparedStatement.addBatch();
			}
			
			int[] success=preparedStatement.executeBatch();
			for(int i=0;i<success.length;i++)
			{
				if(success[i]<=0)
				{
					ids.add(tableids.get(i));
				}
			}
			
			return ids;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
	public List<TableInfo> selectListByTime(Integer restId,Date time){
		List<TableInfo> tableInfos=new ArrayList<>();
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("select * from tableinfo where restid=? and tableid not in (select tableid from reseinfo where state='预定中' and (? between TIMESTAMPADD(HOUR,-2,time) and TIMESTAMPADD(HOUR,2,time))) order by capacity");
			preparedStatement.setInt(1, restId);
			preparedStatement.setTimestamp(2, new Timestamp(time.getTime()));
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Integer id=resultSet.getInt("id");
				Integer tableid=resultSet.getInt("tableid");
				Integer capacity=resultSet.getInt("capacity");
				String position=resultSet.getString("position");
				TableInfo tableInfo=new TableInfo(id,tableid,capacity,position,restId);
				tableInfos.add(tableInfo);
			}
			return tableInfos;
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
}
