package Lib.Discont;

import Lib.CartItem;

public class BogoDiscountStrategy implements DiscountStrategy{

    @Override
    public double calculatePrice(CartItem item) {
        int quantity = item.getQuantity();
        double price = item.getProduct().getPrice();
        int quantityTopay = (quantity / 2) + (quantity % 2);
        return price * quantityTopay;
    }
}