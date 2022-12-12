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

    
    
    /**
     *  
     * @param result
     * @param year
     * @param month
     * @param day
     * This method takes Gjson style String and the corresponding accurate date as the parameter
     * This method return nothing but it will write a .Gjson file in the current file.
     */
    
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
    
     
    /**
     * 
     * @param pathFileArray
     * @param year
     * @param month
     * @param day
     * This method takes the String and the corresponding accurate date as the parameter
     * This method return nothing but it will write a .txt file in the current file.
    
     */
    public static void writeTxtFile(String pathFileArray,String year,String month,String day) {
        
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
        
        
        
                       // getting the data from argument
                       String day = args[0];
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
                           
                           
                           // THis means the drone have visited all the sensor and decided to go back
                           if(sensorList.isEmpty()) {
                               ArrayList<Integer> goodDirection = currPos.getGoodDirection(nfz_list);
                               int direction_size = goodDirection.size();   
                               int direction = goodDirection.get(0);
                               int index = direction;
                               Position nextPosition = currPos.nextPosition(direction);
                               double MiniDistance = nextPosition.distanceFromPosition(startingPoint);
                               
                               // get the best position to reduce the total moves
                               for (int i = 0;i<direction_size;i++) {
                                   Position position = currPos.nextPosition(goodDirection.get(i));
                                   double distance = position.distanceFromPosition(startingPoint);
                                   if (distance < MiniDistance) {
                                       index = i;
                                       MiniDistance = distance;
                                       nextPosition = position;
                               }
                           }
                               
                               // used to write a pathFileArray String
                               int path_index = 1 + drone.moves;
                               int index_for_file = index*10;
                               pathFileArray = pathFileArray + path_index + "," 
                                             + drone.currPos.longitude + ","
                                             + drone.currPos.latitude + ","
                                             + index_for_file + ","
                                             + nextPosition.longitude + ","
                                             + nextPosition.latitude + ",";
                               
                               // add the nextPosition into the path
                               path.add(nextPosition);
                               // move the drone
                               drone.move(nextPosition);
                               // add the necessary information into pathFileArray
                               pathFileArray = pathFileArray + "null" + "\n";
                               if (drone.currPos.distanceFromPosition(startingPoint)<0.0002) {
                                   end = true;
                                   System.out.println(drone.moves);
                                   break;
                               }
                               
                           }
                               
                           else {    
                           
                           
                           // get the closest Sensor   
                           Sensor closestSensor = currPos.getClosestSensor(sensorList);
                           // get the arrayList of good direction
                           ArrayList<Integer> goodDirection = currPos.getGoodDirection(nfz_list);
                           ArrayList<Integer> goodDirection_backup = new ArrayList<Integer>();
                           goodDirection_backup = currPos.getGoodDirection(nfz_list);
                           
                           
                           
                           // pick one direction and its related position
                           int direction_value = goodDirection.get(0);
                           Position nextPosition = currPos.nextPosition(direction_value);
                           
                           
                           // remove the position have been visited to avoid get stuck
                           // if it doesn't remove, it might make the drone keep move between two Position.
                           for (int i = 0;i<goodDirection.size();i++) {
                               direction_value = goodDirection.get(i);
                               nextPosition = currPos.nextPosition(direction_value);
                               
                               for (Position p:path) {
                                   if (nextPosition.latitude == p.latitude && nextPosition.longitude == p.longitude) {
                                       // avoid out of boundary, lots of time ignore it
                                       while (i>=goodDirection.size()) {
                                           i--;
                                       }
                                       // remove it
                                       goodDirection.remove(i);
                                   }
                               }
                           }
                         
                           
                           // find out the best direction and its next position
                           // By comparing the distance between next Position and sensor
                           // find out the closest one to the target sensor
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
                           
                           // used to write a pathFileArray String
                           int path_index = 1 + drone.moves;
                           int index_for_file = index*10;
                           pathFileArray = pathFileArray + path_index + "," 
                                         + drone.currPos.longitude + ","
                                         + drone.currPos.latitude + ","
                                         + index_for_file + ","
                                         + nextPosition.longitude + ","
                                         + nextPosition.latitude + ",";
                           
                        // add the nextPosition into the path
                         path.add(nextPosition);
                         // move the drone to next position
                         drone.move(nextPosition);
                           
                       // if the drone is in the reading region then remove this sensor from sensorList
                       // to avoid keep reading the same sensor and then the drone will move to next Sensor
                       //  add the sensor being read into pathFileArray instead of null
                        if (drone.currPos.inReadingRange(closestSensor)) {
                           pathFileArray = pathFileArray + closestSensor.getLocation() + "\n"; 
                           sensorList.remove(closestSensor);
                       }
                        // no sensor being read so null
                       else {
                         pathFileArray = pathFileArray + "null" + "\n";
                     }

                       
                   }
                   }
                   
                   // combining all the feature collection together to get the final feature collection
                   
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

                   // write .Gjso file
                   writeGjsonFile(result,year,month,day);
                   // write .txt file
                   writeTxtFile(pathFileArray,year,month,day);
                  
                           
                           
                   
        
}
}




