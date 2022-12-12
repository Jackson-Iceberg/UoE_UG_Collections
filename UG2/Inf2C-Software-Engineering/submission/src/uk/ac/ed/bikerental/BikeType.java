package uk.ac.ed.bikerental;
import java.math.BigDecimal;

public class BikeType {
    private String bikeType; 
    private BigDecimal originalReplacementValue;
        
    public BikeType(BigDecimal rv) {
        originalReplacementValue = rv;
    }
    
    public BikeType (String bikeType, BigDecimal originalReplacementValue) {
        this.bikeType = bikeType;
        this.originalReplacementValue = originalReplacementValue;
    }
    

    public BigDecimal getOriginalReplacementValue() {
        return originalReplacementValue;
    }        
    public String getBikeType(){
        return bikeType;
    }

    public void setBikeType(String bikeType) {
        this.bikeType = bikeType;
    }
    

    public void setOriginalReplacementValue(BigDecimal o) {
        this.originalReplacementValue = o;
    }
   
}