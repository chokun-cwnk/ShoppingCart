package Lib;

public class CartItem {
    private final Product product;
    private final int quantity;

    private void checkRep(){
        if(product == null){
            throw new RuntimeException("RI violated : product");
        }
        
        if(quantity <= 0){
            throw new RuntimeException("RI violated : quantity");
        }
    }

    public CartItem(Product product,int quantity){
        this.product = product;
        this.quantity = quantity;
        checkRep();
    }

    public Product getProduct(){
        return product;
    }

    public int getQuantity(){
        return quantity;
    }
}
