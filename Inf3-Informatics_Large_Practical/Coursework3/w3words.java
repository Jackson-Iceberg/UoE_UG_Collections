package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;

public class w3words {
    Compass coordinates;
    String words;

    public w3words(Compass coordinates, String words) {
        this.coordinates = coordinates;
        this.words = words;
    }

    public static class Compass {
        double lng;
        double lat;
    }

    public final Compass getCoordinates() {
        return coordinates;
    }

    public final void setCoordinates(Compass coordinates) {
        this.coordinates = coordinates;
    }

    public final String getWords() {
        return words;
    }

    public final void setWords(String words) {
        this.words = words;
    }
}
