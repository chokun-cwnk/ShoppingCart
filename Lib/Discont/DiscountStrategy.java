package Lib.Discont;

import Lib.CartItem;

public interface DiscountStrategy {
    double calculatePrice(CartItem item);
}