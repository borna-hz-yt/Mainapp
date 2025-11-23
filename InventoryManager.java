public class InventoryManager {
    Perishable[] products = new Perishable[10];
    int count = 0;

    public void addProduct(Perishable p) throws Exception {
        if (count >= products.length)
            throw new Exception("Inventory full");
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