package org.kainos.ea.cli;

public class SalesEmployee extends Employee{
    private int employeeID;
    private String name;
    private double salary;
    private double monthlySales;
    private float commissionRate;

    public SalesEmployee(int employeeID, String name, double salary, double monthlySales, float commissionRate) {
        super(employeeID, name, salary);
        this.monthlySales = monthlySales;
        this.commissionRate = commissionRate;
    }
    @Override
    public double calcPay(){
        return getSalary()/12 + getMonthlySales()*getCommissionRate();
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(int employeeID) {
        this.employeeID = employeeID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public double getMonthlySales() {
        return monthlySales;
    }

    public void setMonthlySales(double monthlySales) {
        this.monthlySales = monthlySales;
    }

    public float getCommissionRate() {
        return commissionRate;
    }

    public void setCommissionRate(float commissionRate) {
        this.commissionRate = commissionRate;
    }
}
