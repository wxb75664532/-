package obj;

public class CustInfo {
	private Integer custid;
	private String custname;
	private String number;
	private String address;
	private String taste;
	private String username;
	public Integer getCustid() {
		return custid;
	}
	public void setCustid(Integer custid) {
		this.custid = custid;
	}
	public String getCustname() {
		return custname;
	}
	public void setCustname(String custname) {
		this.custname = custname;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTaste() {
		return taste;
	}
	public void setTaste(String taste) {
		this.taste = taste;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public CustInfo(Integer custid, String custname, String number, String address, String taste, String username) {
		super();
		this.custid = custid;
		this.custname = custname;
		this.number = number;
		this.address = address;
		this.taste = taste;
		this.username = username;
	}
	public CustInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
}
