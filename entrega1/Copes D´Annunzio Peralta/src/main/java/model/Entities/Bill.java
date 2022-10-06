package model.Entities;


public class Bill {

	private int idBill,idClient;

	public Bill(int idBill, int idClient) {
		this.idBill = idBill;
		this.idClient = idClient;
	}

	public int getIdBill() {
		return idBill;
	}

	public int getIdClient() {
		return idClient;
	}
    
    
}
