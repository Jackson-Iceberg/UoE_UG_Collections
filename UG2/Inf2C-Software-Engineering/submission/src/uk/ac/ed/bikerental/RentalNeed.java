package uk.ac.ed.bikerental;

import java.util.HashMap;
import java.util.Map;

public class RentalNeed {

    private DateRange dateDesire;
    private Map<BikeType,Integer>bikeReq;
    private Location customerAddress; 
    
    public RentalNeed(DateRange d, Map<BikeType, Integer> bike_req, Location address) {
        // TODO Auto-generated constructor stub
        dateDesire = d;
        bikeReq = new HashMap<BikeType,Integer>();
        bikeReq.putAll(bike_req);
        customerAddress = address;
    }
    
    public void setDateDesire(DateRange d) {
        dateDesire = d;
    }
    public void setBikeReq(Map<BikeType, Integer> bike_req) {
        bikeReq = new HashMap<BikeType,Integer>();
        bikeReq.putAll(bike_req);
    }
    public void setLocation(Location address) {
        customerAddress = address;
    }
    
    public DateRange getDateDesire() {
        return dateDesire;
    }
    public Map<BikeType,Integer> getBikeReq() {
        return bikeReq;
    }
    public Location getLocation() {
        return customerAddress;
    }

}
