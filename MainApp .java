import java.util.Scanner;

class Product {
    int productId;
    String productName;
    int prodQuantity;
    double price;

    public void setProduct(int id, String name, int qty, double prc) {
        productId = id;
        productName = name;
        prodQuantity = qty;
        price = prc;
    }

    public int getId() { return productId; }
    public String getName() { return productName; }
    public int getQuantity() { return prodQuantity; }
    public double getPrice() { return price; }

    public void displayProdInfo() {
        System.out.println("ID: " + productId + " | Name: " + productName + " | Quantity: " + prodQuantity + " | Price: " + price);
    }
}

class Perishable extends Product {
    int date;
    public void setDate(int d) { date = d; }

    public void displayProdInfo() {
        super.displayProdInfo();
        System.out.println("Expiry Date: " + date);
        System.out.println("-----------------------------");
    }
}

class ProductNotFoundException extends Exception {
    public ProductNotFoundException(String msg) { super(msg); }
}

class InventoryManager {
    Perishable[] products = new Perishable[10];
    int count = 0;

    public void addProduct(Perishable p) throws Exception {
        if (count >= products.length) throw new Exception("Inventory full");
        products[count++] = p;
    }

    public void viewProducts() {
        if (count == 0) {
            System.out.println("No products in inventory.");
            return;
        }
        for (int i = 0; i < count; i++)
            if (products[i] != null)
                products[i].displayProdInfo();
    }

    public void updateProduct(int id, int qty, double price) throws ProductNotFoundException {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getId() == id) {
                products[i].setProduct(id, products[i].getName(), qty, price);
                return;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }

    public void deleteProduct(int id) throws ProductNotFoundException {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getId() == id) {
                for (int j = i; j < count - 1; j++)
                    products[j] = products[j + 1];
                products[count - 1] = null;
                count--;
                return;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }

    public void searchProduct(int id) throws ProductNotFoundException {
        for (int i = 0; i < count; i++) {
            if (products[i] != null && products[i].getId() == id) {
                products[i].displayProdInfo();
                return;
            }
        }
        throw new ProductNotFoundException("Product not found.");
    }
}

public class MainApp {
    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();
        Scanner s = new Scanner(System.in);

        while (true) {
            System.out.println("\n Inventory Management System ");
            System.out.println("1. Add Product");
            System.out.println("2. View All Products");
            System.out.println("3. Update Product");
            System.out.println("4. Delete Product");
            System.out.println("5. Search Product by ID");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try { choice = s.nextInt(); } 
            catch (Exception e) { System.out.println("Invalid input."); s.nextLine(); continue; }

            switch(choice) {
                case 1:
                    try {
                        System.out.print("Enter ID: "); int id = s.nextInt(); s.nextLine();
                        System.out.print("Enter Name: "); String name = s.nextLine();
                        System.out.print("Enter Quantity: "); int qty = s.nextInt();
                        System.out.print("Enter Price: "); double price = s.nextDouble();
                        System.out.print("Enter Expiry Date: "); int date = s.nextInt();

                        Perishable p = new Perishable(); 
                        p.setProduct(id, name, qty, price);
                        p.setDate(date);

                        manager.addProduct(p); 
                        System.out.println("Product added successfully.");
                    } catch (Exception e) { System.out.println(e.getMessage()); s.nextLine(); }
                    break;

                case 2:
                    manager.viewProducts();
                    break;

                case 3:
                    try {
                        System.out.print("Enter product ID to update: "); int uid = s.nextInt();
                        System.out.print("Enter new Quantity: "); int newQty = s.nextInt();
                        System.out.print("Enter new Price: "); double newPrice = s.nextDouble();
                        manager.updateProduct(uid, newQty, newPrice);
                        System.out.println("Product updated successfully.");
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 4:
                    try {
                        System.out.print("Enter product ID to delete: "); int did = s.nextInt();
                        manager.deleteProduct(did);
                        System.out.println("Product deleted successfully.");
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 5:
                    try {
                        System.out.print("Enter product ID to search: "); int sid = s.nextInt();
                        manager.searchProduct(sid);
                    } catch (Exception e) { System.out.println(e.getMessage()); }
                    break;

                case 6:
                    System.out.println("Exiting...");
                    s.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
