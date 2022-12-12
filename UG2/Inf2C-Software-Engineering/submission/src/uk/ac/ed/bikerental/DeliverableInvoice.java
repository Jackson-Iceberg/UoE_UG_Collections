package uk.ac.ed.bikerental;

public class DeliverableInvoice implements Deliverable{
    private String delivery_state;
    private Invoice invoice;
    private Location startLocation;
    private Location endLocation;

    public DeliverableInvoice(Invoice invoice, Location start, Location end) {
        this.delivery_state = "No schedual now";
        this.invoice = invoice;
        this.startLocation = start;
        this.endLocation = end;
     }
    
    public Invoice getInvoice() {
        return invoice;
    }
    public Location getStartLocation() {
        return startLocation;
    }
    public Location getEndLocation() {
        return endLocation;
    }
    
    public void setBikes(Invoice invoice) {
        this.invoice = invoice;
    }
    public void setStartLocation(Location location) {
        startLocation = location;
    }
    public void setEndLocation(Location location) {
        endLocation = location;
    }
    public String getStatus() {
        return delivery_state;
    }
    

    @Override
    public void onPickup() {
        delivery_state = "Waiting for Pick up";
        
    }

    @Override
    public void onDropoff() {
        delivery_state = "Waiting for Drop off";
        
    }

}
