package bookstoreproject;

import java.util.ArrayList;
import java.util.HashMap;

import bookstoreproject.inventory.*;
import bookstoreproject.sales.*;
import bookstoreproject.io.*;
import bookstoreproject.product.ProductInfo;

public class App {
    public String makeAnnouncement() {
        return "Starting Project";
    }

    public static void main(String[] args) {
        System.out.println(new App().makeAnnouncement());
        // Initialize the Inventory and Sales classes
        InventoryMgnt inventory_mgnt = new InventoryMgnt();
        InventoryItem bookItem = InventoryItem.createInventoryItem(inventory_mgnt, "Book", 20, 14.0);
        InventoryItem penItem = InventoryItem.createInventoryItem(inventory_mgnt, "Pen", 20, 2.0);
        InventoryItem pencilItem = InventoryItem.createInventoryItem(inventory_mgnt, "Pencil", 20, 1.0);
        InventoryItem stationaryItem = InventoryItem.createInventoryItem(inventory_mgnt, "Stationary", 20, 4.0);

        ArrayList<ItemEntry> entries = ItemEntry.readEntriesFromFile("inventory_items.txt");

        // Create and populate productInfoMap
        HashMap<String, ProductInfo> productInfoMap = new HashMap<>();
        productInfoMap.put("Book", bookItem.getProductInfo());
        productInfoMap.put("Pen", penItem.getProductInfo());
        productInfoMap.put("Pencil", pencilItem.getProductInfo());
        productInfoMap.put("Stationary", stationaryItem.getProductInfo());

        for (ItemEntry entry : entries) {
            System.out.println("Product: " + entry.getProduct());
            System.out.println("Quantity: " + entry.getQuantity());
            System.out.println("Price: " + entry.getPrice());
            System.out.println("-----------------------");
        }

        Sales sales = new Sales(inventory_mgnt);

        // Header
        System.out.printf("%-15s %-15s %-15s%n", "Product", "Availability", "Price");

        // Display availability and pricing of all items in the Inventory
        for (String product : productInfoMap.keySet()) {
            ProductInfo productInfo = productInfoMap.get(product);
            InventoryItem item = inventory_mgnt.getItem(productInfo);

            if (item != null) {
                System.out.printf("%-15s %-15s %-15.2f%n", product, inventory_mgnt.isAvailable(item, 1),
                        item.getPricingInfo().getPrice());
            } else {
                System.out.printf("%-15s %-15s %-15s%n", product, "Item Not Found", "N/A");
            }
        }

        // Perform some sales transactions and show results
        String[] productsToSell = {"Book", "Pencil", "Stationary"};
        int[] quantitiesToSell = {2, 5, 3,};  // Quantities for each product

        for(int i = 0; i < productsToSell.length; i++) {
            String product = productsToSell[i];
            int quantity = quantitiesToSell[i];

            try {
                ProductInfo productInfo = productInfoMap.get(product);
                if(productInfo == null) {
                    throw new IllegalArgumentException("The product " + product + " does not exist in the inventory.");
                }
                InventoryItem inventoryItem = inventory_mgnt.getItem(productInfo);
                boolean saleResult = sales.makeSale(inventoryItem, quantity);
                System.out.printf("Sold %d %s: %-5s%n", quantity, product, saleResult);
            } catch (Exception e) {
                System.out.println("Error processing sale for " + product + ": " + e.getMessage());
            }
        }

        // If there's a static SalesCounter class to track total sales
        // System.out.printf("Total Sales: %d%n", SalesCounter.totalSales);

        // Exit the program
        System.out.println("\nExiting the program.");
    }
}

