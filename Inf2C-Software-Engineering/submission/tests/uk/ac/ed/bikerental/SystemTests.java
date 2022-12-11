package uk.ac.ed.bikerental;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SystemTests {
    // You can add attributes here
    
    private RentalSystem RentalSystem;
    private RentalNeed rental_need;
    private QuoteProcessor quote_processor;
    private BikeType biketype1;
    private BikeType biketype2;
    private BikeType biketype3;
    private BikeType biketype4;
    private Collection<DateRange> date1;
    private Collection<DateRange> date2;
    private Collection<DateRange> date3;
    
    private Bike bike1;
    private Bike bike2;
    private Bike bike3;
    private Bike bike4;
    
    private Bike bike101;
    private Bike bike102;
    private Bike bike103;
    private Bike bike104;
    private Bike bike105;
    private Bike bike106;
    private Bike bike107;
    private Bike bike108;
    private Bike bike109;
    private Bike bike110;
    private Bike bike201;
    private Bike bike202;
    private Bike bike203;
    private Bike bike204;
    private Bike bike205;
    private Bike bike206;
    private Bike bike207;
    private Bike bike208;
    private Bike bike209;
    private Bike bike210;
    
    private BikeProvider BikeProvider1;
    private BikeProvider BikeProvider2;
    private BikeProvider BikeProvider3;

    @BeforeEach
    void setUp() throws Exception {
        // Setup mock delivery service before each tests
        DeliveryServiceFactory.setupMockDeliveryService();
        // Put your test setup here
        RentalSystem = new RentalSystem(new ArrayList<BikeProvider>());
        quote_processor = new QuoteProcessor();
        
        date1 = new ArrayList<DateRange>();
        date2 = new ArrayList<DateRange>();
        date3 = new ArrayList<DateRange>();
        date2.add(new DateRange(LocalDate.of(2019, 11, 10), LocalDate.of(2019, 11, 15)));
        
        date3.add(new DateRange(LocalDate.of(2019, 11, 18), LocalDate.of(2019, 11, 20)));
        date3.add(new DateRange(LocalDate.of(2019, 10, 20),LocalDate.of(2019, 10, 24)));
        
        
        biketype1 = new BikeType("Roadbicycle", new BigDecimal(500));
        biketype2 = new BikeType("Trackbicycle", new BigDecimal(700));
        biketype3 = new BikeType("Mountainbicycle", new BigDecimal(1000));
        biketype4 = new BikeType("e-bicycle", new BigDecimal(2000));
        
        bike1 = new Bike("301",biketype1,date2,LocalDate.of(2018, 1, 10),true);
        bike2 = new Bike("302",biketype1,date3,LocalDate.of(2018, 1, 10),true);
        bike3 = new Bike("303",biketype2,date1,LocalDate.of(2018, 1, 10),true);
        bike4 = new Bike("304",biketype3,date2,LocalDate.of(2018, 1, 10),true);
        
        bike101 = new Bike("001", biketype1, date1, LocalDate.of(2018, 05, 20), true);
        bike102 = new Bike("002", biketype1, date1, LocalDate.of(2018, 2, 3),true);
        bike103 = new Bike("003", biketype1, date2, LocalDate.of(2018, 2, 3),true); 
        bike104 = new Bike("004", biketype1, date1, LocalDate.of(2018, 9, 3),true);
        bike105 = new Bike("005", biketype2, date1, LocalDate.of(2018, 9, 3),true);
        bike106 = new Bike("006", biketype2, date1, LocalDate.of(2018, 9, 3),true);
        bike107 = new Bike("007", biketype2, date2, LocalDate.of(2018, 9, 3),true);
        bike108 = new Bike("008", biketype3, date1, LocalDate.of(2018, 9, 3),true);
        bike109 = new Bike("009", biketype3, date1, LocalDate.of(2018, 9, 3),true);
        bike110 = new Bike("010", biketype3, date2, LocalDate.of(2018, 9, 3),true);
        
        bike201 = new Bike("011", biketype1, date2, LocalDate.of(2018, 5, 20),false);
        bike202 = new Bike("012", biketype1, date2, LocalDate.of(2018, 3, 3),false);
        bike203 = new Bike("013", biketype1, date1, LocalDate.of(2018, 2, 3),true); 
        bike204 = new Bike("014", biketype2, date1, LocalDate.of(2018, 9, 3),true);
        bike205 = new Bike("015", biketype2, date1, LocalDate.of(2018, 9, 3),true);
        bike206 = new Bike("016", biketype2, date2, LocalDate.of(2018, 9, 3),false);
        bike207 = new Bike("017", biketype3, date2, LocalDate.of(2018, 9, 3),false);
        bike208 = new Bike("018", biketype3, date3, LocalDate.of(2018, 9, 3),true);
        bike209 = new Bike("019", biketype4, date1, LocalDate.of(2018, 9, 3),true);
        bike210 = new Bike("020", biketype4, date3, LocalDate.of(2018, 9, 3),true);
        
        // for BikeProvider1
        Collection<Bike> typebike1 = new ArrayList<Bike>();
        Collection<Bike> typebike2 = new ArrayList<Bike>();
        Collection<Bike> typebike3 = new ArrayList<Bike>();
        
        Map<BikeType, Collection<Bike>> map_typebike = new HashMap<BikeType, Collection<Bike>>();
        Map<BikeType, BigDecimal> map_bikedrp = new HashMap<BikeType, BigDecimal>();
       
        typebike1.add(bike101);
        typebike1.add(bike102);
        typebike1.add(bike103);
        typebike1.add(bike104);
        map_typebike.put(biketype1, typebike1);
        map_bikedrp.put(biketype1, new BigDecimal(10));
        typebike2.add(bike105);
        typebike2.add(bike106);
        typebike2.add(bike107);
        map_typebike.put(biketype2, typebike2);
        map_bikedrp.put(biketype2, new BigDecimal(15));
        typebike3.add(bike108);
        typebike3.add(bike109);
        typebike3.add(bike110);
        map_typebike.put(biketype3, typebike3);
        map_bikedrp.put(biketype3, new BigDecimal(20));
        BikeProvider1 = new BikeProvider("1", new Location("EH1 5JR", "Address1"),true, new BigDecimal(0.2), map_typebike, map_bikedrp);
        
        //for BikeProvider2
        Collection<Bike> typebike4 = new ArrayList<Bike>();
        Collection<Bike> typebike5 = new ArrayList<Bike>();
        Collection<Bike> typebike6 = new ArrayList<Bike>();
        Collection<Bike> typebike7 = new ArrayList<Bike>();
        Map<BikeType, Collection<Bike>> map_typebike2 = new HashMap<BikeType, Collection<Bike>>();
        Map<BikeType, BigDecimal> map_bikedrp2 = new HashMap<BikeType, BigDecimal>();
        typebike4.add(bike201);
        typebike4.add(bike202);
        typebike4.add(bike203);
        map_typebike2.put(biketype1, typebike4);
        map_bikedrp2.put(biketype1, new BigDecimal(15));
     
        typebike5.add(bike204);
        typebike5.add(bike205);
        typebike5.add(bike206);
        map_typebike2.put(biketype2, typebike5);
        map_bikedrp2.put(biketype2, new BigDecimal(20));
        typebike6.clear();
        typebike6.add(bike207);
        typebike6.add(bike208);
        map_typebike2.put(biketype3, typebike6);
        map_bikedrp2.put(biketype3, new BigDecimal(10));
        typebike7.clear();
        typebike7.add(bike209);
        typebike7.add(bike210);
        map_typebike2.put(biketype4, typebike7);
        map_bikedrp2.put(biketype4, new BigDecimal(40));
        BikeProvider2 = new BikeProvider("2", new Location("EH2 5FR", "Address2"),true, new BigDecimal(0.3), map_typebike2, map_bikedrp2);
        // for BikeProvider3
        Collection<Bike> typebike8 = new ArrayList<Bike>();
        Collection<Bike> typebike9 = new ArrayList<Bike>();
        Collection<Bike> typebike10 = new ArrayList<Bike>();
        Collection<Bike> typebike11 = new ArrayList<Bike>();
        Map<BikeType, Collection<Bike>> map_typebike3 = new HashMap<BikeType, Collection<Bike>>();
        Map<BikeType, BigDecimal> map_bikedrp3 = new HashMap<BikeType, BigDecimal>();
        typebike8.add(bike1);
        map_typebike3.put(biketype1, typebike8);
        map_bikedrp3.put(biketype1, new BigDecimal(10));
        typebike9.add(bike2);
        map_typebike3.put(biketype2, typebike9);
        map_bikedrp3.put(biketype2, new BigDecimal(20));
        typebike10.add(bike3);
        map_typebike3.put(biketype3, typebike10);
        map_bikedrp3.put(biketype3, new BigDecimal(30));
        typebike11.add(bike4);
        map_typebike3.put(biketype4, typebike11);
        map_bikedrp3.put(biketype4, new BigDecimal(40));
        BikeProvider3 = new BikeProvider("3", new Location("EH4 9FR", "Address3"),true, new BigDecimal(0.4), map_typebike3, map_bikedrp3);
        // for RentalNeed
        Map<BikeType,Integer>desired_bike = new HashMap<BikeType,Integer>();
        desired_bike.put(biketype1, 2);
        desired_bike.put(biketype2, 2);
        desired_bike.put(biketype3, 1);
        rental_need = new RentalNeed((new DateRange(LocalDate.of(2019, 11, 14), LocalDate.of(2019, 11, 19))),desired_bike, new Location("EH2 2YG","Address3"));
        Collection<BikeProvider> bp = new ArrayList<BikeProvider>();
        bp.add(BikeProvider1);
        bp.add(BikeProvider2);
        bp.add(BikeProvider3);
        RentalSystem.setBikeProviderColl(bp);
    }
    
 

    @Test
    void findQuoteTest() {
        // test the find quote, rental needs is (2019,11,14 ~ 2019,11,19), (2 biketype1, 2 biketype2, 1 biketype3)
        
        ArrayList<BikeProvider> meeted_provider = new ArrayList<BikeProvider>();
        meeted_provider.add(BikeProvider1);
        
        // test the providers who satisfied the rental need： result: provider1 meet
        quote_processor.findSatisifiedProvider(rental_need, RentalSystem.getBikeProviderColl());
        ArrayList<BikeProvider> result = (ArrayList<BikeProvider>) quote_processor.getSatisfiedProvider();
        assertEquals(meeted_provider, result);
        
        // test the deposit without the submodule
        ArrayList<Quote> result1 = (ArrayList<Quote>) quote_processor.caculateQuote(rental_need, result, 0, 0, LocalDate.now());
        assertEquals(meeted_provider.size(), result1.size());
        
        Map<BikeType,Collection<Bike>> expect_bikeInfo = new HashMap<BikeType,Collection<Bike>>();
        Collection<Bike> bikeforType1 = new ArrayList<Bike>();
        bikeforType1.add(bike101);
        bikeforType1.add(bike102);
        expect_bikeInfo.put(biketype1, bikeforType1);
        Collection<Bike> bikeforType2 = new ArrayList<Bike>();
        bikeforType2.add(bike105);
        bikeforType2.add(bike106);
        expect_bikeInfo.put(biketype2, bikeforType2);
        Collection<Bike> bikeforType3 = new ArrayList<Bike>();
        bikeforType3.add(bike108);
        expect_bikeInfo.put(biketype3, bikeforType3);
        
        // result only have one quote
        // default deposit policy
        for(Quote q: result1) {
            assertEquals(new BigDecimal(420).setScale(2, RoundingMode.HALF_UP) , q.getTotalPrice());
            assertEquals(new BigDecimal(680).setScale(2, RoundingMode.HALF_UP) , q.getDeposit());
            assertEquals(expect_bikeInfo, q.getBikeInfo());
            assertEquals(BikeProvider1, q.getBikeProvider());
            assertEquals(rental_need.getDateDesire(), q.getDesiredDate());
            assertEquals(BikeProvider1.getShopAddress(), q.getProviderLocation());
            assertEquals(rental_need.getBikeReq(), q.getbikeNumInfo());
        }
        // test the deposit in linear
        quote_processor.initialiseQuoteDisplay();
        ArrayList<Quote> result2 = (ArrayList<Quote>) quote_processor.caculateQuote(rental_need, result, 0.2, 1, LocalDate.of(2019, 11, 30));
        for(Quote q: result2) {
            assertEquals(new BigDecimal(420).setScale(2, RoundingMode.HALF_UP), q.getTotalPrice());
            assertEquals(new BigDecimal(544).setScale(2, RoundingMode.HALF_UP), q.getDeposit());
        }
        // test the deposit in Double
        quote_processor.initialiseQuoteDisplay();
        ArrayList<Quote> result3 = (ArrayList<Quote>) quote_processor.caculateQuote(rental_need, result, 0.2, 2, LocalDate.of(2019, 11, 30));
        for(Quote q: result3) {
            assertEquals(new BigDecimal(420).setScale(2, RoundingMode.HALF_UP), q.getTotalPrice());
            assertEquals(new BigDecimal(408).setScale(2, RoundingMode.HALF_UP), q.getDeposit());
        }
        
     
        
        
        // check cudtomer bookQuote (Quote quote,Boolean deliveryChoice) 
        // this function in Customer
        Quote quoteTest = result3.get(0);
        Customer c = new Customer(rental_need);
        // customer book the quote
        Invoice invoiceTest = c.bookQuote(quoteTest,true);
        if(invoiceTest.getDeliveryChoice()) {
            MockDeliveryService delivery = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
            DeliverableInvoice di = new DeliverableInvoice(invoiceTest,c.getRentalNeed().getLocation(), quoteTest.getProviderLocation());
            LocalDate start = invoiceTest.getBookedDateRange().getStart();
            delivery.scheduleDelivery(di, di.getStartLocation(), di.getEndLocation(), start);
        }
        
        
        Map<BikeType,Collection<String>> invioce_right_bikeInfo = new HashMap<>();
        Collection<String> id1 = new ArrayList<>();
        Collection<String> id2 = new ArrayList<>();
        Collection<String> id3 = new ArrayList<>();
        id1.add("001");
        id1.add("002");
        id2.add("005");
        id2.add("006");
        id3.add("008");
        invioce_right_bikeInfo.put(biketype1, id1);
        invioce_right_bikeInfo.put(biketype2, id2);
        invioce_right_bikeInfo.put(biketype3, id3);
        Invoice invoiceRight = new Invoice(invioce_right_bikeInfo,new DateRange(LocalDate.of(2019, 11, 14), LocalDate.of(2019, 11, 19)),"Edinburgh110",quoteTest.getDeposit(),quoteTest.getTotalPrice(),true,true,BikeProvider1.getID(),BikeProvider1.getShopAddress());
        assertEquals(invoiceTest.getBikeInfo(),invoiceRight.getBikeInfo());
        assertEquals(invoiceTest.getBikeProviderId(),invoiceRight.getBikeProviderId());
        assertEquals(invoiceTest.getBookedDateRange(),new DateRange(LocalDate.of(2019, 11, 14), LocalDate.of(2019, 11, 19)));
        assertEquals(invoiceTest.getBikeProviderLocation(),invoiceRight.getBikeProviderLocation());
        assertEquals(invoiceTest.getBookNumber(),invoiceRight.getBookNumber());
        assertEquals(invoiceTest.getDeliveryChoice(),invoiceRight.getDeliveryChoice());
        assertEquals(invoiceTest.getPartnershipMember(),invoiceRight.getPartnershipMember());
        assertEquals(invoiceTest.getTotalDeposit(),invoiceRight.getTotalDeposit());
        assertEquals(invoiceTest.getTotalPrice(),invoiceRight.getTotalPrice());
        
// check dateRange whether is put into.
        
        Quote quoteTestDateRange = result3.get(0);
        //Quote quoteRightDateRange = result3.get(0);
        //System upDate the provider's information once the Quote is booked
        Map<BikeType,Collection<Bike>> test = new HashMap<>();
        RentalSystem.updateProviderDateRange(quoteTestDateRange);
        
        // to test whether system update the bike state once the bike is booked
        boolean result_DateRange = true;
        DateRange bookedDate = quoteTestDateRange.getDesiredDate();
        for (BikeProvider BP: RentalSystem.getBikeProviderColl()) {
            if(quoteTestDateRange.getBikeProvider().getID()==BP.getID()) {
                for(BikeType BT : test.keySet()) {
                    for (Bike b1 : test.get(BT)) {
                        for(Bike b2 : BP.getBikeInfo().get(BT)) {
                            if(b2.getBikeId() == b1.getBikeId()) {
                                Collection<DateRange> system_DateRange = b2.getState();
                                for(DateRange dr: system_DateRange) {
                                    if (!dr.overlaps(bookedDate)) {
                                        result_DateRange = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        assert (result_DateRange);
    }
        
    
        
    
    @Test
    void returnBikeTest() {
        Map<BikeType,Collection<String>> invoice_bikesId = new HashMap<>();
        Collection<String> bikesId1 = new ArrayList<>();
        bikesId1.add("011");
        bikesId1.add("012");
        Collection<String> bikesId2 = new ArrayList<>();
        bikesId2.add("016");
        Collection<String> bikesId3 = new ArrayList<>();
        bikesId3.add("017");
        invoice_bikesId.put(biketype1, bikesId1);
        invoice_bikesId.put(biketype2, bikesId2);
        invoice_bikesId.put(biketype3, bikesId3);
     
        Invoice invoice = new Invoice(invoice_bikesId, new DateRange(LocalDate.of(2019, 11, 10), LocalDate.of(2019, 11, 15)),true, "2", BikeProvider2.getShopAddress());
        ArrayList<BikeProvider> providers = (ArrayList<BikeProvider>) RentalSystem.getBikeProviderColl();
        //customer return bike to the original : provider2
        // here we know the BikeProvider2 is in the index 1, but in the next compare(asserEquals), need to compare Id to determine where the BikeProvider is.
        BikeProvider bp2 = providers.get(1);
        DeliverableInvoice db = bp2.confirmBikeReturn(invoice);
        //update the rental system after BikeProvider confirm the return of the bike
        
        assert(db == null);
        RentalSystem.updateProvider(bp2);
        
        // find the expect value, then to compare
        Collection<Bike> typebike4 = new ArrayList<Bike>();
        Collection<Bike> typebike5 = new ArrayList<Bike>();
        Collection<Bike> typebike6 = new ArrayList<Bike>();
        Collection<Bike> typebike7 = new ArrayList<Bike>();
        Map<BikeType, Collection<Bike>> map_typebike2 = new HashMap<BikeType, Collection<Bike>>();
        Map<BikeType, BigDecimal> map_bikedrp2 = new HashMap<BikeType, BigDecimal>();
        bike201.setBikeInStoreState(true);
        bike202.setBikeInStoreState(true);
        bike206.setBikeInStoreState(true);
        bike207.setBikeInStoreState(true);
        
        typebike4.add(bike201);
        typebike4.add(bike202);
        typebike4.add(bike203);
        map_typebike2.put(biketype1, typebike4);
        map_bikedrp2.put(biketype1, new BigDecimal(15));
     
        typebike5.add(bike204);
        typebike5.add(bike205);
        typebike5.add(bike206);
        map_typebike2.put(biketype2, typebike5);
        map_bikedrp2.put(biketype2, new BigDecimal(20));
        typebike6.clear();
        typebike6.add(bike207);
        typebike6.add(bike208);
        map_typebike2.put(biketype3, typebike6);
        map_bikedrp2.put(biketype3, new BigDecimal(10));
        typebike7.clear();
        typebike7.add(bike209);
        typebike7.add(bike210);
        map_typebike2.put(biketype4, typebike7);
        map_bikedrp2.put(biketype4, new BigDecimal(40));
        
        BikeProvider expect_provider = new BikeProvider("2", new Location("EH2 5FR", "Address2"),true, new BigDecimal(0.3), map_typebike2, map_bikedrp2);
        ArrayList<BikeProvider> system_bikeProviders = (ArrayList<BikeProvider>) RentalSystem.getBikeProviderColl();
        for(BikeProvider bp: system_bikeProviders) {
            if(bp.getID() == expect_provider.getID()) {
                System.out.println("Yes now compare the BikeProvider and the expect value");
                assertEquals(bp.getBikeInfo(), expect_provider.getBikeInfo());
                break;
            }
        }
        
        //customer return the bike to the partner Provider1
        
        BikeProvider bp1 = providers.get(0);
        DeliverableInvoice dI = bp1.confirmBikeReturn(invoice);
        
        assert(dI != null);
        MockDeliveryService delivery = (MockDeliveryService) DeliveryServiceFactory.getDeliveryService();
        DateRange booked_date = dI.getInvoice().getBookedDateRange();
        LocalDate delivery_date = booked_date.getEnd(); 
        delivery.scheduleDelivery(dI, bp1.getShopAddress(), dI.getEndLocation(), delivery_date);
        delivery.carryOutPickups(delivery_date);
        delivery.carryOutDropoffs();
        assert(delivery.getPickupsOn(delivery_date) == null);
        assert(dI.getStatus() == "Waiting for Drop off");
    
    }
}
