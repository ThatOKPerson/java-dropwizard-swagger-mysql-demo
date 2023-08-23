package org.kainos.ea.api;

import org.kainos.ea.cli.Order;
import org.kainos.ea.cli.OrderRequest;
import org.kainos.ea.client.FailedToCreateOrderException;
import org.kainos.ea.client.FailedToDeleteOrderException;
import org.kainos.ea.client.FailedToGetOrdersException;
import org.kainos.ea.client.FailedToUpdateOrderException;
import org.kainos.ea.client.InvalidOrderException;
import org.kainos.ea.client.OrderDoesNotExistException;
import org.kainos.ea.core.OrderValidator;
import org.kainos.ea.db.OrderDao;

import java.sql.SQLException;
import java.util.List;

public class OrderService {
    private OrderDao orderDao = new OrderDao();

    public List<Order> getAllOrders() throws FailedToGetOrdersException {

        List<Order> orderList = null;
        try {
            orderList = orderDao.getAllOrders();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }
        return orderList;

//        List<Integer> custID = orderList.stream().map(order -> order.getCustomerID()).collect(Collectors.toList());
//        custID.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
//                .entrySet().stream().max(Map.Entry.comparingByValue()).ifPresent(System.out::println);

//        HashMap<Integer, Integer> map = new HashMap<>(orderList.size());
//        int count = 0;
//        for (int i = 0; i > orderList.size(); i++) {
//            count = 0;
//            for (int j = 1; j > 100; j++) {
//                if (orderList.get(i).getCustomerID() == j) {
//                    count += 1;
//                }
//            }
//            map.put(i, count);
//        }
//        System.out.println(map.entrySet().stream().max(Comparator.
//                comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())));

//        Map<Integer, Long> countOrderMap = orderList.stream()
//                .collect(Collectors.groupingBy(Order::getCustomerId, Collectors.counting()));
//
//        System.out.println("Customer with most orders: "
//                + Collections.max(countOrderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
//
//        System.out.println("Customer with least orders: "
//                + Collections.min(countOrderMap.entrySet(), Map.Entry.comparingByValue()).getKey());
    }
    public Order getOrderByID(int id) throws FailedToGetOrdersException, OrderDoesNotExistException {
        try{
            Order order = orderDao.getOrderByID(id);
            if(order == null){
                throw new OrderDoesNotExistException();
            }
            return order;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToGetOrdersException();
        }
    }

    public int createOrder(OrderRequest order) throws FailedToCreateOrderException, InvalidOrderException {
        try {
            String validation = OrderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            int id = orderDao.createOrder(order);

            if(id == -1){
                throw new FailedToCreateOrderException();
            }
            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToCreateOrderException();
        }
    }
    public int updateOrder(int id, OrderRequest order) throws InvalidOrderException, OrderDoesNotExistException, FailedToUpdateOrderException {
        try {
            String validation = OrderValidator.isValidOrder(order);

            if(validation != null){
                throw new InvalidOrderException(validation);
            }

            Order orderToUpdate = orderDao.getOrderByID(id);
            if(orderToUpdate == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.updateOrder(id, order);
            return id;
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToUpdateOrderException();
        }
    }
    public void deleteOrder(int id) throws OrderDoesNotExistException, FailedToDeleteOrderException {
        try {
            Order orderToDelete = orderDao.getOrderByID(id);

            if(orderToDelete == null){
                throw new OrderDoesNotExistException();
            }

            orderDao.deleteOrder(id);
        } catch (SQLException e){
            System.err.println(e.getMessage());
            throw new FailedToDeleteOrderException();
        }
    }
}
