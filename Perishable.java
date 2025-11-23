public class Perishable extends Product {
    int date;

    public void setDate(int d) { 
        date = d; 
    }

    @Override
    public void displayProdInfo() {
        super.displayProdInfo();
        System.out.println("Expiry Date: " + date);
        System.out.println("-----------------------------");
    }
}
