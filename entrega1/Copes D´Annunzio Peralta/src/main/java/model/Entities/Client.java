package model.Entities;


public class Client {
	
    private int idCliente;
    private String nombre,email;
    
	public Client(int idCliente, String nombre, String email) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.email = email;
	}
	
	public int getIdCliente() {
		return idCliente;
	}
	public String getNombre() {
		return nombre;
	}
	public String getEmail() {
		return email;
	}
	
	@Override
	public String toString() {
		return "Client [idCliente=" + idCliente + ", nombre=" + nombre + ", email=" + email + "]";
	}
    
}
