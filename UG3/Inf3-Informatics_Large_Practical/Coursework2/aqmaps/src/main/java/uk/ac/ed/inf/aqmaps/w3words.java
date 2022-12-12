package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;

public class w3words {
    // two attributes coordinates and words
    Compass coordinates;
    String words;
    
    
    /**
     * Initialize the w3words
     * @param coordinates
     * @param words
     */
    public w3words(Compass coordinates, String words) {
        this.coordinates = coordinates;
        this.words = words;
    }
    
    // creates the related Compass class which has two attribute: lng and lat
    // The lng is type of double and represent the value of longitude
    // The lat is type of double and represent the value of latitude
    public static class Compass {
        double lng;
        double lat;
    }
    
    /**
     * Gets the coordinates of the Compass
     * 
     * @return the coordinates of the Compass
     */
    public final Compass getCoordinates() {
        return coordinates;
    }
    
    /** 
     * Sets the coordinates of the Compass
     * 
     * @param coordinates of the Compass
     */
    public final void setCoordinates(Compass coordinates) {
        this.coordinates = coordinates;
    }
    
    /**
     * get the String of the words
     * 
     * @return the words string
     */
    public final String getWords() {
        return words;
    }

    /** sets String of the words
     * 
     * @param words
     */
    public final void setWords(String words) {
        this.words = words;
    }
}
