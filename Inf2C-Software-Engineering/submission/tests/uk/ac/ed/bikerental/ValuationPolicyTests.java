package uk.ac.ed.bikerental;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.junit.jupiter.api.*;

public class ValuationPolicyTests {
    // You can add attributes here
    private BikeType bikeType1;
    private BikeType bikeType2;
    
    private LocalDate manuDate1;
    private LocalDate manuDate2;
    
    private Bike bike1;
    private Bike bike2;
    
    private LocalDate currentDate;
    
    private LinearDepreciation linear;
    private DoubleDecliningBalanceDepreciation doubleDeclining;
    
    
    
    

    @BeforeEach
    void setUp() throws Exception {
        // Put setup here
        this.bikeType1 = new BikeType(new BigDecimal(900));
        this.manuDate1 = LocalDate.of(2016, 2, 2);
        this.bike1 = new Bike(bikeType1, manuDate1);
        
        this.bikeType2 = new BikeType(new BigDecimal(300));
        this.manuDate2 = LocalDate.of(2019, 11, 11);
        this.bike2 = new Bike(bikeType2, manuDate2);
        
        this.currentDate = LocalDate.of(2019, 11, 29);
        this.linear = new LinearDepreciation(new BigDecimal(0.1));
        this.doubleDeclining = new DoubleDecliningBalanceDepreciation(new BigDecimal(0.1));
        }
    
    // TODO: Write tests for valuation policies
    
    @Test
    public void ValuationPolicy1() {
        assertEquals(new BigDecimal(630).setScale(2, RoundingMode.HALF_UP), linear.calculateValue(bike1, currentDate));
        
        BigDecimal r = doubleDeclining.calculateValue(bike1, currentDate);
        assertEquals(new BigDecimal(460.8).setScale(2, RoundingMode.HALF_UP),r);
    }
    
    @Test
    public void ValuationPolicy2() {
        assertEquals(new BigDecimal(300).setScale(2, RoundingMode.HALF_UP), linear.calculateValue(bike2, currentDate));
        assertEquals(new BigDecimal(300).setScale(2, RoundingMode.HALF_UP), doubleDeclining.calculateValue(bike2, currentDate));
    }
    
}
