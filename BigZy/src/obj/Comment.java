package obj;

import java.util.Date;

public class Comment {
	private Integer id;
	private Integer orderId;
	private String ty;//���۷��
	private String sketch;//��������
	private Date time;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getOrderId() {
		return orderId; 
	}
	public void setOrderId(Integer id) {
		this.orderId = id;
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
	public Comment() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	public Comment(Integer id, Integer orderid, String ty, String sketch, Date time) {
		super();
		this.id = id;
		this.orderId=orderid;
		this.ty = ty;
		this.sketch = sketch;
		this.time = time;
	}
	
	
}
