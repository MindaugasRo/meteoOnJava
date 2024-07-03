package org.example;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class GetApiData {
    public static String callApi() throws IOException {
        String url = "https://api.meteo.lt/v1/places";
        URL apiUrl = new URL(url);
        BufferedReader reader = new BufferedReader(new InputStreamReader(apiUrl.openStream()));
        StringBuilder response = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();
        return response.toString();
    }
    //        String response = GetApiData.callApi();
    //        System.out.println(response);

}

