package obj;

public class CarteInfo {
	
	public CarteInfo() {
		super();
		// TODO 自动生成的构造函数存根
	}
	public CarteInfo(Integer id, String foodName, Integer unitPrice, String category, String intro, String picture,
			Integer restid) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.unitPrice = unitPrice;
		this.category = category;
		this.intro = intro;
		this.picture = picture;
		this.restid = restid;
	}
	private Integer id;
	private String foodName;
	private Integer unitPrice;
	private String category;
	private String intro;
	private String picture;
	private Integer restid;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFoodName() {
		return foodName;
	}
	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}
	public Integer getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(Integer unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	public Integer getRestid() {
		return restid;
	}
	public void setRestid(Integer restid) {
		this.restid = restid;
	}
	
}
