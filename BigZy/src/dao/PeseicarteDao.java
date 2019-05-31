package dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.CustPeseicarte;
import obj.TableInfo;

public class PeseicarteDao extends JdbcConnactor{
	
	public PeseicarteDao() {
		super();
	}
	
	public void addFood(Integer orderid,List<Integer> carteids) throws Exception {
		super.getConnactor();
		connection.setAutoCommit(false);
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("insert into peseicarte(orderid,carteid) values(?,?)");
			
			for(int i=0;i<carteids.size();i++) {
				preparedStatement.setInt(1, orderid);
				preparedStatement.setInt(2, carteids.get(i));
				preparedStatement.addBatch();

				if(i%5==0)
				{
					preparedStatement.executeBatch();
				}
			}
			preparedStatement.executeBatch();
			
			connection.commit();
			connection.setAutoCommit(true);
		}catch (Exception e) {
			// TODO: handle exception
			connection.rollback();
			connection.setAutoCommit(true);
			throw new Exception(e);
		}
		finally {
			super.closed();
		}
	}
	
	public List<CustPeseicarte> selectList(Integer orderid){
		List<CustPeseicarte> peseicartes=new ArrayList<>();
		super.getConnactor();
		try {
			preparedStatement=(PreparedStatement)connection.prepareStatement("select carteinfo.id,foodname,unitprice from peseicarte,carteinfo " + 
					"where orderid=? and  peseicarte.carteid=carteinfo.id");
			preparedStatement.setInt(1, orderid);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Integer id=resultSet.getInt("id");
				Integer count=resultSet.getInt("unitprice");
				String foodname=resultSet.getString("foodname");
				CustPeseicarte peseicarte=new CustPeseicarte(id,foodname,count);
				peseicartes.add(peseicarte);
			}
			return peseicartes;
		
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}finally {
			super.closed();
		}
	}
}
