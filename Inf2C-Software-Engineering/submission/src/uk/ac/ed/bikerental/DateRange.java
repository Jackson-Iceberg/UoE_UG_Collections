package uk.ac.ed.bikerental;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Objects;

/**
 * 
 * @author ZHOU HAO ,YU HONHYU
 * @author S1862323,S1719616
 * 
 * <p>
 * this class is used to store LocalDate1 and LocalDate2.
 * it contains two function getStart() and getEnd(), which is date that localDate1 and localDate2, so it means LocalDate1 is the start day of the local and LocalDate2 is the end day.
 * The start day and the end day combine together to get a DateRange.
 * The key function is overlap(),which would take a dateRange as a parameter and return a boolean.
 * The true means that this bike is available to rent,and the false means that this bike is not available to rent.
 *          
 *
 *
 */


public class DateRange {
    private LocalDate start, end;
    
    public DateRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }
    
    public LocalDate getStart() {
        return this.start;
    }
    
    public LocalDate getEnd() {
        return this.end;
    }

    public long toYears() {
        return ChronoUnit.YEARS.between(this.getStart(), this.getEnd());
    }

    public long toDays() {
        return ChronoUnit.DAYS.between(this.getStart(), this.getEnd());
    }

    /**
     * This function use DateRange as parameter and return a boolean that represent whether the bike is available to rent.
     * The true means that this bike is available to rent,and the false means that this bike is not available to rent.
     * <p>
     * @param other : data type is DateRange
     * @return Boolean : true means available,false means not available
     * @see Boolean : true or false
     *      
     * 
     * 
     */
    
    
    
 
    public Boolean overlaps(DateRange other) {
        // TODO: implement date range intersection checking
        LocalDate oStart = other.getStart();
        LocalDate oEnd = other.getEnd();
        
        assert(!oEnd.isBefore(oStart)): "The input DateRange is invalid";
        
        if(oEnd.isBefore(this.start)||oStart.isAfter(this.end)){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        // hashCode method allowing use in collections
        return Objects.hash(end, start);
    }

    @Override
    public boolean equals(Object obj) {
        // equals method for testing equality in tests
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DateRange other = (DateRange) obj;
        return Objects.equals(end, other.end) && Objects.equals(start, other.start);
    }
    
    // You can add your own methods here
}
