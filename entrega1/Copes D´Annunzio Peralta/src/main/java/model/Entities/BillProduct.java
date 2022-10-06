package model.Entities;

public class BillProduct {
    
	private int idBill,idProduct, quantity;

	public BillProduct(int idBill, int idProduct, int quantity) {
		this.idBill = idBill;
		this.idProduct = idProduct;
		this.quantity = quantity;
	}

	public int getIdBill() {
		return idBill;
	}

	public int getIdProduct() {
		return idProduct;
	}

	public int getQuantity() {
		return quantity;
	}
    
}
