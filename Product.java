public class Product {
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
        System.out.println("ID: " + productId + " | Name: " + productName 
            + " | Quantity: " + prodQuantity + " | Price: " + price);
    }
}