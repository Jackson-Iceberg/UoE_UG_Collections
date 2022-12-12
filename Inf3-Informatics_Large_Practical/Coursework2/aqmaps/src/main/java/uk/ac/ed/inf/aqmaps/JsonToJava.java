package uk.ac.ed.inf.aqmaps;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonToJava {
    
    /**
     * 
     * @param  DeserialisingJSon sensorList String
     * @return ArrayList<Sensor>
     */
    public static ArrayList<Sensor> getSensorList(String str) {

        Type listType = new TypeToken<ArrayList<Sensor>>() {
        }.getType();
        // Use the ”fromJson(String, Type)” method
        ArrayList<Sensor> sensorList = new Gson().fromJson(str, listType);
        return sensorList;

    }
    
    
    /**
     * 
     * @param String location
     * @return the w3words of the sensor
     */
    public static w3words getW3Words(String str) {
        Type listType = new TypeToken<w3words>() {
        }.getType();
        w3words details = new Gson().fromJson(str, listType);
        return details;
    }

    /**
     * 
     * @param str
     * @return
     * @throws IOException
     * @throws InterruptedException
     * This method is used to convert Gjson file into Java file by reading .gJson From url
     * It can be used to convert like no-fly-zone, maps, w3word details and so on.
     */
    public static String DeserialisingJSon(String str) throws IOException, InterruptedException {
        String urlString = str;
        // Create a new HttpClient with default settings.
        var client = HttpClient.newHttpClient();
        // HttpClient assumes that it is a GET request by default.
        var request = HttpRequest.newBuilder().uri(URI.create(urlString)).build();
        // The response object is of class HttpResponse<String>
        var response = client.send(request, BodyHandlers.ofString());
        String result = response.body();
        return result;
    }
}
