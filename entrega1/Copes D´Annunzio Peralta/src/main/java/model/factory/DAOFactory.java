package model.factory;

import model.DAO.DBDAO;
import model.DAO.DBMySQLDAO;

public class DAOFactory {
    public static final String DB_MYSQL = "mysql";
    private String db;

    public DAOFactory(String db){
        this.db=db;
    }

    public static DBDAO getDAODB(String db) {
        switch (db) {
            case DB_MYSQL:
                return DBMySQLDAO.getInstance();
            default:
                return null;
        }
    }
}
