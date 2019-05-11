package com.jsonp2;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class App 
{
    public static void main( String[] args ) throws MalformedURLException {
        URL url = new URL("https://bpdts-test-app.herokuapp.com/users");
        try (InputStream is = url.openStream(); JsonReader reader = Json.createReader(is)){
            JsonArray results = reader.readArray();

            int count = 1;
            for (JsonObject result : results.getValuesAs(JsonObject.class)){
                if(result.get("latitude").getClass().getSimpleName().equals("JsonBigDecimalNumber")  &&
                        result.get("longitude").getClass().getSimpleName().equals("JsonBigDecimalNumber"))
                if(result.getJsonNumber("latitude").bigDecimalValue().doubleValue() < 52.205338 &&
                        result.getJsonNumber("latitude").bigDecimalValue().doubleValue() > 36.127228 &&
                        result.getJsonNumber("longitude").bigDecimalValue().doubleValue() > -1.257726 &&
                        result.getJsonNumber("longitude").bigDecimalValue().doubleValue() < 1.257726)
                System.out.println(count++ + " " + result.getString("first_name") + " " +
                        result.getString("last_name") + " " + "Latitude" + " " +
                        result.getJsonNumber("latitude").bigDecimalValue().doubleValue() + " " + "Longitude" + " " +
                        result.getJsonNumber("longitude").bigDecimalValue().doubleValue());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
