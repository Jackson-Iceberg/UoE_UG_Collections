package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;

public class Sensor {
    // reading represent the value of the air quality
    // battery represent the value of the sensor battery
    // location represent the what3words of the sensor
    // position represent the Position of the sensor
    private String reading;
    private String battery;
    private String location;
    private Position position;

    
    /**
     * Initialize the Sensor
     * @param reading
     * @param battery
     * @param location
     * @param position
     * 
     */
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
     * 
     * Because the sensor only have the what3words location and we need the Position.
     * Then use its location to get the corresponding Position and reset it to form a complete Sensor
     * Also, the getPosition() is different with the getSensorPosition()
     * The getSensorPosition is used as getting the data from Http and deserialize the json file.
     * The getPosition() is used to get the position value of the sensor, only using it after resetting the sensor attributes
     * 
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
    
    
    /**
     * 
     * @throws IOException
     * @throws InterruptedException
     * 
     * This method is used to reset the position of the sensor.
     * Invoking the getSensorPosition() and return nothing
     * 
     */
    public void setPosition() throws IOException, InterruptedException {
        this.position = getSensorPosition();
    }

    /**
     * 
     * @return the position of the sensor
     * It only used after resetting the sensor position otherwise it will be null
     * 
     */
    public Position getPosition() {
        return this.position;
    }

    /**
     * this method is used to check the battery of the sensor
     * if the battery value is null or NaN,then sets the sensor reading to negative
     * which means low battery and RGB String is #000000
     */
    public void checkBattery() {
        if ((battery == "null") || (battery == "NaN")) {
            setReading("-1.0");
        }
        if (Double.parseDouble(battery) <= 10) {
            setReading("-1.0");
        }
    }
}