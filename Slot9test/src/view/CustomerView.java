package view;

import controller.Controller;
import entity.Customers;

import java.util.List;
import java.util.Scanner;

public class CustomerView {
    private Controller<Customers> controller;

    public CustomerView(Controller<Customers> controller) {
        this.controller = controller;
    }



    public void createCustomer() {
        showAllCustomers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer id:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter customer address:");
        String address = scanner.nextLine();
        System.out.println("Enter customer phone:");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter customer email:");
        String email = scanner.nextLine();

        Customers customer = new Customers(id, name, address, phone, email);



        try {
            controller.create(customer);
            System.out.println("Customer created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create customer: " + e.getMessage());
        }
    }

    public void updateCustomer() {
        showAllCustomers();


        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter new customer name:");
        String name = scanner.nextLine();
        System.out.println("Enter new customer address:");
        String address = scanner.nextLine();
        System.out.println("Enter new customer phone:");
        int phone = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter new customer email:");
        String email = scanner.nextLine();

        Customers customer = new Customers(id, name, address, phone, email);



        try {
            controller.update(customer);
            System.out.println("Customer updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update customer: " + e.getMessage());
        }
    }

    public void deleteCustomer() {
        showAllCustomers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        // Yêu cầu xác nhận từ người dùng
        System.out.println("Are you sure you want to delete this customer? (Y/N)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                controller.delete(Customers.class,id);
                System.out.println("Customer deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete customer: " + e.getMessage());
            }
        } else if (confirmation.equalsIgnoreCase("N")) {
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Invalid input. Deletion cancelled.");
        }
    }


    public void findCustomerById() {
        showAllCustomers();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter customer ID to find:");
        int id = scanner.nextInt();
        try {
            Customers customer = controller.findById(Customers.class, id);
            if (customer != null) {
                System.out.println("Customer found:");
                System.out.println("Customer ID: " + customer.getCustomer_id());
                System.out.println("Name: " + customer.getCustomer_name());
                System.out.println("Address: " + customer.getCustomer_address());
                System.out.println("Phone: " + customer.getCustomer_phone());
                System.out.println("Email: " + customer.getCustomer_email());
                System.out.println("-------------------------------------");
            } else {
                System.out.println("Customer not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while finding customer by ID: " + e.getMessage());
        }
    }

    public void showAllCustomers() {
        String sql = "SELECT * FROM customers";
        try {
            List<Customers> customersList = controller.showAll(Customers.class);
            if (!customersList.isEmpty()) {
                System.out.println("List of customers:");
                for (Customers customer : customersList) {
                    System.out.println("Customer ID: " + customer.getCustomer_id());
                    System.out.println("Name: " + customer.getCustomer_name());
                    System.out.println("Address: " + customer.getCustomer_address());
                    System.out.println("Phone: " + customer.getCustomer_phone());
                    System.out.println("Email: " + customer.getCustomer_email());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("No customers found.");
            }
        } catch (Exception e) {
            System.out.println("Error while fetching all customers: " + e.getMessage());
        }
    }





}
