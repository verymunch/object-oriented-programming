import java.util.ArrayList;
import java.util.Objects;

class Customer {
    private String name;
    private String state;

    /**
     * Two-argument constructor that contains customer information.
     * @param customerName: the name of customer.
     * @param customerState: the location of customer.
     */
    public Customer(String customerName, String customerState) {
        name = customerName;
        state = customerState;
    }

    /**
     * @return the customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * @return the customer's state location (i.e. "MN").
     */
    public String getState() {
        return state;
    }

    /**
     * Sets the name of the customer.
     * @param newName: new name of a customer.
     */
    public void setName(String newName) {
        name = newName;
    }

    /**
     * Sets the customer's state location.
     * @param newState: new state location of a customer.
     */
    public void setState(String newState) {
        state = newState;
    }
}

class Product {
    private int id;
    private String item;
    private double price;
    private int quantity;

    /**
     * Four-argument constructor that contains product information.
     * @param itemID: the ID of product.
     * @param itemName: the name of product.
     * @param listPrice: the price of product.
     * @param numberOfItems: the number of products available.
     */
    public Product(int itemID, String itemName, double listPrice, int numberOfItems) {
        id = itemID;
        item = itemName;
        price = listPrice;
        quantity = numberOfItems;
    }

    /**
     * @return the ID of a product.
     */
    public int getID() {
        return id;
    }

    /**
     * @return the name of a product.
     */
    public String getItem() {
        return item;
    }

    /**
     * @return the price of a product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * @return the quantity of a product.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets the ID of a product.
     * @param newID: new ID for a product.
     */
    public void setID(int newID) {
        id = newID;
    }

    /**
     * Sets the name of a product.
     * @param newItem: new name for a product.
     */
    public void setItem(String newItem) {
        item = newItem;
    }

    /**
     * Sets the price of a product.
     * @param newPrice: new price for a product.
     */
    public void setPrice(double newPrice) {
        price = newPrice;
    }

    /**
     * Sets the quantity of a product.
     * @param newQuantity: new quantity of a product.
     */
    public void setQuantity(int newQuantity) {
        quantity = newQuantity;
    }
}

class Transaction {
    private String transactionNumber;
    private Customer customer; // composition relationship
    private ArrayList<Product> products; // aggregation relationship

    /**
     * Three-argument constructor that contains information on a specific order placed.
     * @param transNum: the order number representing a specific transaction.
     * @param name: the name of customer.
     * @param state: the state location of customer.
     */
    public Transaction(String transNum, String name, String state) {
        transactionNumber = transNum;
        customer = new Customer(name, state);
        products = new ArrayList<Product>();
    }

    /**
     * @return the transaction number of a customer's order.
     */
    public String getTransactionNumber() {
        return transactionNumber;
    }

    /**
     * @return the customer's name who placed the order.
     */
    public String getCustomerName() {
        return customer.getName();
    }

    /**
     * @return the customer's state location who placed the order.
     */
    public String getCustomerState() {
        return customer.getState();
    }

    /**
     * Sets the transaction number of a customer's order.
     * @param newTransactionNumber: new transaction number of an order.
     */
    public void setTransactionNumber(String newTransactionNumber) {
        transactionNumber = newTransactionNumber;
    }

    /**
     * Adds a new product to customer's current order.
     * @param id: the ID of product.
     * @param item: the name of product.
     * @param price: the price of product.
     * @param quantity: the number of products purchased by the customer.
     * @return boolean (true) if product successfully was added.
     */
    public boolean addProduct(int id, String item, double price, int quantity) {
        Product p = new Product(id, item, price, quantity);
        products.add(p);
        return true;
    }

    /**
     * Removes existing product from customer's current order.
     * @param id: the ID of product.
     * @return boolean (flag) representing if requested product to remove successfully was removed.
     */
    public boolean removeProduct(int id) {
        int i = 0;
        boolean found = false;
        while (i < products.size() && (!found)) {
            // when product ID from customer's order matches requested product to remove
            if (Objects.equals(products.get(i).getID(), id)) {
                found = true;
                products.remove(i);
            }
            i++;
        }
        return found;
    }

    /**
     * Modifies existing product information for product's price and quantity.
     * @param id: the ID of product.
     * @param newPrice: the new price of product.
     * @param newQuantity: the new number of products purchased by the customer.
     */
    public void modifyProduct(int id, double newPrice, int newQuantity) {
        int i = 0;
        boolean found = false;
        while (i < products.size() && (!found)) {
            // when product ID from customer's order matches requested product to modify
            if (Objects.equals(products.get(i).getID(), id)) {
                found = true;
                products.get(i).setPrice(newPrice);
                products.get(i).setQuantity(newQuantity);
            }
            i++;
        }
    }

