package model;

import java.util.Date;

public class CustComment {
	
	public Integer getOrderid() {
		return orderid;
	}
	public void setOrderid(Integer orderid) {
		this.orderid = orderid;
	}
	private Integer id;
	private Integer orderid;
	private Integer custid;
	private Integer restid;
	private String ty;//评论风格
	private String sketch;//评论内容
	private Date time;
	private String restname;
	public CustComment() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public CustComment(Integer id, Integer custid, Integer restid, String ty, String sketch, Date time,
			String restname,Integer orderid) {
		super();
		this.id = id;
		this.custid = custid;
		this.restid = restid;
		this.ty = ty;
		this.sketch = sketch;
		this.time = time;
		this.restname = restname;
		this.orderid=orderid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public String getTy() {
		return ty;
	}
	public void setTy(String ty) {
		this.ty = ty;
	}
	public String getSketch() {
		return sketch;
	}
	public void setSketch(String sketch) {
		this.sketch = sketch;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getRestname() {
		return restname;
	}
	public void setRestname(String restname) {
		this.restname = restname;
	}
	
	
	
}
