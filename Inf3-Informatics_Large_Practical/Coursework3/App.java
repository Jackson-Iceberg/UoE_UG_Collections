package uk.ac.ed.inf.aqmaps;

import com.mapbox.geojson.FeatureCollection;
import java.util.*;
import com.mapbox.geojson.GeoJson;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
import com.mapbox.geojson.LineString;
import java.io.*;

public class App {
    public static java.util.Random rnd;

    List<Feature> featureCollection = new ArrayList<Feature>();
    
    // This method takes Gjson style String and the corresponding accurate date as the parameter
    //  This method return nothing but it will write a Gjson file in the current file.
    public static void writeGjsonFile(String result,String year,String month,String day) {
        FileWriter writer;
        try {
            String str = "readings-" + day + "-" + month + "-" + year +".geojson";
            writer = new FileWriter(str);
            writer.write(result);
            writer.flush();
            writer.close();
            System.out.println("readings-" + day + "-" + month + "-" + year +".geojson" + " Successfully wrote the file!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    // This method takes the String and the corresponding accurate date as the parameter
    //  This method return nothing but it will write a txt file in the current file.
    public static void writePathFile(String pathFileArray,String year,String month,String day) {
        
        FileWriter writer;
        try {
            String str = "flightpath-" + day + "-" + month + "-" + year +".txt";
            writer = new FileWriter(str);
            writer.write(pathFileArray);
            writer.flush();
            writer.close();
            System.out.println("flightpath-" + day + "-" + month + "-" + year +".txt" + " Successfully wrote the file!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    
    
   

    public static void main(String[] args) throws IOException, InterruptedException {
        // check the number of args
        if (args.length != 7) {
            System.out.println("Error! The number of input is not right!");
            System.exit(0);
        }
        
        List<String> daylist = new ArrayList<>();
        daylist.add("01");
        daylist.add("02");
        daylist.add("03");
        daylist.add("04");
        daylist.add("05");
        daylist.add("06");
        daylist.add("07");
        daylist.add("08");
        daylist.add("09");
        daylist.add("10");
        daylist.add("11");
        daylist.add("12");
        daylist.add("13");
        daylist.add("14");
        daylist.add("15");
        daylist.add("16");
        daylist.add("17");
        daylist.add("18");
        daylist.add("19");
        daylist.add("20");
        daylist.add("21");
        daylist.add("22");
        daylist.add("23");
        daylist.add("24");
        daylist.add("25");
        daylist.add("26");
        daylist.add("27");
        daylist.add("28");
        daylist.add("29");
        daylist.add("30");
        daylist.add("31");
        
        for (String day:daylist) {
        
                       // getting the data from argument
        //               String day = args[0];
                       String month = args[1];
                       String year = args[2];
                       double latitude = Double.parseDouble(args[3]);
                       double longitude = Double.parseDouble(args[4]);
                       int seed = Integer.parseInt(args[5]);
                       int port = Integer.parseInt(args[6]);

                       // initialize all the necessary thing
                       Position startingPoint = new Position(longitude, latitude);
                       ArrayList<Position> path = new ArrayList<Position>();
                       path.add(startingPoint);
                       Drone drone = new Drone(startingPoint);
                       String pathFileArray = "";
                       
                   
                   // getting the sensors java style file and it can be used to get the sensorList. 
                   // the string includes 33 sensors and each one have w3words location,value of battery and air-quality-data reading.
                   // The sensorList_fc is the feature collection of the sensor.  
                   String string = "http://localhost:" + port + "/maps" + "/" + year + "/" + month + "/" + day + "/air-quality-data.json";
                   String str_sensorList = JsonToJava.DeserialisingJSon(string);
                   ArrayList<Sensor> sensorList = JsonToJava.getSensorList(str_sensorList);
                   FeatureCollection sensorList_fc = GetFeatureCollection.getSensorListGjson(sensorList);

                   // getting the NoFlyZone java style file and it can be used to get the Feature Collection of the NoFlyZone
                   // And it also can be used to form List<list<Point>> which can be used to define the NoFlyZone in java.
                   String str_noFlyZone = "http://localhost:" + port + "/buildings/no-fly-zones.geojson";
                   String str_noFlyZone_featureCollection = JsonToJava.DeserialisingJSon(str_noFlyZone);
                   FeatureCollection noFlyZone_fc = FeatureCollection.fromJson(str_noFlyZone_featureCollection);
                   List<List<Point>> nfz_list = NoFlyZone.getNoFlyZone(noFlyZone_fc);
                   
                   ArrayList<Sensor> sensorPath = new ArrayList<Sensor>();
                   ArrayList<Sensor> greedyPath = new ArrayList<Sensor>();
                   int list_size = sensorList.size();
                   
                   
                   boolean end = false;
                   while(drone.checkMoves()&&!end) {
                           Position currPos = drone.currPos;
                           
                           
                           
                           if(sensorList.isEmpty()) {
                               ArrayList<Integer> goodDirection = currPos.getGoodDirection(nfz_list);
                               int direction_size = goodDirection.size();   
                               int direction = goodDirection.get(0);
                               int index = direction;
                               Position nextPosition = currPos.nextPosition(direction);
                               double MiniDistance = nextPosition.distanceFromPosition(startingPoint);
                               for (int i = 0;i<direction_size;i++) {
                                   Position position = currPos.nextPosition(goodDirection.get(i));
                                   double distance = position.distanceFromPosition(startingPoint);
                                   if (distance < MiniDistance) {
                                       index = i;
                                       MiniDistance = distance;
                                       nextPosition = position;
                               }
                           }
                               int path_index = 1 + drone.moves;
                               int index_for_file = index*10;
                               pathFileArray = pathFileArray + path_index + "," 
                                             + drone.currPos.longitude + ","
                                             + drone.currPos.latitude + ","
                                             + index_for_file + ","
                                             + nextPosition.longitude + ","
                                             + nextPosition.latitude + ",";
                               
                               
                               path.add(nextPosition);
                               drone.move(nextPosition);
                               pathFileArray = pathFileArray + "null" + "\n";
                               if (drone.currPos.distanceFromPosition(startingPoint)<0.0002) {
                                   end = true;
                                   System.out.println(drone.moves);
                                   break;
                               }
                               
                           }
                               
                           else {    
                           
                           
  
                           Sensor closestSensor = currPos.getClosestSensor(sensorList);
                           // ArrayList<Integer> goodDirection = new ArrayList<>();
                           ArrayList<Integer> goodDirection = currPos.getGoodDirection(nfz_list);
                           
                           
                           
                           
                           int direction_amount = goodDirection.size();
                           int direction_value = goodDirection.get(0);
                           int direction_index = 0;
                           Position nextPosition = currPos.nextPosition(direction_value);
                           
                           
                           
                           for (int i = 0;i<goodDirection.size();i++) {
                               direction_value = goodDirection.get(i);
                               nextPosition = currPos.nextPosition(direction_value);
                               
                               for (Position p:path) {
                                   if (nextPosition.latitude == p.latitude && nextPosition.longitude == p.longitude) {
                                       goodDirection.remove(i);
                                   }
                               }
                           }
                         
                           
                           
                           int direction_size = goodDirection.size();   
                           int direction = goodDirection.get(0);
                           int index = direction;
                           nextPosition = currPos.nextPosition(direction);
                           double MiniDistance = nextPosition.distanceFromPosition(closestSensor.getPosition());
                           
                           for (int i = 0;i<direction_size;i++) {
                               Position position = currPos.nextPosition(goodDirection.get(i));
                               double distance = position.distanceFromPosition(closestSensor.getPosition());
                               if (distance < MiniDistance) {
                                   index = i;
                                   MiniDistance = distance;
                                   nextPosition = position;
                           }
                           }
                           
                           int path_index = 1 + drone.moves;
                           int index_for_file = index*10;
                           pathFileArray = pathFileArray + path_index + "," 
                                         + drone.currPos.longitude + ","
                                         + drone.currPos.latitude + ","
                                         + index_for_file + ","
                                         + nextPosition.longitude + ","
                                         + nextPosition.latitude + ",";
                           
                           
                         path.add(nextPosition);
                         drone.move(nextPosition);
                           
                       if (drone.currPos.inReadingRange(closestSensor)) {
                           pathFileArray = pathFileArray + closestSensor.getLocation() + "\n"; 
                           sensorList.remove(closestSensor);
                       }
                       else {
                         pathFileArray = pathFileArray + "null" + "\n";
                     }

                       
                   }
                   }
                   
                   FeatureCollection path_fc = GetFeatureCollection.getPathGjson(path);
                   for (Feature f : noFlyZone_fc.features()) {
                       sensorList_fc.features().add(f);
                   }
                   FeatureCollection outBoundary_fc = GetFeatureCollection.getOutBoundary();
                   for (Feature f : outBoundary_fc.features()) {
                       sensorList_fc.features().add(f);
                   }
                   for (Feature f : path_fc.features()) {
                       sensorList_fc.features().add(f);
                   }

                   String result = sensorList_fc.toJson();

                   writeGjsonFile(result,year,month,day);
                   writePathFile(pathFileArray,year,month,day);
                  
                           
                           
                   
        } 
}
}




