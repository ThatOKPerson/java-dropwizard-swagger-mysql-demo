package org.kainos.ea.core;

import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.db.CustomerDao;

import java.sql.SQLException;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

public class OrderValidator {
    static CustomerDao customerDao = new CustomerDao();
    public static String isValidOrder(OrderRequest order) throws SQLException {
        if (order.getOrderDate().before(Date.from(Instant.now().minus(Duration.ofDays(365))))){
            return "Order over a year old";
        }
        if(customerDao.getCustomerByID(order.getCustomerID()) == null){
            return "Customer doesn't exist";
        }
        return null;
    }
}
