package uk.ac.ed.bikerental;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

public class Quote {
	private Map<BikeType,Collection<Bike>> bikeInfo ;
	private BikeProvider bikeProvider;
	private DateRange desiredDate;
	private BigDecimal totalPrice;
	private BigDecimal deposit;
	private Location providerLocation;
	private Map<BikeType,Integer> bikeNumInfo;
	
	public Quote(Map<BikeType,Integer> bikeNumInfo,Map<BikeType,Collection<Bike>> bikeInfo, BikeProvider bikeProvider, DateRange desiredDate,BigDecimal totalPrice,BigDecimal deposit,Location providerLocation) {
		this.bikeInfo = bikeInfo;
		this.bikeProvider = bikeProvider;
		this.desiredDate = desiredDate;
		this.totalPrice = totalPrice;
		this.deposit = deposit;
		this.providerLocation = providerLocation;
		this.bikeNumInfo = bikeNumInfo;
	}
	public Map<BikeType,Integer> getbikeNumInfo(){
	    return bikeNumInfo;
	}
	public void setbikeNumInfo(Map<BikeType,Integer> bikeNumInfo) {
	    this.bikeNumInfo = bikeNumInfo;
	}
	
	public Map<BikeType,Collection<Bike>> getBikeInfo() {
		return bikeInfo;
	}

	public void setBikeInfo(Map<BikeType,Collection<Bike>> bikeInfo) {
		this.bikeInfo = bikeInfo;
	}

	public BikeProvider getBikeProvider() {
		return bikeProvider;
	}

	public void setBikeProvider(BikeProvider bp) {
		this.bikeProvider = bp;
	}

	public DateRange getDesiredDate() {
		return desiredDate;
	}

	public void setDesiredDate(DateRange desiredDate) {
		this.desiredDate = desiredDate;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDeposit() {
		return deposit;
	}

	public void setDeposit(BigDecimal deposit) {
		this.deposit = deposit;
	}

	public Location getProviderLocation() {
		return providerLocation;
	}

	public void setProviderLocation(Location providerLocation) {
		this.providerLocation = providerLocation;
	}
	
	
	
}
