package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class Invoice {
    
    private String bookNumber;
    private Map<BikeType,Collection<String>> bikeIDInfo;
    private DateRange bookedDateRange;
    private BigDecimal totalDeposit;
    private BigDecimal totalPrice;
    private String bikeProviderId;
    private Location providerLocation;
    private Boolean deliveryChoice;
    private Boolean partnershipMember;

    public Invoice(Map<BikeType,Collection<String>> bikeInfo,DateRange dateRange,String bookNumber,BigDecimal totalDeposit,BigDecimal totalPrice,Boolean deliveryChoice,Boolean partnershipMember,String id, Location location) {
        this.bookNumber = bookNumber;
        this.bikeIDInfo = bikeInfo;
        this.bookedDateRange = dateRange;
        this.totalDeposit = totalDeposit;
        this.totalPrice = totalPrice;
        this.deliveryChoice = deliveryChoice;
        this.partnershipMember = partnershipMember;
        this.bikeProviderId = id;
        this.providerLocation = location;
        
    }
    public Invoice(Map<BikeType,Collection<String>> bikeInfo,DateRange dateRange, Boolean partnershipMember,String id, Location location) {
        this.bikeIDInfo = bikeInfo;
        this.bookedDateRange = dateRange;
        this.bikeProviderId = id;
        this.providerLocation = location;
        this.partnershipMember = partnershipMember;
    }
    
    public String getBikeProviderId() {
        return bikeProviderId;
    }

    public void setBikeProviderId(String bikeProviderId) {
        this.bikeProviderId = bikeProviderId;
    }
    
    public Location getBikeProviderLocation() {
        return providerLocation;
    }

    public void setBikeProviderLocation(Location location) {
        this.providerLocation = location;
    }
    
    public Boolean getPartnershipMember() {
        return partnershipMember;
    }

    public void setPartnershipMember(Boolean partnershipMember) {
        this.partnershipMember = partnershipMember;
    }

    public Boolean getDeliveryChoice() {
        return deliveryChoice;
    }

    public void setDeliveryChoice(Boolean deliveryChoice) {
        this.deliveryChoice = deliveryChoice;
    }

    public String getBookNumber() {
        return bookNumber;
    }
    public void setBookNumber(String bookNumber) {
        this.bookNumber = bookNumber;
    }
    
    public Map<BikeType, Collection<String>> getBikeInfo() {
        return bikeIDInfo;
    }

    public void setBikeInfo(Map<BikeType, Collection<String>> bikeInfo) {
        this.bikeIDInfo = bikeInfo;
    }

    public BigDecimal getTotalDeposit() {
        return totalDeposit;
    }
    public void setTotalDeposit(BigDecimal totalDeposit) {
        this.totalDeposit = totalDeposit;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }
    public DateRange getBookedDateRange() {
        return bookedDateRange;
    }

    public void setBookedDateRange(DateRange dateRange) {
        this.bookedDateRange = dateRange;
    }
    
    
    
   
    
}
