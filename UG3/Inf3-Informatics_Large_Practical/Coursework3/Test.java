package uk.ac.ed.inf.aqmaps;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.LineString;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;

public class Test {
    
    public static ArrayList<Sensor> TwoOptSwap(ArrayList<Sensor> sensorList,int i,int k){
        Sensor i_sensor = sensorList.get(i);
        Sensor k_sensor = sensorList.get(k);
        sensorList.set(i, k_sensor);
        sensorList.set(k, i_sensor);
        return sensorList;
    }
//  public int getBestDirection(Sensor closestSensor,List<List<Point>> nfz_list) throws IOException, InterruptedException {
//  Position currPos = new Position(this.longitude,this.latitude);
//  Position nextPos = currPos.nextPosition(0);;
//  double mini_distance = 0;
//  int index = 0;
//  for (int i = 0;i<36;i++) {
//      if (insideBoundary(currPos,nextPos,nfz_list)) {
//          nextPos = currPos.nextPosition(i);
//          mini_distance = nextPos.distanceFromPosition(closestSensor.getPosition());
//          index = i;
//          break;
//      }
//  }        
//  for (int i = 0; i < 36; i++) {
//      nextPos = currPos.nextPosition(i);
//      if (insideBoundary(currPos,nextPos,nfz_list)) {
//          double distance = nextPos.distanceFromPosition(closestSensor.getPosition()); 
//          if (distance < mini_distance) {
//              mini_distance = distance;
//              index = i;
//      }
//  }
//  }
//  return index;
//}

//public double getSensorListDistance (ArrayList<Sensor> sensorList,Drone drone) throws IOException, InterruptedException{
//  // the distance between the first sensor,the last sensor and move back to beginning point
//  double distance = drone.currPos.distanceFromPosition(sensorList.get(0).getPosition()) * 2;
//  double distance_back = sensorList.get(0).getPosition().distanceFromPosition(sensorList.get(sensorList.size()-1).getPosition());
//  double result = distance + distance_back;
//  for (int i = 0;i<sensorList.size()-1;i++) {
//      Sensor sensorA = sensorList.get(i);
//      Sensor sensorB = sensorList.get(i+1);
//      double distance_between_sensors = sensorA.getPosition().distanceFromPosition(sensorB.getPosition());
//      result += distance_between_sensors;
//  }
//  return result;
//}

//public static ArrayList<Sensor> TwoOptSwap(ArrayList<Sensor> sensorList,int i,int k){
//  Sensor i_sensor = sensorList.get(i);
//  Sensor k_sensor = sensorList.get(k);
//  sensorList.set(i, k_sensor);
//  sensorList.set(k, i_sensor);
//  return sensorList;
//}
    
