package model;

public class CustPeseicarte {
	private Integer id;
	private String foodName;
	private Integer unitPrice;
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
	public CustPeseicarte(Integer id, String foodName, Integer count) {
		super();
		this.id = id;
		this.foodName = foodName;
		this.unitPrice = count;
	}
	public CustPeseicarte() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
}
