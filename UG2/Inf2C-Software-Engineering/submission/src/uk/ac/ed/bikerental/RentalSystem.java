package uk.ac.ed.bikerental;

import java.util.Collection;

public class RentalSystem {
    
    private Collection<BikeProvider> BikeProviderColl;
    
    public RentalSystem(Collection<BikeProvider> b) {
        BikeProviderColl = b;
    }

    public Collection<BikeProvider> getBikeProviderColl() {
        return BikeProviderColl;
    }

    public void setBikeProviderColl(Collection<BikeProvider> bikeProviderColl) {
        BikeProviderColl = bikeProviderColl;
    }
    
    public void updateProviderDateRange(Quote quote) {
        for (BikeProvider b : BikeProviderColl) {
           if(b.getID() == quote.getBikeProvider().getID()) {
               for(BikeType bt :b.getBikeInfo().keySet()) {
                   
                   for (Bike bOfq :(Collection<Bike>) quote.getBikeInfo().get(bt)) {
                       for(Bike bOfp : b.getBikeInfo().get(bt)) {
                           if (bOfq.getBikeId() == bOfp.getBikeId()) {
                                   bOfp.getState().add(quote.getDesiredDate());
                                  
                           }
                       }
                   }
               }
                  
            }
        }
    }
    
    public void updateProvider(BikeProvider bp) {
        Collection<BikeProvider> bpSet = BikeProviderColl;
        for(BikeProvider b: bpSet) {
            if(b.getID() == bp.getID()) {
                BikeProviderColl.remove(b);
                BikeProviderColl.add(bp);
                break;
            }
        }
    }
}
