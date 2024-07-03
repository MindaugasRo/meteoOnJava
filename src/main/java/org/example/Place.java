package org.example;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Place {
    private static String code;
    private static String name;
    private static String administrativeDivision;
    private static String countryCode;

    public Place() {
        code = null;
        name = null;
        administrativeDivision = null;
        countryCode = null;
    }

    public static void addPlace(Place place){
       Main.places.add(place);
        updateJson();
    }

    private static void updateJson() {
        try(FileWriter writer = new FileWriter("places.json")) {
            Main.gson.toJson(Main.places,writer);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Place getPlace(){
        System.out.println("Įveskite miesto pavadinimą");
        String city = Main.sc.nextLine();
        try(FileReader reader = new FileReader("places.json")){
            // Nuskaitomas JSON failas
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                // Ištraukia laukus iš JSON objekto
                String placeName = jsonObject.get("name").getAsString();
                if(city.equals(placeName)) {
                    String code = jsonObject.get("code").getAsString();
                    String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
                    String countryCode = jsonObject.get("countryCode").getAsString();
//                // Sukuria Place objektą ir įdeda į sąrašą

                    Place place = new Place();
                    place.setCode(code);
                    place.setName(placeName);
                    place.setAdministrativeDivision(administrativeDivision);
                    place.setCountryCode(countryCode);
                    return place;
                }
            }
        }catch (Exception e){
            System.out.println(e);
            System.out.println("Kabooomm ėmė ir sulūžo!!!");
        }
        System.out.println("Įvesto miesto saraše nėra");
        Place pl = new Place();
        return pl;
    }



    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public static String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdministrativeDivision() {
        return administrativeDivision;
    }

    public void setAdministrativeDivision(String administrativeDivision) {
        this.administrativeDivision = administrativeDivision;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Place place = (Place) obj;
        return code.equals(place.code);
    }

    @Override
    public String toString() {
        return "Place{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", administrativeDivision='" + administrativeDivision + '\'' +
                ", countryCode='" + countryCode + '\'' +
                '}';
    }

}
