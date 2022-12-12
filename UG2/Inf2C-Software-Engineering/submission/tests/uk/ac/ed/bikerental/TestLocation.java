package uk.ac.ed.bikerental;
import org.junit.jupiter.api.*;

public class TestLocation {
   private Location location1;
   private Location location2;
   private Location location3;
   private Location location4;
   

   @BeforeEach
   void setUp() throws Exception {
       this.location1 = new Location("EH3 9FEEEE","Chalmer street");
       this.location2 = new Location("EH3 9GEEEE","Simpson loan");
       this.location3 = new Location("LD2 9FEEEE","VitaEEEE");
       this.location4 = new Location("JD2 90EE","EEWarwickEEEE");
   }
   @Test
   public void Location1() {
       assert(location1.isNearTo(location2));
       
   }
   @Test
   public void Location2() {
       if (location2.isNearTo(location3)) {
           return;
       }
   }
   @Test
   public void Location3() {
       if (location3.isNearTo(location4)) {
           return;
       }
   }    
   @Test
   public void Location4() {
       if (location1.isNearTo(location1)) {
           return;
       }
   }
}
