package obj;

public class TableInfo {
	private Integer id;
	private Integer tableid;
	private Integer capacity;
	private String position;
	private Integer restid;
	
	
	public TableInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public TableInfo(Integer id, Integer tableid, Integer capacity, String position, Integer restid) {
		super();
		this.id = id;
		this.tableid = tableid;
		this.capacity = capacity;
		this.position = position;
		this.restid = restid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getTableid() {
		return tableid;
	}
	public void setTableid(Integer tableid) {
		this.tableid = tableid;
	}
	public Integer getCapacity() {
		return capacity;
	}
	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
	}
	
	
}
