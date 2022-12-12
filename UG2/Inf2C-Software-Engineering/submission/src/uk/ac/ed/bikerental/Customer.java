package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Customer {
    private RentalNeed rentalNeed;
    
    public Customer(RentalNeed rental_need) {
        rentalNeed = rental_need;
    }
    
    // for getter function
 
    public RentalNeed getRentalNeed(){
        return rentalNeed;
    }
    
    //for setter function
    public void setRentalNeed(RentalNeed a) {
        this.rentalNeed = a; 
    }
    public Invoice bookQuote (Quote quote,Boolean deliveryChoice) {
        BigDecimal totalPrice = quote.getTotalPrice();
        BigDecimal totalDeposit = quote.getDeposit();
        
        Map<BikeType,Collection<String>> bikeInfo = new HashMap<BikeType,Collection<String>>();
        Map<BikeType,Collection<Bike>> bikes = quote.getBikeInfo();
        Set<BikeType> bikeType = bikes.keySet();
        for(BikeType bt: bikeType) {
            Collection<String> bikeIds = new ArrayList<String>();
            Collection<Bike> bikes_in_same_type = bikes.get(bt);
            for(Bike b : bikes_in_same_type) {
                bikeIds.add(b.getBikeId());
            }
            bikeInfo.put(bt, bikeIds);
        }
        String bookNumber = "Edinburgh110";
        Boolean partnershipMember = quote.getBikeProvider().getPartnershipMember();
        
       
        Invoice invoice = new Invoice(bikeInfo,quote.getDesiredDate(),bookNumber,totalDeposit,totalPrice,deliveryChoice,partnershipMember, quote.getBikeProvider().getID(), quote.getProviderLocation());
        return invoice;
        }
        
      
 
}
