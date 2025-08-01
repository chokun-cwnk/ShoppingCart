package Lib.Discont;

import Lib.CartItem;

public class BulkDiscountStrategy implements DiscountStrategy{
    private final int minimumQuantity;
    private final double discoutPercentage;

    public BulkDiscountStrategy(int minimumQuantity, double discoutPercentage){
        this.minimumQuantity = minimumQuantity;
        this.discoutPercentage = discoutPercentage;
    }

    @Override
    public double calculatePrice(CartItem item) {
        double originalPrice = item.getProduct().getPrice() * item.getQuantity();
        if(item.getQuantity() >= minimumQuantity){
            return originalPrice * (1.0 - discoutPercentage);
        }
        return originalPrice;
    }

    
}