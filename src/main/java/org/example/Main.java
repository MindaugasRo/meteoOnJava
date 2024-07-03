package org.example;

import com.google.gson.*;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static List<Place> places;
    public static Scanner sc;
    public static Gson gson;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        gson = new Gson();
        places = getPlaces();

        while (true) {
            menuOptionsMessage();
            int item = numberInput(sc);
            sc.nextLine();

            switch (item) {
                case 1:
                    Place.addPlace(new Place());
                    break;
                case 2:
                    System.out.println(Place.getPlace());
                    break;
                case 3:

                    break;
                case 4:

                    break;
                case 5:
                    programExit();
                default:
                    System.out.println("Įveskite skaitmenį iš patekto meniu");


            }

        }
    }

    private static List<Place> getPlaces() {
        List<Place> places = new ArrayList<>();
        try(FileReader reader = new FileReader("places.json")){
            JsonElement jsonElement = JsonParser.parseReader(reader);
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject jsonObject = element.getAsJsonObject();
                String code = jsonObject.get("code").getAsString();
                String name = jsonObject.get("name").getAsString();
                String administrativeDivision = jsonObject.get("administrativeDivision").getAsString();
                String countryCode = jsonObject.get("countryCode").getAsString();
                //Sukuriams Place objektas ir pridedamas į sąrašą
                Place place = new Place();
                place.setCode(code);
                place.setName(name);
                place.setAdministrativeDivision(administrativeDivision);
                place.setCountryCode(countryCode);

            }
        }catch (Exception e){
            System.out.println(e);
        }
        return places;
    }

    public static void menuOptionsMessage() {
        System.out.println();
        System.out.println("---------------");
        System.out.println(" 1 - Įvesti naują vietą");
        System.out.println(" 2 - Rodyti vietų sąrašą");
        System.out.println(" 3 - Redaguoti sąrašą");
        System.out.println(" 4 - Ištrinti vietą");
        System.out.println(" 5 - Stabdyti programą");
        System.out.println("---------------");
    }

    public static int numberInput(Scanner sc) {
        while (true) {
            try {
                return sc.nextInt();
            } catch (Exception e) {
                System.out.println("Įveskite savo pasirinkimą skaitmenimis");
                sc.nextLine();
            }
        }
    }

    public static void programExit () {
        System.out.println("Programa uždaryta");
        System.exit(1);
    }


}