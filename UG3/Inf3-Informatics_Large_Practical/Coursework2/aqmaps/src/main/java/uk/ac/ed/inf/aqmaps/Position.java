package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mapbox.geojson.Point;

public class Position {

    // position should only changeable in this class so it is public final
    public final double latitude;
    public final double longitude;

    /**
     * Initialize the Position
     * @param longitude
     * @param latitude
     */
    public Position(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    
    // 
    /**
     * 
     * @param currPosition
     * @param nextPosition
     * @param nfz_list
     * @return boolean value (if true means no intersect the boundary and the no-fly-zone
     * latitude_check and longitude_check are used to check whether the drone is in the confinement area
     * NoFlyZone.checkNoFlyZone is used to check whether the drone will intersect the no-fly-zone
     * if true then it means the drone is in within the boundary and doesn't intersect any no-fly-zone
     * 
     */
    public static boolean insideBoundary(Position currPosition,Position nextPosition,List<List<Point>> nfz_list) {
        // Big boundary
        boolean latitude_check = nextPosition.latitude > 55.942617 && nextPosition.latitude < 55.946233;
        boolean longitude_check = nextPosition.longitude > -3.192473 && nextPosition.longitude < -3.184319;
        double x1 = currPosition.latitude;
        double y1 = currPosition.longitude;
        double x2 = nextPosition.latitude;
        double y2 = nextPosition.longitude;
        boolean intersect = NoFlyZone.checkNoFlyZone(x1,y1,x2,y2,nfz_list);
        return latitude_check && longitude_check && !intersect;
    }
    
    /**
     * 
     * @param nextPosition
     * @return the distance between the drone current position and the parameter nextPosition
     * the return value is double
     * The calculation method is the Euclidean distance
     */
    public double distanceFromPosition(Position nextPosition) {
        double lat = Math.pow(this.latitude - nextPosition.latitude, 2);
        double log = Math.pow(this.longitude - nextPosition.longitude, 2);
        double result = Math.sqrt(lat + log);
        return result;
    }

    /**
     * 
     * @param sensorList
     * @return the closest sensor within the sensor list
     * Comparing each sensor and calculating the distance between the drone and its sensor.
     * Getting the shortest distance and closest sensor
     * 
     */
    public Sensor getClosestSensor(ArrayList<Sensor> sensorList) {
        Sensor closestSensor = sensorList.get(0);
        double mini_distance = distanceFromPosition(sensorList.get(0).getPosition());
        for (Sensor s : sensorList) {
            double distance = distanceFromPosition(s.getPosition());
            if (distance < mini_distance) {
                mini_distance = distance;
                closestSensor = s;
            }
        }
        return closestSensor;
    }
    
    /**
     * 
     * @param sensor
     * @return whether the drone current position is in the reading region between the drone and the sensor
     * If true then means the drone is within 0.0002 degree with the target sensor,otherwise false
     * 
     */
    public boolean inReadingRange(Sensor sensor) {

        if (distanceFromPosition(sensor.getPosition()) <= 0.0002) {
            return true;
        }
        return false;
    }
   
    
    /**
     * 
     * @param index
     * @return the related next position of the drone
     * Due to the direction index, multiply it by 10 and transfer the angle.
     * Getting the change in the latitude and longitude
     * add up together and get the next Position of the drone 
     */
    public Position nextPosition(int index) {
        // each move is 0.0003 degree
        // to avoid the angles greater than 350,which are not allowed
        if (index > 35) {
            index = index - 36;
        }
        
        double angle = Math.toRadians(index * 10);
        double latitude_change = 0.0003 * Math.sin(angle);
        double longitude_change = 0.0003 * Math.cos(angle);
     
        // Calculate the nextPos by adding the change in latitude and longitude
        // to the current position
        Position nextPos = new Position(this.longitude+longitude_change,this.latitude+latitude_change); 
        return nextPos;
    }
    
//    /**
//     * 
//     * @param startingPoint
//     * @param nfz_list
//     * @return the next Position that the drone will go
//     * This method is used to move the drone back to the starting point after visiting all the sensors
//     * 
//     */
//    public Position goBack(Position startingPoint,List<List<Point>> nfz_list) {
//        Position currPos = new Position(this.longitude,this.latitude);
//        Position nextPos = currPos.nextPosition(0);
//        int index = 0;
//        for (int i = 0; i < 36; i++) {
//            if (insideBoundary(currPos,nextPos,nfz_list)) {
//                nextPos = nextPosition(i);
//                index = i;
//                break;
//            }
//        }
//
//        double mini_Distance = nextPos.distanceFromPosition(startingPoint);
//        for (int i = 0; i < 36; i++) {
//            nextPos = currPos.nextPosition(i);
//            if (insideBoundary(currPos,nextPos,nfz_list)) {
//
//                double distance = nextPos.distanceFromPosition(startingPoint);
//                if (distance < mini_Distance) {
//                    mini_Distance = distance;
//                    index = i;
//                }
//            }
//        }
//        return currPos.nextPosition(index);
//    }
    
    /**
     * 
     * @param nfz_list (no-fly-zone, list of list of points)
     * @return ArrayList<Integer> which is the list of directions 
     * those directions will not intersect the no fly zone
     * 
     */
    public ArrayList<Integer> getGoodDirection(List<List<Point>> nfz_list) {
        ArrayList<Integer> goodDirection = new ArrayList<>();
        Position currPos = new Position(this.longitude, this.latitude);
        for (int i = 0;i<36;i++) {
            Position nextPos = currPos.nextPosition(i);
            if (Position.insideBoundary(currPos, nextPos, nfz_list)) {
                goodDirection.add(i);
            }
        }
        return goodDirection;
    }
    
    
//    /**
//     * 
//     * @param goodDirection
//     * @param closestSensor
//     * @return Position
//     * This method is take ArrayList<Interger> goodDirection, which is a list of direction that will not intersect the no-fly-zone and boundary
//     * Sensor closesSensor is the target sensor, the drone want to access and read its value
//     * It will compare which next position will be the closest one to the target sensor, and then move to this position as the next Position
//     */
//    public Position getBestNextPosition(ArrayList<Integer> goodDirection,Sensor closestSensor) {
//        int direction_size = goodDirection.size();
//        Position currPos = new Position(this.longitude, this.latitude);
//        
//        int direction = goodDirection.get(0);
//        Position nextPos = currPos.nextPosition(direction);
//        double MiniDistance = nextPos.distanceFromPosition(closestSensor.getPosition());
//        Position bestPos = nextPos;
//        
//        for (int i = 0;i<direction_size;i++) {
//            direction = i;
//            nextPos = currPos.nextPosition(direction);
//            double distance = nextPos.distanceFromPosition(closestSensor.getPosition());
//            if (distance < MiniDistance) {
//                MiniDistance = distance;
//                bestPos = nextPos;
//            }
//        }
//        return bestPos;
//    }
     
    
    
//    
//    /**
//     * 
//     * @param goodDirection
//     * @param path
//     * @return ArrayList<Integer> goodDirection
//     * This method is used to avoid the drone keep moving between two points, get stuck on there.
//     * This method will remove the direction and its related position to the path, to find out whether this Position have been visited or not.
//     * If the position have been visited, it should be removed from the goodDirection to avoid getting stuck
//     * 
//     *
//     */
//    public ArrayList<Integer> removeVisitedPath(ArrayList<Integer> goodDirection,ArrayList<Position> path){
//        Position currPos = new Position(this.longitude, this.latitude);
//        int direction_size = goodDirection.size();
//        
//        int direction = goodDirection.get(0);
//        Position nextPosition = currPos.nextPosition(direction);
//       
//        for (int i = 0;i<direction_size;i++) {
//            direction = goodDirection.get(i);
//            nextPosition = currPos.nextPosition(direction);
//            for (Position p:path) {
//                if (nextPosition.latitude == p.latitude && nextPosition.longitude == p.longitude) {
//                    goodDirection.remove(i);
//                }
//            }
//            
//        }
//        return goodDirection;
//    }
//    
    
    
    
}
