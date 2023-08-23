package org.kainos.ea.cli;

public class Customer {
    private int CustomerID;
    private String Name;
    private String Address;
    private String Phone;

    public Customer(int customerID, String name, String address, String phone) {
        CustomerID = customerID;
        Name = name;
        Address = address;
        Phone = phone;
    }


    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

}
