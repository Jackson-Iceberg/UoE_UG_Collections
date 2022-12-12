package uk.ac.ed.inf.heatmap;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import com.mapbox.geojson.Feature;
import com.mapbox.geojson.Point;
import com.mapbox.geojson.Polygon;
 
public class ReadFiledata {
    public static String txt2String(File file){
        StringBuilder result = new StringBuilder();
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String s = null;
            while((s = br.readLine())!=null){
                result.append(System.lineSeparator()+s);
            }
            br.close();    
        }catch(Exception e){
            e.printStackTrace();
        }
        return result.toString();
    }
    
    public static int[][] stringList2intList(String string){
        int[][] result = new int [10][10];
        string = string.replaceAll("\n", ",").replaceAll(" ", "").replaceAll("\r","");
        String[] stringList = string.split(",");
        
        for (int i = 0;i<10;i++) {
            for (int j = 0;j<10;j++) {                
                result[i][j] = Integer.valueOf((stringList[i*10+j+1])).intValue();;
            }     
        }
        return result;
    }
    
    
    public static void main(String[] args){
        File file = new File("predictions.txt");
        String string = txt2String(file);
        int [] [] list = new int [10][10];
        list = stringList2intList(string);
        
   
        for (int i = 0;i<10;i++) {
            System.out.println();
            for (int j = 0;j<10;j++) {                
                System.out.print(list[i][j]+" ");
            }     
       }
    }
}