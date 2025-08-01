package Lib;

public class Product {
    private final String productId;
    private final String productName;
    private final double price;

    private void checkRep(){
        if(productId == null || productId.isBlank()){
            throw new RuntimeException("RI violated : productId");
        }
        if(productName == null || productName.isBlank()){ 
            throw new RuntimeException("RI violated : productName");
        }
        if(price < 0){
            throw new RuntimeException("RI violated : price");
        }
    }
    
    public Product(String productId,String productName,double price){
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        checkRep();
    }

    public String getId(){
        return productId;
    }

    public String getName(){
        return productName;
    }

    public double getPrice(){
        return price;
    }

    public String getProductId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductId'");
    }
}
