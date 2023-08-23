package org.kainos.ea.db;

import org.kainos.ea.cli.Customer;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerDao {

    private static DatabaseConnector databaseConnector = new DatabaseConnector();
    public Customer getCustomerByID(int id) throws SQLException {
        Connection c = databaseConnector.getConnection();
        Statement st = c.createStatement();

        ResultSet rs = st.executeQuery("SELECT CustomerID, Name, Address, Phone FROM Customer WHERE CustomerID = " + id + ";");

        while (rs.next()) {
            return new Customer(
                    rs.getInt("CustomerID"),
                    rs.getString("Name"),
                    rs.getString("Address"),
                    rs.getString("Phone")
            );
        }
        return null;
    }
}
