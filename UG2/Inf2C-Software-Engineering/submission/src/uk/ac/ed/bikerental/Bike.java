package uk.ac.ed.bikerental;

import java.util.ArrayList;
import java.util.Collection;
import java.time.LocalDate;

public class Bike {
    private String BikeId;
    private BikeType type;
    private Collection<DateRange> bikeState;
    //private String owner;
    private LocalDate manuDate;
    private Boolean bikeInStore;
    
    
    public Bike(BikeType t, LocalDate mDate) {
        type = t;
        manuDate = mDate;
    }
    
    public Bike(String id,BikeType t, Collection<DateRange> bS, LocalDate date, Boolean a) {
        BikeId = id;
        type = t;
        bikeState = new ArrayList<DateRange>();
        bikeState.addAll(bS);
        //owner = name;
        manuDate = date;
        bikeInStore = a;
    }
    
    // the getter function
    public String getBikeId() {
        return this.BikeId;
    }
    public BikeType getType() {
        return this.type;
    }
    public Collection<DateRange> getState() {
        return this.bikeState;
    }
    //public String getOwner() {
        //return this.owner;
    //}
    public LocalDate getManuDate() {
        return this.manuDate;
    }
    public boolean getBikeInStoreState() {
        return bikeInStore;
    }
    
    // the setter function
    public void setType(BikeType t) {
        this.type = t;
    }
    public void setState(Collection<DateRange> bS) {
        this.bikeState.clear();
        this.bikeState.addAll(bS);
    }
    //public void setOwner(String name) {
        //this.owner = name;
    //}
    public void setManuDate(LocalDate date) {
        this.manuDate = date;
    }
    public void setBikeInStoreState(Boolean a) {
        this.bikeInStore = a;
    }
    
    
}