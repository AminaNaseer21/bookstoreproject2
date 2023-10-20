package bookstoreproject.inventory;

import bookstoreproject.product.*;
import bookstoreproject.pricing.*;
import bookstoreproject.quantity.*;

public  class InventoryItem {
    private ProductInfo productInfo = new ProductInfo();
    private QuantityInfo quantityInfo = new QuantityInfo();
    private PricingInfo pricingInfo = new PricingInfo();

    public InventoryItem(String name, int quantity, double price) {
        this.productInfo.setProductInfoName(name);
        this.quantityInfo.setQuantity(quantity);
        this.pricingInfo.setPrice(price);
    }

    public QuantityInfo getQuantityInfo() {
        return quantityInfo;
    }

    public ProductInfo getProductInfo(){
        return productInfo;
    }

    public PricingInfo getPricingInfo(){
        return pricingInfo;
    }

    public static InventoryItem createInventoryItem(InventoryMgnt manager, String name, int quantity, double price) {
        InventoryItem item = new InventoryItem(name, quantity, price);
        manager.addItem(item.getProductInfo(),item);
        return item;
    }
   
}
