import java.util.Scanner;

class MenuItem {
    int id;
    String name;
    double price;

    MenuItem(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    void display() {
        System.out.printf("%-5d %-15s Rs. %.2f%n",
                id, name, price);
    }
}

public class generateBill {

    static Scanner sc = new Scanner(System.in);

    static MenuItem[] menu = {
        new MenuItem(1, "Burger", 120.00),
        new MenuItem(2, "Pizza", 250.00),
        new MenuItem(3, "Pasta", 180.00),
        new MenuItem(4, "Sandwich", 100.00),
        new MenuItem(5, "Juice", 50.00)
    };

    static int[] quantities = new int[menu.length];

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("
================================");
            System.out.println(" RESTAURANT ORDER SYSTEM");
            System.out.println("================================");
            System.out.println("1. View Menu");
            System.out.println("2. Place Order");
            System.out.println("3. Generate Bill");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Please enter a valid number.");
                sc.next();
                choice = 0;
                continue;
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    displayMenu();
                    break;

                case 2:
                    placeOrder();
                    break;

                case 3:
                    generateBill();
                    break;

                case 4:
                    System.out.println("Thank you for visiting!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }

        } while (choice != 4);

        sc.close();
    }

    static void displayMenu() {
        System.out.println("
---------- FOOD MENU ----------");
        System.out.printf("%-5s %-15s %s%n",
                "ID", "Item", "Price");

        for (MenuItem item : menu) {
            item.display();
        }
    }

    static void placeOrder() {
        displayMenu();

        while (true) {
            System.out.print(
                    "
Enter item ID (0 to finish): ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid item ID.");
                sc.next();
                continue;
            }

            int id = sc.nextInt();

            if (id == 0) {
                break;
            }

            if (id < 1 || id > menu.length) {
                System.out.println("Invalid item ID!");
                continue;
            }

            System.out.print("Enter quantity: ");

            if (!sc.hasNextInt()) {
                System.out.println("Enter a valid quantity.");
                sc.next();
                continue;
            }

            int qty = sc.nextInt();

            if (qty <= 0) {
                System.out.println(
                        "Quantity must be greater than zero.");
            } else {
                quantities[id - 1] += qty;

                System.out.println(
                        menu[id - 1].name
                        + " added to your order.");
            }
        }

        System.out.println("Order entry completed.");
    }

    static void generateBill() {
        double subtotal = 0;

        System.out.println("
========== FINAL BILL ==========");
        System.out.printf("%-15s %5s %10s %10s%n",
                "Item", "Qty", "Price", "Total");

        for (int i = 0; i < menu.length; i++) {
            if (quantities[i] > 0) {
                double total =
                        menu[i].price * quantities[i];

                subtotal += total;

                System.out.printf(
                        "%-15s %5d %10.2f %10.2f%n",
                        menu[i].name,
                        quantities[i],
                        menu[i].price,
                        total);
            }
        }

        if (subtotal == 0) {
            System.out.println("No items have been ordered.");
            System.out.println("=================================");
            return;
        }

        double gst = subtotal * 0.05;
        double grandTotal = subtotal + gst;

        System.out.println("---------------------------------");
        System.out.printf("Subtotal:       Rs. %.2f%n", subtotal);
        System.out.printf("GST (5%%):       Rs. %.2f%n", gst);
        System.out.println("---------------------------------");
        System.out.printf("Grand Total:    Rs. %.2f%n",
                grandTotal);
        System.out.println("=================================");
    }
          }
