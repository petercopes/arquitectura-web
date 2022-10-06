package main;

import model.DAO.*;
import model.Entities.Client;
import model.factory.DAOFactory;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws SQLException, FileNotFoundException, IOException {
        DBDAO mysqlFactory = DAOFactory.getDAODB(DAOFactory.DB_MYSQL);

        //Crear las tablas
        ClientDAO clientDAO = mysqlFactory.getClientDAO();
        BillDAO billDAO = mysqlFactory.getBillDAO();
        ProductDAO productDAO = mysqlFactory.getProductDAO();
        BillProductDAO billProductDAO = mysqlFactory.getBillProductDAO();

        CSVParser productParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/productos.csv"));
        CSVParser clientParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/clientes.csv"));
        CSVParser BillParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/facturas.csv"));
        CSVParser BillProductParser = CSVFormat.DEFAULT.withHeader().parse(new FileReader("./src/main/resources/facturas-productos.csv"));

        clientDAO.loadData(clientParser);
        productDAO.loadData(productParser);
        billDAO.loadData(BillParser);
        billProductDAO.loadData(BillProductParser);

        System.out.println("Producto mas recaudado: ");
        System.out.println(productDAO.productoMasRecaudado() + System.lineSeparator());

        System.out.println("Listado de clientes ordenado por facturacion: ");
        ArrayList<Client> clientes = clientDAO.masFacturados();
        for(Client c: clientes) {
            System.out.println(c);
        }
    }
}
