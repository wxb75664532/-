package model;

import java.util.Date;


public class CustReseInfo {
	
	private Integer orderid;
	private Integer restid;
	private Integer peoplenum;
	private Integer tableid;
	private Date time;
	private String state;
	private String restname;
	private String address;
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
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
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRestname() {
		return restname;
	}
	public void setRestname(String restname) {
		this.restname = restname;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public CustReseInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public CustReseInfo(Integer orderid, Integer restid, Integer peoplenum, Integer tableid, Date time, String state,
			String restname, String address) {
		super();
		this.orderid = orderid;
		this.restid = restid;
		this.peoplenum = peoplenum;
		this.tableid = tableid;
		this.time = time;
		this.state = state;
		this.restname = restname;
		this.address = address;
	}
	
		
}
