package menu;

import controller.Controller;
import entity.*;
import model.Model;
import view.CustomerView;
import view.OrderDetailsView;
import view.OrdersView;
import view.ProductsView;

import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in);

    private static final Model<Customers> modelCustomers = new Model<>();
    private static final Controller<Customers> customersController = new Controller<>(modelCustomers);
    private static final CustomerView customerView = new CustomerView(customersController);

    private static final Model<Products> modelProducts = new Model<>();
    private static final Controller<Products> productsController = new Controller<>(modelProducts);
    private static final ProductsView productsView = new ProductsView(productsController);

    private static final Model<Orders> modelOrders = new Model<>();
    private static final Controller<Orders> ordersController = new Controller<>(modelOrders);
    private static final OrdersView ordersView = new OrdersView(ordersController,customerView,productsView);

    private static final Model<OrderDetails> modelOrderDetails = new Model<>();
    private static final Controller<OrderDetails> orderDetailsController = new Controller<>(modelOrderDetails);
    private static final OrderDetailsView orderDetailsView = new OrderDetailsView(orderDetailsController);

    public static void menu() {
        while (true) {
            System.out.println("====== Shoppee Management =======");
            System.out.println("1. Customers");
            System.out.println("2. Products");
            System.out.println("3. Orders");
            System.out.println("4. OrderDetails");
            System.out.println("0. Exit");
            System.out.println("Enter your choice:");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    handleCustomers();
                    break;
                case 2:
                    handleProducts();
                    break;
                case 3:
                    handleOrders();
                    break;
                case 4:
                    handleOrderDetails();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter again.");
            }

            // Ask user if they want to continue or exit
            System.out.println("Do you want to continue? (Y/N)");
            String continueChoice = scanner.nextLine();
            if (!continueChoice.equalsIgnoreCase("Y")) {
                System.out.println("Exiting...");
                break; // Exit the loop and terminate the program
            }
        }
    }

    public static void handleCustomers() {
        System.out.println("======= Customers Menu =======");
        System.out.println("1. Add Customer");
        System.out.println("2. Update Customer");
        System.out.println("3. Delete Customer");
        System.out.println("4. Find Customer");
        System.out.println("5. Show all Customer");
        System.out.println("0. Back to Main Menu");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                customerView.createCustomer();
                break;
            case 2:
                customerView.updateCustomer();
                break;
            case 3:
                customerView.deleteCustomer();
                break;
            case 4:
                customerView.findCustomerById();
                break;
            case 5:
                customerView.showAllCustomers();
                break;
            case 0:
                System.out.println("Exiting ...");
                break;
            default:
                System.out.println("Enter invalid, please enter agian");
        }
    }

    public static void handleProducts() {
        System.out.println("======= Products Menu =======");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. Find Product");
        System.out.println("5. Show all Products");
        System.out.println("0. Back to Main Menu");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                productsView.createProduct();
                break;
            case 2:
                productsView.updateProduct();
                break;
            case 3:
                productsView.deleteProduct();
                break;
            case 4:
                productsView.findProductById();
                break;
            case 5:
                productsView.showAllProducts();
                break;
            case 0:
                System.out.println("Exiting ...");
                break;
            default:
                System.out.println("Enter invalid, please enter again");
        }
    }

    public static void handleOrders() {
        System.out.println("======= Orders Menu =======");
        System.out.println("1. Add Order");
        System.out.println("2. Update Order");
        System.out.println("3. Delete Order");
        System.out.println("4. Find Order");
        System.out.println("5. Show all Orders");
        System.out.println("0. Back to Main Menu");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                ordersView.createOrder();
                break;
            case 2:
                ordersView.updateOrder();
                break;
            case 3:
                ordersView.deleteOrder();
                break;
            case 4:
                ordersView.findOrderById();
                break;
            case 5:
                ordersView.showAllOrders();
                break;
            case 0:
                System.out.println("Exiting ...");
                break;
            default:
                System.out.println("Invalid choice, please enter again");
        }
    }

    public static void handleOrderDetails() {
        System.out.println("======= Order Details Menu =======");
        System.out.println("1. Add Order Detail");
        System.out.println("2. Update Order Detail");
        System.out.println("3. Delete Order Detail");
        System.out.println("4. Find Order Detail");
        System.out.println("5. Show all Order Details");
        System.out.println("0. Back to Main Menu");
        System.out.println("Enter your choice:");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice){
            case 1:
                orderDetailsView.createOrderDetail();
                break;
            case 2:
                orderDetailsView.updateOrderDetail();
                break;
            case 3:
                orderDetailsView.deleteOrderDetail();
                break;
            case 4:
                orderDetailsView.findOrderDetailById();
                break;
            case 5:
                orderDetailsView.showAllOrderDetails();
                break;
            case 0:
                System.out.println("Exiting ...");
                break;
            default:
                System.out.println("Invalid choice, please enter again");
        }
    }

    public static void main(String[] args) {
        menu();
    }
}
