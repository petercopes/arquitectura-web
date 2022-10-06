package model.DAO;

import model.Entities.Bill;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public  class BillDAO implements EntityDAO{
    private Connection conn;
    
    public BillDAO() throws SQLException {
        this.createTable();
    }
    @Override
    public void loadData(CSVParser parser) throws SQLException {
        this.conn = DBMySQLDAO.createConnection();
        for(CSVRecord row: parser) {
            Bill b = new Bill(Integer.parseInt(row.get("idFactura")),Integer.parseInt(row.get("idCliente")));
            insertBill(b);
        }
        this.conn.close();

    }
    private void insertBill(Bill b) throws SQLException{
        String insert = "INSERT INTO Factura (idFactura, idCliente_FK) VALUES (?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, b.getIdBill());
        ps.setInt(2, b.getIdClient());
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }



    @Override
    public void createTable() throws SQLException {
        this.conn = DBMySQLDAO.createConnection();
        String tablaFactura = "CREATE TABLE IF NOT EXISTS Factura("
                + "idFactura INT,"
                + "idCliente_FK INT,"
                + "PRIMARY KEY(idFactura),"
                + "FOREIGN KEY(idCliente_FK) references Cliente(idCliente))";

        this.conn.prepareStatement(tablaFactura).execute();
        this.conn.commit();
        this.conn.close();
    }
}
