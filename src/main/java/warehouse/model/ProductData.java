package warehouse.model;

public class ProductData {
	private String productID;
	private String productName;
	private String productCategory;
	private double productQuantity;
	private String productUnit;

	public ProductData() {}

	public ProductData(String productID, String productName, String productCategory, double productQuantity, String productUnit) {
		this.productID = productID;
		this.productName = productName;
		this.productCategory = productCategory;
		this.productQuantity = productQuantity;
		this.productUnit = productUnit;
	}

	// Getter und Setter
	public String getProductID() { return productID; }
	public void setProductID(String productID) { this.productID = productID; }
	public String getProductName() { return productName; }
	public void setProductName(String productName) { this.productName = productName; }
	public String getProductCategory() { return productCategory; }
	public void setProductCategory(String productCategory) { this.productCategory = productCategory; }
	public double getProductQuantity() { return productQuantity; }
	public void setProductQuantity(double productQuantity) { this.productQuantity = productQuantity; }
	public String getProductUnit() { return productUnit; }
	public void setProductUnit(String productUnit) { this.productUnit = productUnit; }
}