package org.kainos.ea.client;

public class ProductDoesNotExistException extends Throwable {
    @Override
    public String getMessage(){
        return "Product doesn't exist in the database";
    }
}
