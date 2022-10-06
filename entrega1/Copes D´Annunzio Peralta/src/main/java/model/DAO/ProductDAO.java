package model.DAO;

import model.Entities.Product;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public  class ProductDAO implements EntityDAO{
    private Connection conn;
    public ProductDAO() throws SQLException {
        this.createTable();
    }
    public Product productoMasRecaudado() throws SQLException{

        Product product = null;
        this.conn = DBMySQLDAO.createConnection();

        String select = "SELECT p.*, SUM(p.valor * fp.cantidad) as total "
                + "FROM producto p JOIN factura_producto fp ON (p.idProducto = fp.idProducto) "
                + "WHERE p.idProducto = fp.idProducto "
                + "GROUP BY idProducto "
                + "ORDER BY `total` DESC "
                + "LIMIT 1";
        PreparedStatement ps = this.conn.prepareStatement(select);
        ResultSet rs = ps.executeQuery();
        while(rs.next()) {
            product = new Product(rs.getInt(1), rs.getString(2), rs.getFloat(3));
        }
        this.conn.commit();
        ps.close();
        this.conn.close();

        return product;
    };

    @Override
    public void loadData(CSVParser parser) throws SQLException {
        this.conn = DBMySQLDAO.createConnection();
        for(CSVRecord row: parser) {
            Product p  = new Product(Integer.parseInt(row.get("idProducto")),row.get("nombre"),Float.parseFloat(row.get("valor")));
            insertProduct(p);
        }
        this.conn.close();
    }
    private void insertProduct(Product p) throws SQLException{
        String insert = "INSERT INTO Producto (idProducto, nombre, valor) VALUES (?, ?, ?)";
        PreparedStatement ps = this.conn.prepareStatement(insert);
        ps.setInt(1, p.getIdProduct());
        ps.setString(2, p.getName());
        ps.setFloat(3, p.getValue());
        ps.executeUpdate();
        this.conn.commit();
        ps.close();
    }
    @Override
    public void createTable() throws SQLException {
        this.conn = DBMySQLDAO.createConnection();
        String tablaProducto = "CREATE TABLE IF NOT EXISTS Producto("
                + "idProducto INT,"
                + "nombre VARCHAR(45),"
                + "valor FLOAT,"
                + "PRIMARY KEY(idProducto))";

        this.conn.prepareStatement(tablaProducto).execute();
        this.conn.commit();
        this.conn.close();
    }
}
