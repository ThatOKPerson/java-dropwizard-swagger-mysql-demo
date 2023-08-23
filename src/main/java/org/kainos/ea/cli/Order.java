package org.kainos.ea.cli;


import java.util.Date;

public class Order  implements Comparable<Order>{
    private int orderID;
    private int customerID;
    private Date orderDate;
    public Order(int orderID, int customerID, Date orderDate){
        this.orderID = orderID;
        this.customerID = customerID;
        this.orderDate = orderDate;
    }

    public int getOrderID() {
        return orderID;
    }

    public void setOrderID(int orderID) {
        this.orderID = orderID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    @Override
    public int compareTo(Order order) {
        return Integer.compare(this.customerID, order.customerID);
    }

    @Override
    public String toString(){
        return "Order ID: " + this.orderID + ", Customer ID: " + this.customerID + ", Order date: " + this.orderDate;
    }
}
