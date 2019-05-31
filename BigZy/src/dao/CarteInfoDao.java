package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import obj.CarteInfo;

public class CarteInfoDao extends JdbcConnactor{
	
	public CarteInfoDao(){
		super();
	}
	
	public boolean addCarteInfo(CarteInfo carteInfo)
	{
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement) connection.prepareStatement("insert ignore into carteinfo(foodname,unitprice,category,intro,restid) values(?,?,?,?,?)");
			preparedStatement.setString(1, carteInfo.getFoodName());
			preparedStatement.setInt(2, carteInfo.getUnitPrice());
			preparedStatement.setString(3, carteInfo.getCategory());
			preparedStatement.setString(4, carteInfo.getIntro());
			preparedStatement.setInt(5,carteInfo.getRestid());
			int x=preparedStatement.executeUpdate();
			if(x>0)return true;
			else return false;
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		
	}
	
	public List<Integer> deleteLists(List<Integer> carteIds,Integer restid)
	{
		List<Integer> ids=new ArrayList<>();
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement) connection.prepareStatement("delete ignore from carteinfo where id=? and id not in" + 
					"(select peseicarte.carteid as id from peseicarte,reseinfo where reseinfo.orderid=peseicarte.orderid and reseinfo.restid=?)");
			
			for(Integer id:carteIds)
			{
				preparedStatement.setInt(1,id);
				preparedStatement.setInt(2,restid);
				preparedStatement.addBatch();
			}
			int[] success=preparedStatement.executeBatch();
			
			for(int i=0;i<success.length;i++)
			{
				if(success[i]<=0)
				{
					ids.add(carteIds.get(i));
				}
			}
			return ids;
		}catch (Exception e) {
			// TODO: handle exception
			return new ArrayList<>();
		}finally {
			super.closed();
		}
	}
	
	public List<CarteInfo> selectLists(Integer restId)
	{
		List<CarteInfo> carteInfos=new ArrayList<>();
		super.getConnactor();
		
		try {
			
			preparedStatement=(PreparedStatement) connection.prepareStatement("select * from carteinfo where restid=?");
			preparedStatement.setInt(1, restId);
			resultSet=preparedStatement.executeQuery();
			while (resultSet.next()) {
				Integer id=resultSet.getInt("id");
				String foodName=resultSet.getString("foodname");
				Integer unitPrice=resultSet.getInt("unitprice");
				String category=resultSet.getString("category");
				String intro=resultSet.getString("intro");
				String picture=resultSet.getString("pictrue");
				CarteInfo carteInfo=new CarteInfo(id,foodName,unitPrice,category,intro,picture,restId);
				carteInfos.add(carteInfo);
				
			}
			return carteInfos;
		}catch (SQLException e) {
			// TODO: handle exception
			
			e.printStackTrace();
			return null;
		}
	}
	
}
