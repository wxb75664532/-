package obj;

import org.omg.CORBA.PRIVATE_MEMBER;

public class RestInfo {
	private Integer restid;
	private String restname;
	private String address;
	private Float praiserate;
	private String characters;
	private String username;
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
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
	public Float getPraiserate() {
		return praiserate;
	}
	public void setPraiserate(Float praiserate) {
		this.praiserate = praiserate;
	}
	public String getCharacters() {
		return characters;
	}
	public void setCharacters(String characters) {
		this.characters = characters;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public RestInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public RestInfo(Integer restid, String restname, String address, Float praiserate, String characters,
			String username) {
		super();
		this.restid = restid;
		this.restname = restname;
		this.address = address;
		this.praiserate = praiserate;
		this.characters = characters;
		this.username = username;
	}
	
}
