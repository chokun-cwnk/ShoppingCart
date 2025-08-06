package Lib;
import java.util.*;

public class ShoppingCart {
    private final List<CartItem> items;
    private final PricingService pricingService;
    private final ProductCatalog productCatalog;

    public ShoppingCart(PricingService pricingService, ProductCatalog productCatalog) {
        if (pricingService == null || productCatalog == null) {
            throw new IllegalArgumentException("PricingService and ProductCatalog cannot be null.");
        }
        this.items = new ArrayList<>();
        this.pricingService = pricingService;
        this.productCatalog = productCatalog;
        checkRep();
    }

    private void checkRep() {
        if (items == null) {
            throw new IllegalStateException("Internal list of items is null.");
        }

        Set<String> productIds = new HashSet<>();
        for (CartItem item : items) {
            if (item.getProduct() == null) {
                throw new IllegalStateException("CartItem contains a null product.");
            }
            if (!productIds.add(item.getProduct().getId())) {
                throw new IllegalStateException("Duplicate product found in cart: " + item.getProduct().getId());
            }
        }
    }

    public void addItem(String productId, int quantity) {
        // ตรวจสอบ quantity ที่ถูกต้อง
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be positive.");
        }

        // ใช้ ProductCatalog เพื่อค้นหา Product 
        Product product = productCatalog.findById(productId);
        if (product != null && quantity > 0) {
            // ตรวจสอบว่ามีอยู่ในตะกร้าแล้วหรือไม่ 
            CartItem existingItem = findItemInCart(product);
            if (existingItem != null) {
                // ถ้ามี ให้เพิ่มจำนวน 
                existingItem.increaseQuantity(quantity);
            } else {
                // ถ้าไม่มี ให้สร้าง CartItem ใหม่ 
                items.add(new CartItem(product, quantity));
            }
        }
        checkRep(); // เรียกใช้ checkRep() หลังจากเปลี่ยนแปลงสถานะ 
    }

    public void removeItem(String productId) {
        // ลบสินค้าออกจากตะกร้า
        Product product = productCatalog.findById(productId);
        if (product != null) {
            CartItem itemToRemove = findItemInCart(product);
            if (itemToRemove != null) {
                items.remove(itemToRemove);
            } else {
                throw new IllegalArgumentException("Item not found in cart.");
            }
        }
    }

    public double getTotalPrice() {
        double total = 0.0;
        // วนลูปในตะกร้าและใช้ PricingService ในการคำนวณราคาสุทธิ 
        for (CartItem item : items) {
            total += pricingService.calculateItemPrice(item);
        }
        return total;
    }
    
    private CartItem findItemInCart(Product product) {
        for (CartItem item : items) {
            if (item.getProduct().equals(product)) {
                return item;
            }
        }
        return null;
    }
    public int getItemCount() {
        // คืนค่าจำนวนสินค้าที่มีในตะกร้า
        return items.size();
    }
    public void clearCart() {
        // ล้างตะกร้า
        items.clear();
        checkRep(); // เรียกใช้ checkRep() หลังจากล้างตะกร้า
    }
}