    /**
     * Sums the total price of an order.
     * @return the total price of a customer's order.
     */
    public double orderTotal() {
        Product p;
        double sum = 0;
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            sum += products.get(i).getPrice() * products.get(i).getQuantity();
        }
        System.out.printf("Order total: $%.2f", sum);
        System.out.println();
        return sum;
    }

    /**
     * @return the number of items purchased by the customer.
     */
    public int orderSize() {
        return products.size();
    }

    /**
     * Displays the contents of a customer's order.
     */
    public void printReceipt() {
        Product p;
        System.out.println(orderSize() + " items purchased.");
        for (int i = 0; i < products.size(); i++) {
            p = products.get(i);
            System.out.println("Product: " + p.getItem() + ". Price: $" + p.getPrice() + ". Quantity: "
            + p.getQuantity() + " item(s) bought.");
        }
    }
}

class OnlineTrans extends Transaction {
    private String file;

    /**
     * Four-argument constructor that contains information on an order created through an online system.
     * @param transactionNumber: the order number representing a specific transaction.
     * @param name: the name of customer.
     * @param state: the state location of customer.
     * @param transFile: the file that contains the details of a specific transaction.
     */
    public OnlineTrans(String transactionNumber, String name, String state, String transFile) {
        super(transactionNumber, name, state);
        file = transFile;
    }

    /**
     * @return the file name for a specific transaction.
     */
    public String getFile() {
        return file;
    }

    /**
     * Sets the name of the transaction file.
     * @param fileName: new name of the transaction file.
     */
    public void setFile(String fileName) {
        file = fileName;
    }
}

class PhoneTrans extends Transaction {
    private String representative;

    /**
     * Four-argument constructor that contains information on an order created via a phone call with customer service.
     * @param transactionNumber: the order number representing a specific transaction.
     * @param name: the name of customer.
     * @param state: the state location of customer.
     * @param serviceRep: the name of the customer service representative responsible for taking the order.
     */
    public PhoneTrans(String transactionNumber, String name, String state, String serviceRep) {
        super(transactionNumber, name, state);
        representative = serviceRep;
    }

    /**
     * @return the name of the customer service representative that created the order.
     */
    public String getRepresentative() {
        return representative;
    }

    /**
     * Sets the name of the customer service representative.
     * @param serviceRepName: the new name of the customer service representative.
     */
    public void setRepresentative(String serviceRepName) {
        representative = serviceRepName;
    }
}

public class Main {
    public static void main(String[] args) {
        // Testing OnlineTrans and PhoneTrans objects
        OnlineTrans orderNo1 = new OnlineTrans("#113-2524033-6994661", "Nhuy N.", "MN", "Order001");
        orderNo1.addProduct(001, "Mini Desk Calendar", 1.10, 5);
        orderNo1.addProduct(012, "Akko Mechanical Keyboard", 70.15, 3);
        orderNo1.addProduct(013, "Audio-Technica AT2020 Cardioid Condenser Microphone", 79.00, 1);
        orderNo1.addProduct(014, "Dead By Daylight DLC Pack", 2.49, 6);
        System.out.println("--Online Order 1--");
        System.out.println("Customer name: " + orderNo1.getCustomerName());
        System.out.println("Customer state: " + orderNo1.getCustomerState());
        System.out.println("Transaction number: " + orderNo1.getTransactionNumber());
        System.out.println("Processed order file: " + orderNo1.getFile());
        orderNo1.printReceipt();
        orderNo1.orderTotal();
        System.out.println("\n" + orderNo1.getTransactionNumber() + " changed.");
        orderNo1.removeProduct(013);
        orderNo1.modifyProduct(001, .99, 2);
        orderNo1.printReceipt();
        orderNo1.orderTotal();
        System.out.println();

        PhoneTrans orderNo2 = new PhoneTrans("#112-6240870-1920232", "McKyley C.", "IL", "Andy H.");
        orderNo2.addProduct(002, "Focusrite Scarlett Solo 3rd Gen USB Audio Interface", 129.00, 1);
        orderNo2.addProduct(021, "ELDEN RING", 59.99, 1);
        orderNo2.addProduct(022, "TUBBZ Vinyl Rubber Duck Figure", 16.82, 3);
        orderNo2.addProduct(023, "Mini Desk Calendar", .99, 10);
        orderNo2.addProduct(024, "Dymatize ISO100 Hydrolyzed Protein Powder", 33.43, 2);
        System.out.println("--Phone Order 1--");
        System.out.println("Customer name: " + orderNo2.getCustomerName());
        System.out.println("Customer state: " + orderNo2.getCustomerState());
        System.out.println("Transaction number: " + orderNo2.getTransactionNumber());
        System.out.println("Order processed by: " + orderNo2.getRepresentative());
        orderNo2.printReceipt();
        orderNo2.orderTotal();
        System.out.println("\n" + orderNo2.getTransactionNumber() + " changed.");
        orderNo2.removeProduct(024);
        orderNo2.modifyProduct(023, .99, 1);
        orderNo2.printReceipt();
        orderNo2.orderTotal();
    }
}