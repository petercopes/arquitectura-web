package model.DAO;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBMySQLDAO extends DBDAO{

    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URI = "jdbc:mysql://localhost:3306/entregable1";
    private static Connection conn;
    private static DBDAO instance = new DBMySQLDAO();
    private DBMySQLDAO(){
        DBMySQLDAO.registerDriver();
    }
    public static DBDAO getInstance(){
        return instance;
    };
    private static void registerDriver() {
        try {
            //Se crea una instancia del Driver utilizando mecanismo de reflexi�n
            Class.forName(DRIVER).getDeclaredConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
                | NoSuchMethodException | SecurityException | ClassNotFoundException e) {
            //Captura fallos al agregar el Driver y sale del programa informando.
            e.printStackTrace();
            System.exit(1);
        }
    }
    public static Connection createConnection() throws SQLException {
        conn = DriverManager.getConnection(URI, "root", "");
        conn.setAutoCommit(false);
        return conn;
    }
    @Override
    public ClientDAO getClientDAO() throws SQLException {
        return new ClientDAO();
    }

    @Override
    public BillDAO getBillDAO() throws SQLException {
        return new BillDAO();
    }

    @Override
    public BillProductDAO getBillProductDAO() throws SQLException {
        return new BillProductDAO();
    }

    @Override
    public ProductDAO getProductDAO() throws SQLException {
        return new ProductDAO();
    }
}
