package main;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import model.Rating;
import model.Root;

import java.io.File;

public class Main {

    public static void main(String args[]) {

        try {

            Rating rating = new Rating("urn:www.citc.gov.sa","15");
            ObjectMapper om = new ObjectMapper();
            om.enable(SerializationFeature.WRAP_ROOT_VALUE);
            String jsonString = om.writeValueAsString(rating);
            System.out.println("-- after serialization --");
            om.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
            Rating ratingDes = om.readValue(jsonString, Rating.class);
            System.out.println(ratingDes);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
