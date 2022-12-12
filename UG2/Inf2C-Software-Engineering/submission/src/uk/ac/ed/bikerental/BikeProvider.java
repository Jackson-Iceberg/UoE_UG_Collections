package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class BikeProvider {
	private String ID;
	private Location shopAddress;
	private Boolean partnershipMember;
	private BigDecimal depositRate;
	private Map<BikeType, Collection<Bike>> bikeInfo;
	private Map<BikeType, BigDecimal> bikeRP;
	
	public BikeProvider(String name, Location shopAddress,Boolean partnershipMember, BigDecimal DepositRate, Map<BikeType, Collection<Bike>> bN, Map<BikeType, BigDecimal> bRP) {
		this.ID = name;
		this.shopAddress = shopAddress;
		this.partnershipMember = partnershipMember;
		this.depositRate = DepositRate;
		// ? how to equal
		this.bikeInfo = new HashMap<BikeType, Collection<Bike>>();
		this.bikeInfo.putAll(bN);
		
		this.bikeRP = bRP;
	}
	
	
	public String getID() {
		return ID;
	}
	public void setID(String name) {
		this.ID = name;
	}
	public Location getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(Location shopAddress) {
		this.shopAddress = shopAddress;
	}

	public Boolean getPartnershipMember() {
		return partnershipMember;
	}
	public void setPartnershipMember(Boolean partnershipMember) {
		this.partnershipMember = partnershipMember;
	}
	public BigDecimal getDepositRate() {
		return depositRate;
	}
	public void setDepositRate(BigDecimal depositRate2) {
		depositRate = depositRate2;
	}
	public Map<BikeType, Collection<Bike>> getBikeInfo(){
	    return bikeInfo;
	}
	public Map<BikeType, BigDecimal> getBikeRP(){
	    return bikeRP;
	}
	public void setBikeInfo(Map<BikeType, Collection<Bike>> bN) {
	    bikeInfo.clear();
	    bikeInfo.putAll(bN);
	}
	public void setBikeRP(Map<BikeType, BigDecimal> bRP) {
	    bikeRP.clear();
	    bikeRP.putAll(bRP);
	}
	
	public void updateBRP(BikeType type, BigDecimal dRP) {
	    assert(!this.bikeRP.containsKey(type)): "Provider doesn't contain the bike type" + type.getBikeType() + "/n";
	    this.bikeRP.put(type, dRP);
	 }
	
	public void updateBikeInfo(BikeType type, Collection<Bike> b) {
	    assert(!this.bikeInfo.containsKey(type)): "Provider doesn't contain the bike type" + type.getBikeType() + "/n";
	    this.bikeInfo.put(type, b);
	}
	
	public void addNewBikeTYPE(BikeType t, Collection<Bike> b, BigDecimal drp) {
	    // how to ensure
	    this.bikeInfo.put(t,b);
	    this.bikeRP.put(t, drp);
	}
	
	 public  DeliverableInvoice confirmBikeReturn(Invoice a) {
	        if(a.getBikeProviderId() == ID) {
	            Map<BikeType, Collection<String>> returnBikeInfo = a.getBikeInfo();
	            Map<BikeType, Collection<Bike>> provider_total_bike = bikeInfo;
	            Set<BikeType>return_type = returnBikeInfo.keySet();
	        
	            for(BikeType bt : return_type) {
	                Collection<Bike> provider_bikes_inType = provider_total_bike.get(bt);
	                Collection<String> return_bikesId_inType = returnBikeInfo.get(bt);
	            
	                for(String id: return_bikesId_inType) {
	                    for(Bike provider_bike: provider_bikes_inType) {
	                        if(provider_bike.getBikeId() == id) {
	                            provider_bike.setBikeInStoreState(true);
	                            break;
	                        }
	                    }
	                }
	                provider_total_bike.put(bt, provider_bikes_inType);
	            }
	            bikeInfo = provider_total_bike;
	            return null;
	        }
	        else {
	            // this bike provider behave as way of partner
	            assert(a.getPartnershipMember()): "Original Provider is not a member of the partnership";
	            assert(partnershipMember): "You are not a member of the partnership";
	            
	            DeliverableInvoice invoice = new DeliverableInvoice(a, shopAddress, a.getBikeProviderLocation());
	            
	            
	            return invoice;
	        }
	        
	    }

	
	
	
}
