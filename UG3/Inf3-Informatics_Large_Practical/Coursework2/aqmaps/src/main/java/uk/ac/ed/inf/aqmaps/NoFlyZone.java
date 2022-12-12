package uk.ac.ed.inf.aqmaps;

import java.util.ArrayList;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.FeatureCollection;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;

public class NoFlyZone {
    
    
    /**
     * 
     * @param takes the feature collection noFlyZone_fc as parameter
     * @return the list of list of point
     * this method is used to return the ArrayList<Point> inside each Polygon
     * there are four Polygon so it returns ArrayList<ArrayList<Point>>
     */
    protected static List<List<Point>> getNoFlyZone(FeatureCollection noFlyZone_fc){
        List<List<Point>> nfz_list = new ArrayList<>();
        List<Feature> fc_list = noFlyZone_fc.features();
        for (int i = 0;i<fc_list.size();i++) {
            Feature f = fc_list.get(i);
            Polygon polygon = (Polygon) f.geometry();
            nfz_list.add(i, polygon.coordinates().get(0));
        }
        return nfz_list;
    }
    
    
    
    
    
    /**
     * 
     * @param double x1
     * @param double y1
     * @param double x2
     * @param double y2
     * @param the list of list of point nfz_list
     * @return boolean if true means the next Position of the drone intersect the no-fly-zone
     * if false the it means it is not in the no fly zone
     * The method is based on the line2D.linesIntersect
     */
    protected static boolean checkNoFlyZone(double x1,double y1,double x2,double y2,List<List<Point>> nfz_list) {
        boolean intersect = false;
        for (int i = 0;i<nfz_list.size();i++) {
            if (!intersect) {
                for (int j = 0;j<nfz_list.get(i).size()-1;j++) {
                    if (!intersect) {
                        double x3 = nfz_list.get(i).get(j).latitude();
                        double y3 = nfz_list.get(i).get(j).longitude();
                        double x4 = nfz_list.get(i).get(j+1).latitude();
                        double y4 = nfz_list.get(i).get(j+1).longitude();
                        if (java.awt.geom.Line2D.linesIntersect(x1,y1,x2,y2,x3,y3,x4,y4)) {
                            intersect = true;
                        }
                    }
                }
            }
        }
        return intersect;
}
}