    public static void main(String[] args) throws IOException, InterruptedException {
        String string = "http://localhost:80/maps" + "/" + "2020" + "/" + "01" + "/" + "03" + "/air-quality-data.json";
        String str_sensorList = JsonToJava.DeserialisingJSon(string);
        ArrayList<Sensor> sensorList = JsonToJava.getSensorList(str_sensorList);
        FeatureCollection sensorList_fc = GetFeatureCollection.getSensorListGjson(sensorList);

        String str_noFlyZone = "http://localhost:80/buildings/no-fly-zones.geojson";
        String str_noFlyZone_featureCollection = JsonToJava.DeserialisingJSon(str_noFlyZone);
        FeatureCollection noFlyZone_fc = FeatureCollection.fromJson(str_noFlyZone_featureCollection);
        
        
        ArrayList<Sensor> sensorList_back = new ArrayList<Sensor>();
        ArrayList<Double> distanceList = new ArrayList<Double>();
        
        double latitude = 55.9444;
        double longitude = -3.1878;
        Position startingPoint = new Position(longitude,latitude);
        Drone drone = new Drone(startingPoint);
//        
//        
//        for (Sensor s:sensorList) {
//            sensorList_back.add(s);
//        }
//        int improve = 0;
//        int iteration = 0;
//        double best_distance = drone.currPos.getShortestPath(sensorList, drone);
//        while ( improve < 1000 ) {
//            for ( int i = 1; i < sensorList.size() - 1; i++ ) 
//            {
//                for ( int k = i + 1; k < sensorList.size(); k++) 
//                {
//                    ArrayList<Sensor> sensorList_swap = TwoOptSwap(sensorList,i,k);
//                    double distance = drone.currPos.getShortestPath(sensorList_swap,drone);
//                    if (distance < best_distance) {
//                        sensorList = sensorList_swap;
//                        best_distance = distance;
//                        improve = 0;
//                    }
//                }
//        }
//            improve++;
//        }
//        System.out.println(best_distance);
//        System.out.println(drone.currPos.getShortestPath(sensorList_back,drone));
  
//      for (Position p : path) {
//      if (nextPos.latitude == p.latitude && nextPos.longitude == p.longitude) {
//          // randomly go out of this trap
//          for (int i = 0; i < 36; i++) {
//              index++;
//              nextPos = drone.currPos.nextPosition(index);
//              if (nextPos.insideBoundary(drone)) {
//                  break;
//              }
//          }
//      }
//  }
//      Sensor closestSensor = drone.currPos.getClosestSensor(sensorList);
//      //Sensor closestSensor = sensorList.get(0);
//      int index = drone.currPos.getBestDirection(closestSensor, drone);
//      Position nextPos = drone.currPos.nextPosition(index);
//      
//      // get ou of the trap
//      for(Position p:path) {
//          if (nextPos.latitude == p.latitude && nextPos.longitude == p.longitude) {
//              // randomly go out of this trap
//              for (int i = 0;i<36;i++) {
//                  index++;
//                  nextPos = drone.currPos.nextPosition(index);
//                  if (nextPos.insideBoundary(drone)) {
//                      break;
//                  }
//              }
//              break;
//          }
//      }
//      nextPos = drone.currPos.nextPosition(index);
//      path.add(nextPos);
//      drone.move(nextPos);
//      if (drone.currPos.inReadingRange(closestSensor)) {
//          sensorList.remove(closestSensor);
//      }
        
//      int improve = 0;
//      double best_distance = drone.currPos.getShortestPath(sensorList, drone);
//      while (improve < 1000) {
//          for (int i = 0; i < sensorList.size() - 1; i++) {
//              for (int k = i + 1; k < sensorList.size(); k++) {
//                  ArrayList<Sensor> sensorList_swap = TwoOptSwap(sensorList, i, k);
//                  double distance = drone.currPos.getShortestPath(sensorList_swap, drone);
//                  if (distance < best_distance) {
//                      sensorList = sensorList_swap;
//                      best_distance = distance;
//                      improve = 0;
//                  }
//              }
//          }
//          improve++;
//      }
//
        System.out.print(false||true);
        
    }
}
int direction_amount = goodDirection.size();
int direction = goodDirection.get(0);
Position nextPosition = currPos.nextPosition(direction);

for (int i = 0;i<positive_direction_value;i++) {
    direction = goodDirection.get(i);
    nextPosition = currPos.nextPosition(direction);
    int path_size = path.size();
    for (Position p:path) {
        if (nextPosition.latitude == p.latitude && nextPosition.longitude == p.longitude) {
            break;
        }
        else {
            path_size--;
        }
    if (path_size == 0) {
        direction = goodDirection.get(i);
        nextPosition = currPos.nextPosition(direction);
        i=positive_direction_value;
    }
}
}





double MinDistance = nextPosition.distanceFromPosition(closestSensor.getPosition());
int index = direction;
for (int i = 0;i<goodDirection.size();i++) {
    Position position = currPos.nextPosition(goodDirection.get(i));
    double distance = position.distanceFromPosition(closestSensor.getPosition());
    if (distance < MinDistance) {
        index = i;
        MinDistance = distance;
        nextPosition = position;
    }


