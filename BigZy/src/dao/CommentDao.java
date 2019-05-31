package dao;


import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import model.CustComment;
import obj.CarteInfo;
import obj.Comment;

public class CommentDao extends JdbcConnactor{
	public CommentDao() {
		super();
	}
	
	public List<CustComment> selectList(Boolean byCust,Integer userId)
	{
		List<CustComment> custComments=new ArrayList<>();
		super.getConnactor();
		try {
			if(byCust==true)
			preparedStatement=(PreparedStatement)connection.prepareStatement("select comment.id,reseinfo.orderid, reseinfo.custid,reseinfo.restid,ty,sketch,comment.time,restinfo.restname " + 
					"from comment,restinfo,reseinfo " + 
					"where reseinfo.custid=?  and reseinfo.restid=restinfo.restid and comment.orderid=reseinfo.orderid");
			else preparedStatement=(PreparedStatement)connection.prepareStatement("select comment.id,reseinfo.orderid, reseinfo.custid,reseinfo.restid,ty,sketch,comment.time,restinfo.restname "+ 
					"from comment,restinfo,reseinfo " + 
					"where reseinfo.custid=?  and reseinfo.restid=restinfo.restid and comment.orderid=reseinfo.orderid");
			
			preparedStatement.setInt(1, userId);
			resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				Integer id=resultSet.getInt("id");
				Integer custid=resultSet.getInt("custid");
				Integer restId=resultSet.getInt("restid");
				String ty=resultSet.getString("ty");
				String sketch=resultSet.getString("sketch");
				Date time=resultSet.getTimestamp("time");
				String restname=resultSet.getString("restname");
				Integer orderid=resultSet.getInt("orderid");
				CustComment comment=new CustComment(id,custid,restId,ty,sketch,time,restname,orderid);
				custComments.add(comment);
			}
			return custComments;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally {
			super.closed();
		}
	}
	
	public boolean addComment(Comment comment)
	{
		if(comment.getOrderId()==null||
				comment.getSketch()==null||comment.getTime()==null||
						comment.getTy()==null)return false;
		super.getConnactor();
		try {
			preparedStatement= (PreparedStatement) connection.prepareStatement("insert ignore into comment(orderid,ty,sketch,time) values(?,?,?,?)");
			preparedStatement.setInt(1,comment.getOrderId());
			preparedStatement.setString(2, comment.getTy());
			preparedStatement.setString(3, comment.getSketch());
			preparedStatement.setTimestamp(4, new Timestamp(comment.getTime().getTime()));
			int result=preparedStatement.executeUpdate();
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
	
	public boolean removeComment(Integer commentId)
	{
		super.getConnactor();
		try {
			preparedStatement= (PreparedStatement) connection.prepareStatement("delete ignore from comment where id=?");
			preparedStatement.setInt(1,commentId);
			int result=preparedStatement.executeUpdate();
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
}
