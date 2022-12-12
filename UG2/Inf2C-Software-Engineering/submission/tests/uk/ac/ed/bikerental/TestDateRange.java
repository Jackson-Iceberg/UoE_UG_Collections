package uk.ac.ed.bikerental;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestDateRange {
    
    private  LocalDate start1 ;
    private  LocalDate end1 ;
    private  LocalDate start2 ;
    private  LocalDate end2 ;
    private  LocalDate start3 ;
    private  LocalDate end3 ;
    private  LocalDate start4 ;
    private  LocalDate end4 ;
    private DateRange duration1;
    private DateRange duration2;
    private DateRange duration3;
    private DateRange duration4;
    
    @BeforeEach
    void setUp() throws Exception {
        this.start1 = LocalDate.of(2019, 1, 1);
        this.end1 = LocalDate.of(2019, 2, 19);
        this.duration1 = new DateRange(start1, end1);

        this.start2 = LocalDate.of(2019, 3, 1);
        this.end2 = LocalDate.of(2019, 4, 13);
        this.duration2 = new DateRange(start2, end2);

        this.start3 = LocalDate.of(2019, 5, 1);
        this.end3 = LocalDate.of(2019, 6, 5);
        this.duration3 = new DateRange(start3, end3);

        this.start4 = LocalDate.of(2019, 6, 5);
        this.end4 = LocalDate.of(2019, 8, 2);
        this.duration4 = new DateRange(start4, end4);
    }
    @Test
    public void DateRange1() {
        assert(duration1.overlaps(duration2));
        
    }
    @Test
    public void DateRange2() {
        assert(duration1.overlaps(duration3));
        
    }
    @Test
    public void DateRange3() {
        assert(!duration3.overlaps(duration4));
        
    }
    @Test
    public void DateRange4() {
        assert(duration4.overlaps(duration2));
        
    }
    
    
}

