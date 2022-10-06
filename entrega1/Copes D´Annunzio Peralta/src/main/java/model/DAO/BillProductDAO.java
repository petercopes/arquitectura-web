package model.DAO;

import model.Entities.BillProduct;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import lombok.Data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Data
public class BillProductDAO implements EntityDAO{
    private Connection conn;
    
   
    public BillProductDAO() throws SQLException {
		this.createTable();
	}
    
    
	@Override
    public void loadData(CSVParser parser) throws SQLException {
        this.conn = DBMySQLDAO.createConnection();
        for(CSVRecord row: parser) {
            BillProduct bp = new BillProduct(Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idProducto")),Integer.parseInt(row.get("cantidad")));
            insertBillProduct(bp);

        }
        this.conn.close();
    }
    private void insertBillProduct(BillProduct bp) throws SQLException{
        String insert = "INSERT INTO Factura_Producto (idFactura, idProducto, cantidad) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, bp.getIdBill());
        ps.setInt(2, bp.getIdProduct());
        ps.setInt(3, bp.getQuantity());
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }
    @Override
    public void createTable() throws SQLException {
        this.conn = DBMySQLDAO.createConnection();

        String tablaFactura_Producto = "CREATE TABLE IF NOT EXISTS Factura_Producto("
                + "idFactura INT,"
                + "idProducto INT,"
                + "cantidad INT,"
                + "PRIMARY KEY(idFactura, idProducto),"
                + "FOREIGN KEY(idFactura) references Factura(idFactura),"
                + "FOREIGN KEY(idProducto) references Producto(idProducto))";

        this.conn.prepareStatement(tablaFactura_Producto).execute();
        this.conn.commit();
        this.conn.close();
    }
}
