package bookstoreproject.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ItemEntry {
    private String product;
    private int quantity;
    private double price;

    public ItemEntry(String product, int quantity, double price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }
    
    public static ArrayList<ItemEntry> readEntriesFromFile(String filename) {
        ArrayList<ItemEntry> entries = new ArrayList<>();
        
        try (Scanner scanner = new Scanner(new File(filename))) {
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                
                
                String product = parts[0].trim();
                if (product.startsWith("\"") && product.endsWith("\"")) {
                    product = product.substring(1, product.length() - 1);
                }
    
                int quantity = Integer.parseInt(parts[1].trim());
                double price = Double.parseDouble(parts[2].trim());
                entries.add(new ItemEntry(product, quantity, price));
            }
        }catch (IOException e) {
            System.err.println("Error reading from the file: " + e.getMessage());
        } 
        return entries;
    }

    public String getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

