package bookstoreproject.inventory;

import java.util.HashMap;
import bookstoreproject.product.*;

public class InventoryMgnt {

    // HashMap to store inventory items
    private HashMap<ProductInfo, InventoryItem> inventory;
    private HashMap<String, ProductInfo> productInfoMap;

    public InventoryMgnt() {
        this.inventory = new HashMap<>();
        this.productInfoMap = new HashMap<>(); // Initialize the productInfoMap
    }


    // Add an item to the inventory
    public void addItem(ProductInfo productInfo, InventoryItem item) {
        this.inventory.put(productInfo, item);
        productInfoMap.put(productInfo.getProductInfoName(), productInfo);
    }

    // Retrieve an item from the inventory based on its ProductInfo
    public InventoryItem getItem(ProductInfo productInfo) {
        return this.inventory.get(productInfo);
    }

    // Check if an item exists in the inventory based on its ProductInfo
    public boolean containsItem(ProductInfo productInfo) {
        return this.inventory.containsKey(productInfo);
    }

    // Remove an item from the inventory based on its ProductInfo
    public void removeItem(ProductInfo productInfo) {
        this.inventory.remove(productInfo);
    }    

    public HashMap<String, ProductInfo> getProductInfoMap(ProductInfo productInfo) {
        return productInfoMap;
    }

    // Check if the requested quantity of an item is available
    public boolean isAvailable(InventoryItem item, int requiredQuantity) {
        return item.getQuantityInfo().getQuantity() >= requiredQuantity;
    }


    // Decrement the quantity of an item by a given amount
    public void decrementQuantity(InventoryItem item, int decrementBy) {
        // Get the current quantity, set the new quantity and check if 
        int currentQuantity = item.getQuantityInfo().getQuantity();
        int newQuantity = currentQuantity - decrementBy;
        newQuantity = Math.max(newQuantity, 0);

        // Update the quantity
        item.getQuantityInfo().setQuantity(newQuantity);
    }
}

