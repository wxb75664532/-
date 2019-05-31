package obj;

import java.util.Date;

public class ReseInfo{
	private Integer orderid;
	private Integer custid;
	private Integer restid;
	private Integer peoplenum;
	private Integer tableid;
	private Date time;
	private String state;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	public Integer getCustid() {
		return custid;
	}
	public void setCustid(Integer custid) {
		this.custid = custid;
	}
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
	}
	public Integer getPeoplenum() {
		return peoplenum;
	}
	public void setPeoplenum(Integer peoplenum) {
		this.peoplenum = peoplenum;
	}
	public Integer getTableid() {
		return tableid;
	}
	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public ReseInfo(Integer orderid, Integer custid, Integer restid, Integer peoplenum, Integer tableid, Date time,String state) {
		super();
		this.orderid = orderid;
		this.custid = custid;
		this.restid = restid;
		this.peoplenum = peoplenum;
		this.tableid = tableid;
		this.time = time;
		this.state=state;
	}
	public ReseInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
}	
