package org.kainos.ea.cli;

public class DeliveryEmployee extends Employee{

    public DeliveryEmployee(int employeeID, String name, double salary) {
        super(employeeID, name, salary);
    }

    @Override
    public double calcPay(){
        return getSalary()/12 + 1000;
    }
}
