package model.DAO;

import java.sql.SQLException;

public abstract class DBDAO {
    protected static DBDAO instance;
    public static DBDAO getInstance(){
        assert instance != null : "Cannot instantiate an abstract DBDAO";
        return instance;
    }
    public abstract ClientDAO getClientDAO() throws SQLException;
    public abstract BillDAO getBillDAO() throws SQLException;
    public abstract BillProductDAO getBillProductDAO() throws SQLException;
    public abstract ProductDAO getProductDAO() throws SQLException;

}
