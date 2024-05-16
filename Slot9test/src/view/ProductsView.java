package view;

import controller.Controller;
import entity.Products;

import java.util.List;
import java.util.Scanner;

public class ProductsView {
    private Controller<Products> controller;

    public ProductsView(Controller<Products> controller) {
        this.controller = controller;
    }

    public void createProduct() {
        showAllProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product id:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào
        System.out.println("Enter product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        Products product = new Products(id, name, description, price, quantity);


        try {
            controller.create(product);
            System.out.println("Product created successfully.");
        } catch (Exception e) {
            System.out.println("Failed to create product: " + e.getMessage());
        }
    }

    public void updateProduct() {
        showAllProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to update:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        System.out.println("Enter new product name:");
        String name = scanner.nextLine();
        System.out.println("Enter new product description:");
        String description = scanner.nextLine();
        System.out.println("Enter new product price:");
        double price = scanner.nextDouble();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào
        System.out.println("Enter new product quantity:");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        Products product = new Products(id, name, description, price, quantity);



        try {
            controller.update(product);
            System.out.println("Product updated successfully.");
        } catch (Exception e) {
            System.out.println("Failed to update product: " + e.getMessage());
        }
    }

    public void deleteProduct() {
        showAllProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to delete:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Đọc newline còn lại trong luồng đầu vào

        // Yêu cầu xác nhận từ người dùng
        System.out.println("Are you sure you want to delete this product? (Y/N)");
        String confirmation = scanner.nextLine();

        if (confirmation.equalsIgnoreCase("Y")) {
            try {
                controller.delete(Products.class,id);
                System.out.println("Product deleted successfully.");
            } catch (Exception e) {
                System.out.println("Failed to delete product: " + e.getMessage());
            }
        } else if (confirmation.equalsIgnoreCase("N")) {
            System.out.println("Deletion cancelled.");
        } else {
            System.out.println("Invalid input. Deletion cancelled.");
        }
    }


    public void findProductById() {
        showAllProducts();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product ID to find:");
        int id = scanner.nextInt();
        try {
            Products product = controller.findById(Products.class,id);
            if (product != null) {
                System.out.println("Product found:");
                System.out.println("Product ID: " + product.getProduct_id());
                System.out.println("Name: " + product.getProduct_name());
                System.out.println("Description: " + product.getProduct_description());
                System.out.println("Price: " + product.getProduct_price());
                System.out.println("Quantity: " + product.getProduct_quantity());
                System.out.println("-------------------------------------");
            } else {
                System.out.println("Product not found.");
            }
        } catch (Exception e) {
            System.out.println("Error while finding product by ID: " + e.getMessage());
        }
    }

    public void showAllProducts() {
        String sql = "SELECT * FROM products";
        try {
            List<Products> productsList = controller.showAll(Products.class);
            if (!productsList.isEmpty()) {
                System.out.println("List of products:");
                for (Products product : productsList) {
                    System.out.println("Product ID: " + product.getProduct_id());
                    System.out.println("Name: " + product.getProduct_name());
                    System.out.println("Description: " + product.getProduct_description());
                    System.out.println("Price: " + product.getProduct_price());
                    System.out.println("Quantity: " + product.getProduct_quantity());
                    System.out.println("-------------------------------------");
                }
            } else {
                System.out.println("No products found.");
            }
        } catch (Exception e) {
            System.out.println("Error while fetching all products: " + e.getMessage());
        }
    }



}
