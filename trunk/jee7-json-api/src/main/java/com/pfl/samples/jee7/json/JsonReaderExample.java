/**
 * JsonReaderExample.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.json;

import java.io.StringReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

/**
 * JsonReader Example program
 * @author Jiji_Sasidharan
 */
public class JsonReaderExample {
    
    private static void withObject() {
        String personJSONData = 
                "  {" +
                "       \"name\": \"John\", " +
                "       \"age\" : 35, " +
                "       \"isMarried\" : true, " +
                "       \"address\": { " +
                "           \"street\": \"#234, Pembroke Road\", " +
                "           \"city\": \"Dublin\", " +
                "           \"zipCode\": \"D4\" " +
                "       }, " +
                "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " +
                "   }";
            
            JsonReader reader = Json.createReader(new StringReader(personJSONData));
            JsonObject personObject = reader.readObject();
            reader.close();
            
            System.out.println("Name   : " + personObject.getString("name"));
            System.out.println("Age    : " + personObject.getInt("age"));
            System.out.println("Married: " + personObject.getBoolean("isMarried"));
            
            JsonObject addressObject = personObject.getJsonObject("address");
            System.out.println("Address: ");
            System.out.println("\t" + addressObject.getString("street"));
            System.out.println("\t" + addressObject.getString("city"));
            System.out.println("\t" + addressObject.getString("zipCode"));
            
            System.out.println("Phone  : ");
            JsonArray phoneNumbersArray = personObject.getJsonArray("phoneNumbers");
            for (JsonValue jsonValue : phoneNumbersArray) {
                System.out.println("\t" + jsonValue.toString());
            }
    }
    
    private static void withArray() {
        String personJSONData = 
                "[ " +
                "   {" +
                "       \"name\": \"John\", " +
                "       \"age\" : 35, " +
                "       \"isMarried\" : true, " +
                "       \"address\": { " +
                "           \"street\": \"#234, Pembroke Road\", " +
                "           \"city\": \"Dublin\", " +
                "           \"zipCode\": \"D4\" " +
                "       }, " +
                "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " +
                "   }," +
                "   {" +
                "       \"name\": \"Mary\", " +
                "       \"age\" : 35, " +
                "       \"isMarried\" : true, " +
                "       \"address\": { " +
                "           \"street\": \"#234, Pembroke Road\", " +
                "           \"city\": \"Dublin\", " +
                "           \"zipCode\": \"D4\" " +
                "       }, " +
                "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " +
                "   }" +
                "]";
        JsonReader reader = Json.createReader(new StringReader(personJSONData));
        JsonArray personObjectArray = reader.readArray();
        reader.close();
        
        for (JsonValue personObj : personObjectArray) {
            System.out.println(personObj.getValueType() + " - " + ((JsonObject) personObj).getString("name"));
        }
    }
    
    public static void main(String[] args) {
        withObject();
        withArray();
    }
}
