package Lib;

import java.util.ArrayList;

import Lib.Discont.DefaultPricingStrategy;
import Lib.Discont.DiscountStrategy;

public class PricingService {
    private record StrategyRule(String sku,DiscountStrategy strategy) {}
    private final ArrayList<StrategyRule> strategies = new ArrayList<>();
    private final DiscountStrategy defaultStrategy = new DefaultPricingStrategy();

    public void addStrategy(String sku,DiscountStrategy strategy){
        StrategyRule ruleToRemove = null ;
        for(StrategyRule rule : strategies){
            if (rule.sku().equals(sku)){
                ruleToRemove = rule ;
                break;
            }
        }
        if (ruleToRemove != null){
            strategies.remove(ruleToRemove);
        }
        strategies.add(new StrategyRule(sku, strategy));
    }

    public double calculateItemPrice(CartItem item){
        String sku =  (String) item.getProduct().getProductId();
        for(StrategyRule rule : strategies){
            if (rule.sku().equals(sku)){
                return rule.strategy().calculatePrice(item);
            }
        }
        return defaultStrategy.calculatePrice(item);
    }
}
