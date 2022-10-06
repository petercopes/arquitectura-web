package model.DAO;

import org.apache.commons.csv.CSVParser;

import java.sql.SQLException;

public interface EntityDAO {
    public void loadData(CSVParser parser) throws SQLException;
    public void createTable() throws SQLException;
}
