package view;

import controller.Controller;
import entity.OrderDetails;

import java.util.List;
import java.util.Scanner;

public class OrderDetailsView {
    private Controller<OrderDetails> controller;

    public OrderDetailsView(Controller<OrderDetails> controller) {
        this.controller = controller;
    }

    public void createOrderDetail() {
        showAllOrderDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order detail id:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter order id:");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter customer id:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter product id:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter total price:");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();

        OrderDetails orderDetail = new OrderDetails(id, orderId, customerId, productId, quantity, totalPrice);



        try {
            controller.create(orderDetail);
            System.out.println("Order detail created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create order detail: " + e.getMessage());
        }
    }

    public void updateOrderDetail() {
        showAllOrderDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order detail ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        // Nhập các thông tin mới cho đối tượng order detail
        System.out.println("Enter new order id:");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new customer id:");
        int customerId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new product id:");
        int productId = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Enter new total price:");
        double totalPrice = scanner.nextDouble();
        scanner.nextLine();

        OrderDetails orderDetail = new OrderDetails(id, orderId, customerId, productId, quantity, totalPrice);

        try {
            controller.update(orderDetail);
            System.out.println("Order detail updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update order detail: " + e.getMessage());
        }
    }

    public void deleteOrderDetail() {
        showAllOrderDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order detail ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        // Yêu cầu xác nhận từ người dùng
        System.out.println("Are you sure you want to delete this order detail? (Y/N)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                controller.delete(OrderDetails.class,id);
                System.out.println("Order detail deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete order detail: " + e.getMessage());
            }
        } else if (confirmation.equalsIgnoreCase("N")) {
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Invalid input. Deletion cancelled.");
        }
    }


    public void findOrderDetailById() {
        showAllOrderDetails();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter order detail ID to find:");
        int id = scanner.nextInt();
        try {
            OrderDetails orderDetail = controller.findById(OrderDetails.class,id);
            if (orderDetail != null) {
                System.out.println("Order detail found:");
                System.out.println("Order Detail ID: " + orderDetail.getOrder_detail_id());
                System.out.println("Order ID: " + orderDetail.getOrder_id());
                System.out.println("Customer ID: " + orderDetail.getCustomer_id());
                System.out.println("Product ID: " + orderDetail.getProduct_id());
                System.out.println("Quantity: " + orderDetail.getQuantity());
                System.out.println("Total Price: " + orderDetail.getTotal_price());
            } else {
                System.out.println("Order detail not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while finding order detail by ID: " + e.getMessage());
        }
    }


    public void showAllOrderDetails() {
        String sql = "SELECT * FROM order_details";
        try {
            List<OrderDetails> orderDetailsList = controller.showAll(OrderDetails.class);
            if (!orderDetailsList.isEmpty()) {
                System.out.println("List of order details:");
                for (OrderDetails orderDetail : orderDetailsList) {
                    System.out.println("Order Detail ID: " + orderDetail.getOrder_detail_id());
                    System.out.println("Order ID: " + orderDetail.getOrder_id());
                    System.out.println("Customer ID: " + orderDetail.getCustomer_id());
                    System.out.println("Product ID: " + orderDetail.getProduct_id());
                    System.out.println("Quantity: " + orderDetail.getQuantity());
                    System.out.println("Total Price: " + orderDetail.getTotal_price());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("No order details found.");
            }
        } catch (Exception e) {
            System.out.println("Error while fetching all order details: " + e.getMessage());
        }
    }



}
