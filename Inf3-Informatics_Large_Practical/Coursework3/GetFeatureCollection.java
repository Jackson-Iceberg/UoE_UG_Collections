package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;

public class GetFeatureCollection {

    public static FeatureCollection getPathGjson(ArrayList<Position> path) {
        List<Feature> featureCollection = new ArrayList<Feature>();

        ArrayList<Point> pointList = new ArrayList<Point>();

        for (Position position : path) {
            Point point = Point.fromLngLat(position.longitude, position.latitude);
            pointList.add(point);
        }
        LineString line = LineString.fromLngLats(pointList);
        Feature eachFeature = Feature.fromGeometry(line);
        featureCollection.add(eachFeature);
        return FeatureCollection.fromFeatures(featureCollection);
    }

    public static FeatureCollection getOutBoundary() {
        List<Feature> featureCollection = new ArrayList<Feature>();
        ArrayList<Point> pointList = new ArrayList<Point>();
        Point NW = Point.fromLngLat(-3.192473, 55.946233);
        Point NE = Point.fromLngLat(-3.184319, 55.946233);
        Point SW = Point.fromLngLat(-3.192473, 55.942617);
        Point SE = Point.fromLngLat(-3.184319, 55.942617);
        pointList.add(NW);
        pointList.add(NE);
        pointList.add(SE);
        pointList.add(SW);
        pointList.add(NW);
        LineString line = LineString.fromLngLats(pointList);
        Feature eachFeature = Feature.fromGeometry(line);
        featureCollection.add(eachFeature);
        return FeatureCollection.fromFeatures(featureCollection);
    }

    /**
     * Outputs a list of positions (flight path of the drone) as a FeatureCollection
     * object
     * 
     * @param path              - list of positions (flight path of the drone)
     * @param featurecollection - FeatureCollection object to be added with the list
     *                          of positions
     * @return FeatureColletion object
     */

    public static FeatureCollection getSensorListGjson(ArrayList<Sensor> sensorList)
            throws IOException, InterruptedException {
        List<Feature> featureCollection = new ArrayList<Feature>();

        for (Sensor sensor : sensorList) {
            sensor.checkBattery();
            sensor.setPosition();
            String reading = sensor.getReading();
            String battery = sensor.getBattery();
            String location = sensor.getLocation();
            Position position = sensor.getPosition();

            Point point_position = Point.fromLngLat(position.longitude, position.latitude);

            // build each Feature of one polygon
            Feature eachFeature = Feature.fromGeometry(point_position);

            eachFeature.addStringProperty("location", location);
            eachFeature.addStringProperty("reading", reading);
            eachFeature.addStringProperty("battery", battery);
            String color = GetSensorInformation.getRGB(reading);
            eachFeature.addStringProperty("marker-color", color);
            eachFeature.addStringProperty("rgb-string", color);
            String symbol = GetSensorInformation.getSymbol(reading);
            eachFeature.addStringProperty("marker-symbol", symbol);

            featureCollection.add(eachFeature);
        }
        return FeatureCollection.fromFeatures(featureCollection);
    }
}
