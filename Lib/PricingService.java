package Lib;

import java.util.ArrayList;

import Lib.Discont.DiscountStrategy;

public class PricingService {
    ArrayList<String> sku;
    ArrayList<DiscountStrategy> discountStrategies;

    public PricingService() {
        sku = new ArrayList<>();
        discountStrategies = new ArrayList<>();
    }

    // เพื่อลงทะเบียนโปรโมชั่นสำหรับรหัสสินค้า
    public void addStrategy(String productId, DiscountStrategy strategy) {
    
        if(!sku.contains(productId)) {
            sku.add(productId);
            discountStrategies.add(strategy);
        }
        
    }

    // คำนวณราคาสินค้าโดยใช้กลยุทธ์ที่ลงทะเบียนไว้
    public double calculateItemPrice(CartItem item) {
        int index = sku.indexOf(item.getProduct().getId());
        if (index != -1) {
            DiscountStrategy strategy = discountStrategies.get(index);
            return strategy.calculatePrice(item);
        } else {
            // ถ้าไม่มีโปรโมชั่นสำหรับสินค้านั้นๆ ให้คืนราคาปกติ
            return item.getProduct().getPrice() * item.getQuantity();
        }
    }
}
