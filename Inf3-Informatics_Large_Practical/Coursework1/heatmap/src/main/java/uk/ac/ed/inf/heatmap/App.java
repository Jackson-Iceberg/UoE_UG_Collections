package uk.ac.ed.inf.heatmap;

import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.GeoJson;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Polygon;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class App {

    static String predictions = ReadFiledata.txt2String(new File("predictions.txt"));
    static int[][] predictionArray = ReadFiledata.stringList2intList(predictions);

    public static FeatureCollection getFeatureCollection() {
        
        Double diffLat = BigDecimal.valueOf((55.946233 - 55.942617) / 10).setScale(6, RoundingMode.HALF_UP).doubleValue();
        Double diffLng = BigDecimal.valueOf((3.192473 - 3.184319) / 10).setScale(6, RoundingMode.HALF_UP).doubleValue();

        List<Feature> featureCollection = new ArrayList<Feature>();
        
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                // build pointList
                // diffLng is controlled to move the longitude, which is relate to j
                // diffLat is controlled to move the latitude, which is relate to i
                // Point = (lng,lat)
                
                ArrayList<Point> pointList = new ArrayList<Point>();
                pointList.add(Point.fromLngLat(-3.192473 + diffLng*(j), 55.946233 - diffLat *(i)));
                pointList.add(Point.fromLngLat(-3.192473 + diffLng*(j+1), 55.946233 - diffLat *(i)));
                pointList.add(Point.fromLngLat(-3.192473 + diffLng*(j+1), 55.946233 - diffLat *(i+1)));
                pointList.add(Point.fromLngLat(-3.192473 + diffLng*(j), 55.946233 - diffLat *(i+1)));
                pointList.add(Point.fromLngLat(-3.192473 + diffLng*(j), 55.946233 - diffLat *(i)));
                    
                // build whole pointList
                var wholePoint = new ArrayList<List<Point>>();    
                wholePoint.add(pointList);

                // build one polygon
                Polygon eachPolygon = Polygon.fromLngLats(wholePoint);

                // build each Feature of one polygon
                Feature eachFeature = Feature.fromGeometry(eachPolygon);
                eachFeature.addNumberProperty("fill-opacity", 0.75);
                String color = Color.getRGB(predictionArray[i][j]);
                eachFeature.addStringProperty("fill", color);
                eachFeature.addStringProperty("rgb-string", color);
                featureCollection.add(eachFeature);
            }
        }
        return FeatureCollection.fromFeatures(featureCollection);
    }
   
    public static void main(String[] args) {

        FeatureCollection featureCollection = getFeatureCollection();
        String result = featureCollection.toJson();

        FileWriter writer;
        try {
            writer = new FileWriter("heatmap.geojson");
            writer.write(result);
            writer.flush();
            writer.close();
            System.out.println("Successfully wrote the file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
