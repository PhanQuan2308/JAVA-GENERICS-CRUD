package view;

import controller.Controller;
import entity.Customers;
import entity.Orders;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class OrdersView {
    private Controller<Orders> controller;

    private CustomerView customerView;
    private ProductsView productsView;

    public OrdersView(Controller<Orders> controller, CustomerView customerView, ProductsView productsView) {
        this.controller = controller;
        this.customerView = customerView;
        this.productsView = productsView;
    }

    public void createOrder() {
        showAllOrders();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter customer id:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter product id:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter order date (yyyy-MM-dd):");
        String orderDateStr = scanner.nextLine();
        Date orderDate = null;
        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format yyyy-MM-dd.");
            return;
        }

        System.out.println("Enter payment type:");
        String paymentType = scanner.nextLine();

        System.out.println("Enter order status:");
        String orderStatus = scanner.nextLine();

        Orders order = new Orders(id, customerId, productId, orderDate, paymentType, orderStatus);



        try {
            controller.create(order);
            System.out.println("Order created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create order: " + e.getMessage());
        }
    }

    public void updateOrder() {
        // Hiển thị danh sách các đơn hàng
        showAllOrders();

        // Hiển thị danh sách các khách hàng
        customerView.showAllCustomers();
        productsView.showAllProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter new customer id:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new product id:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new order date (yyyy-MM-dd):");
        String orderDateStr = scanner.nextLine();
        Date orderDate = null;
        try {
            orderDate = new SimpleDateFormat("yyyy-MM-dd").parse(orderDateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format. Please enter date in the format yyyy-MM-dd.");
            return;
        }

        System.out.println("Enter new payment type:");
        String paymentType = scanner.nextLine();

        System.out.println("Enter new order status:");
        String orderStatus = scanner.nextLine();

        Orders order = new Orders(id, customerId, productId, orderDate, paymentType, orderStatus);

        // Cập nhật đơn hàng
        try {
            controller.update(order);
            System.out.println("Order updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update order: " + e.getMessage());
        }
    }


    public void deleteOrder() {
        showAllOrders();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        // Yêu cầu xác nhận từ người dùng
        System.out.println("Are you sure you want to delete this order? (Y/N)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                controller.delete(Orders.class,id);
                System.out.println("Order deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete order: " + e.getMessage());
            }
        } else if (confirmation.equalsIgnoreCase("N")) {
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Invalid input. Deletion cancelled.");
        }
    }


    public void findOrderById() {
        showAllOrders();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order id to find:");
        int id = scanner.nextInt();
        try {
            Orders order = controller.findById(Orders.class,id);
            if (order != null) {
                System.out.println("Order found:");
                System.out.println("Order ID: " + order.getOrder_id());
                System.out.println("Customer ID: " + order.getCustomer_id());
                System.out.println("Product ID: " + order.getProduct_id());
                System.out.println("Order Date: " + order.getOrder_date());
                System.out.println("Payment Type: " + order.getPayment_type());
                System.out.println("Order Status: " + order.getOrder_status());
                System.out.println("-------------------------------------");
            } else {
                System.out.println("Order not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while finding order by ID: " + e.getMessage());
        }
    }

    public void showAllOrders() {
        String sql = "SELECT * FROM orders";
        try {
            List<Orders> ordersList = controller.showAll(Orders.class);
            if (!ordersList.isEmpty()) {
                System.out.println("List of orders:");
                for (Orders order : ordersList) {
                    System.out.println("Order ID: " + order.getOrder_id());
                    System.out.println("Customer ID: " + order.getCustomer_id());
                    System.out.println("Product ID: " + order.getProduct_id());
                    System.out.println("Order Date: " + order.getOrder_date());
                    System.out.println("Payment Type: " + order.getPayment_type());
                    System.out.println("Order Status: " + order.getOrder_status());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("No orders found.");
            }
        } catch (Exception e) {
            System.out.println("Error while fetching all orders: " + e.getMessage());
        }
    }



}
