package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class QuoteProcessor {
    private Collection<Quote> quoteDisplay;
    private Collection<BikeProvider> satisfiedProvider;
    
    public QuoteProcessor() {
        quoteDisplay = new ArrayList<Quote>();
        satisfiedProvider = new ArrayList<BikeProvider>();
    }
    public Collection<BikeProvider> getSatisfiedProvider(){
        return satisfiedProvider;
    }
    public Collection<Quote> getQuoteDisplay(){
        return quoteDisplay;
    }
    public void initialiseQuoteDisplay() {
        quoteDisplay = new ArrayList<Quote>();
    }
    
    public void findSatisifiedProvider(RentalNeed rental_need, Collection<BikeProvider> providerSet){
        //Collection<BikeProvider> satisfiedProvider = new ArrayList<BikeProvider>();
        for(BikeProvider b : providerSet) {                                         // to check if one provider meet the rental needs 
            Location provider_location = b.getShopAddress();
            if(rental_need.getLocation().isNearTo(provider_location)) {             // check if the provider near to the customer location
                
                Map<BikeType, Integer> bike_need = rental_need.getBikeReq();        // the number of bike per each bike type in rental need
                Map<BikeType, Collection<Bike>> providerBike = b.getBikeInfo();   // the number of bike that provider have
                Set<BikeType> biketype_in_need = bike_need.keySet();                // the type of bike in rental need
                
                DateRange desire_date = rental_need.getDateDesire();
                int total_type_number = biketype_in_need.size();
                int count = total_type_number;
                for(BikeType bt : biketype_in_need) {
                    int biketype_need_number = bike_need.get(bt);                   // number of bikes needed per BikeType
                    if(providerBike.get(bt).size()>= biketype_need_number) {
                        Collection<Bike> bikes_in_same_type = providerBike.get(bt); // the bikes in a bike type that provider have
                        int count1 = biketype_need_number;
                        for(Bike bike: bikes_in_same_type) {
                            Collection<DateRange> state = bike.getState();
                            if(count1 > 0) {
                                boolean bikea = true;
                                boolean bikeE = false;
                                if(state.isEmpty()) { bikeE = true; }
                                else {
                                    for(DateRange date_booked : state) {
                                        if(!desire_date.overlaps(date_booked)) {
                                            bikea = false;
                                            break;
                                        }
                                    }
                                }
                                if(bikea || bikeE) {
                                    count1--;
                                }
                            
                            }
                       }
                       if(count1 == 0) { 
                           if(count>0) { count--; }
                       }
                    }
                }
                if(count == 0) { satisfiedProvider.add(b); }
            }
        }
    }
    
    public Collection<Quote> caculateQuote(RentalNeed rental_need, Collection<BikeProvider> providerSet, double a, int choosen_type, LocalDate ld){
        assert(!providerSet.isEmpty()): "No provider meets the rental needs.";
       
        for(BikeProvider b: providerSet) {
            Map<BikeType, BigDecimal> daily_rental_price = b.getBikeRP();
            Map<BikeType, Integer> bike_number = rental_need.getBikeReq();
            Map<BikeType, Collection<Bike>> provider_bike = b.getBikeInfo();
            Map<BikeType, Collection<Bike>> provdier_bikes_selected = new HashMap<BikeType, Collection<Bike>>();
            
            Set<BikeType> bike_type = bike_number.keySet();
            BigDecimal depositRate = b.getDepositRate();
            DateRange desire_date = rental_need.getDateDesire();
            int days = (int) desire_date.toDays() + 1;
            BigDecimal totalPrice = new BigDecimal(0);
            BigDecimal totalDeposit = new BigDecimal(0);
            
            for(BikeType bt: bike_type) {
                Collection<Bike> bikes_in_same_type = provider_bike.get(bt); // the bikes in a bike type that provider have
                Collection<Bike> bike_selected = new ArrayList<Bike>();
                int count1 = bike_number.get(bt);
                for(Bike bike: bikes_in_same_type) {
                    Collection<DateRange> state = bike.getState();
                    if(count1 > 0) {
                        boolean bikea = true;
                        boolean bikeE = false;
                        if(state.isEmpty()) { bikeE = true; }
                        else {
                            for(DateRange date_booked : state) {
                                if(!desire_date.overlaps(date_booked)) {
                                    bikea = false;
                                    break;
                                }
                            }
                        }
                        if(bikea || bikeE) {
                            count1--;
                            bike_selected.add(bike);
                            //calculate the deposit for each bike
                            if(a > 0 && choosen_type == 1) {
                                BigDecimal depreciate_rate = new BigDecimal(a);
                                LinearDepreciation calculate = new LinearDepreciation(depreciate_rate);
                                BigDecimal replacementValue = calculate.calculateValue(bike, ld);
                                BigDecimal deposit = replacementValue.multiply(depositRate);
                                totalDeposit = totalDeposit.add(deposit);
                            }else if(a > 0 && choosen_type == 2){
                                BigDecimal depreciate_rate = new BigDecimal(a);
                                DoubleDecliningBalanceDepreciation calculate = new DoubleDecliningBalanceDepreciation(depreciate_rate);
                                BigDecimal replacementValue = calculate.calculateValue(bike, ld);
                                BigDecimal deposit = replacementValue.multiply(depositRate);
                                totalDeposit = totalDeposit.add(deposit);
                            }else {
                                BigDecimal deposit = bt.getOriginalReplacementValue().multiply(depositRate);
                                totalDeposit = totalDeposit.add(deposit);
                            }
                        }
                    }     
                }
                provdier_bikes_selected.put(bt, bike_selected);
                //calculate the total rental price per bike type
                BigDecimal type_drp = daily_rental_price.get(bt);                                               //type's daily rental price
                BigDecimal type_total_price_per_day = type_drp.multiply(new BigDecimal(bike_number.get(bt)));   // result = type's daily rental price * number of bikes in this type
                BigDecimal type_total_price = type_total_price_per_day.multiply(new BigDecimal(days));          // result * number of days
                totalPrice = totalPrice.add(type_total_price);
            }
            totalPrice = totalPrice.setScale(2, RoundingMode.HALF_UP);
            totalDeposit = totalDeposit.setScale(2, RoundingMode.HALF_UP);
            // Quote : 1.BikeInformation (Map<BikeType,Integer>), 2.providerID, 3.desired dateRange, 4.total price, 5.total deposit, 6.provider's location
            Quote q = new Quote(bike_number,provdier_bikes_selected, b, rental_need.getDateDesire(), totalPrice, totalDeposit, b.getShopAddress());
            quoteDisplay.add(q);
        }
        return quoteDisplay;
    }
}
