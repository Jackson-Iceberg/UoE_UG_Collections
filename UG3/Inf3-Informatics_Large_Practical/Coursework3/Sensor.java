package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;

public class Sensor {
    private String reading;
    private String battery;
    private String location;
    private Position position;

    public Sensor(String reading, String battery, String location, Position position) {
        this.reading = reading;
        this.battery = battery;
        this.location = location;
        this.position = position;
    }

    /**
     * Sets the reading of the sensor
     * 
     * @param reading value
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    

    /**
     * Gets the what3words location of the sensor
     * 
     * @return what3words location of the sensor
     */
    public String getLocation() {
        return this.location;
    }

    /**
     * Gets the amount of air quality in the sensor
     * 
     * @return the amount of air quality in the sensor
     */
    public String getReading() {
        return this.reading;
    }

    /**
     * Gets the amount of battery available in the sensor
     * 
     * @return the amount of battery available in the sensor
     */
    public String getBattery() {
        return this.battery;
    }

    /**
     * 
     * @param Sensor
     * @return Position of the sensor
     * @throws IOException
     * @throws InterruptedException
     */
    // Gets the sensor position.
    public Position getSensorPosition() throws IOException, InterruptedException {
        String location = this.location;
        String urlLocation = location.replace(".", "/");
        urlLocation = location.replace(".", "/");
        urlLocation = "http://localhost:80/words/" + urlLocation + "/details.json";

        String SensorDetails = JsonToJava.DeserialisingJSon(urlLocation);
        w3words words = JsonToJava.getW3Words(SensorDetails);
        Double lng = (Double) words.coordinates.lng;
        Double lat = (Double) words.coordinates.lat;
        Position result = new Position(lng, lat);
        return result;
    }

    public void setPosition() throws IOException, InterruptedException {
        this.position = getSensorPosition();
    }

    public Position getPosition() {
        return this.position;
    }

    // if the battery value is null or NaN,then sets the sensor reading to negative
    // which means low battery and RGB String is #000000
    public void checkBattery() {
        if ((battery == "null") || (battery == "NaN")) {
            setReading("-1.0");
        }
        if (Double.parseDouble(battery) <= 10) {
            setReading("-1.0");
        }
    }
}