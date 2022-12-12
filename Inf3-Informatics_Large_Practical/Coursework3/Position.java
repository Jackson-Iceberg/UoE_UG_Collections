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

    public Position(double longitude, double latitude) {
        this.longitude = longitude;
        this.latitude = latitude;
    }
    
    // Checks whether the drone is in the confinement area
    // Checks whether the drone is in the no-fly-zone
    // if true then it means the drone is in within the boundary
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
    
    // This method used to calculate the distance between the current Position and nextPosition
    public double distanceFromPosition(Position nextPosition) {
        double lat = Math.pow(this.latitude - nextPosition.latitude, 2);
        double log = Math.pow(this.longitude - nextPosition.longitude, 2);
        double result = Math.sqrt(lat + log);
        return result;
    }

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

    public boolean inReadingRange(Sensor sensor) {

        if (distanceFromPosition(sensor.getPosition()) <= 0.0002) {
            return true;
        }
        return false;
    }

    public Position goBack(Position startingPoint,List<List<Point>> nfz_list) {
        Position currPos = new Position(this.longitude,this.latitude);
        Position nextPos = currPos.nextPosition(0);
        int index = 0;
        for (int i = 0; i < 36; i++) {
            if (insideBoundary(currPos,nextPos,nfz_list)) {
                nextPos = nextPosition(i);
                index = i;
                break;
            }
        }

        double mini_Distance = nextPos.distanceFromPosition(startingPoint);
        for (int i = 0; i < 36; i++) {
            nextPos = currPos.nextPosition(i);
            if (insideBoundary(currPos,nextPos,nfz_list)) {

                double distance = nextPos.distanceFromPosition(startingPoint);
                if (distance < mini_Distance) {
                    mini_Distance = distance;
                    index = i;
                }
            }
        }
        return currPos.nextPosition(index);
    }

    // Move the drone into next Position
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
    public Position getBestNextPosition(ArrayList<Integer> goodDirection,Sensor closestSensor) {
        int direction_size = goodDirection.size();
        Position currPos = new Position(this.longitude, this.latitude);
        
        int direction = goodDirection.get(0);
        Position nextPos = currPos.nextPosition(direction);
        double MiniDistance = nextPos.distanceFromPosition(closestSensor.getPosition());
        Position bestPos = nextPos;
        
        for (int i = 0;i<direction_size;i++) {
            direction = i;
            nextPos = currPos.nextPosition(direction);
            double distance = nextPos.distanceFromPosition(closestSensor.getPosition());
            if (distance < MiniDistance) {
                MiniDistance = distance;
                bestPos = nextPos;
            }
        }
        return bestPos;
    }
     
    
    
    
    
    public ArrayList<Integer> removeVisitedPath(ArrayList<Integer> goodDirection,ArrayList<Position> path){
        Position currPos = new Position(this.longitude, this.latitude);
        int direction_size = goodDirection.size();
        
        int direction = goodDirection.get(0);
        Position nextPosition = currPos.nextPosition(direction);
       
        for (int i = 0;i<direction_size;i++) {
            direction = goodDirection.get(i);
            nextPosition = currPos.nextPosition(direction);
            for (Position p:path) {
                if (nextPosition.latitude == p.latitude && nextPosition.longitude == p.longitude) {
                    goodDirection.remove(i);
                }
            }
            
        }
        return goodDirection;
    }
    
    
    
    
}
