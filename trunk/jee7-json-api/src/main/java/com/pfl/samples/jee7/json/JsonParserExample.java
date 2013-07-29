/**
 * JsonParserExample.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.json;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonParser;
import javax.json.stream.JsonParser.Event;

/**
 * JsonParser Example
 * 
 * @author Jiji_Sasidharan
 */
public class JsonParserExample {

    /**
     * main 
     */
    public static void main(String[] args) {
        String personJSONData = 
                "  {" +
                "       \"name\": \"John\", " +
                "       \"age\" : 35, " +
                "       \"isMarried\" : true, " +
                "       \"email\": null, " +
                "       \"address\": { " +
                "           \"street\": \"#234, Pembroke Road\", " +
                "           \"city\": \"Dublin\", " +
                "           \"zipCode\": \"D4\" " +
                "       }, " +
                "       \"phoneNumbers\": [\"89-923-2342\", \"89-213-2364\"] " +
                "   }";
        
        JsonParser parser = Json.createParser(new StringReader(personJSONData));
        Object jsonObject = parseJson(parser, null);
        System.out.println("Json Object: " + jsonObject);
    }
    
    /**
     * Parse Json to a Map<String, Object> (Object) or Array<Object> (Arrays)
     * 
     * @param parser
     * @param jsonObject
     * @return
     */
    private static Object parseJson(JsonParser parser, Object jsonObject) {
        String keyName = null;
        while (parser.hasNext()) {
            Event event = parser.next();
            switch (event) {
                case START_OBJECT:
                case START_ARRAY:
                    Object newJsonObj = (event == Event.START_OBJECT ? new LinkedHashMap<String, Object>() : new ArrayList<Object>());
                    if (jsonObject == null) {
                        jsonObject = parseJson(parser, newJsonObj);
                    } else {
                        add(jsonObject, keyName, parseJson(parser, newJsonObj));
                    }
                    break;
                case KEY_NAME:
                    keyName = parser.getString();
                    break;
                case END_ARRAY:
                case END_OBJECT:
                    return jsonObject;
                case VALUE_NULL:
                case VALUE_NUMBER:
                case VALUE_STRING:
                case VALUE_TRUE:
                case VALUE_FALSE:
                    add(jsonObject, keyName, getData(event, parser));
                    break;
                default:
                    System.out.println("Unrecognized event: " + event);
            }
        }
        return jsonObject;
    }

    /**
     * @param jsonObject
     * @param keyName
     * @param parseJson1
     */
    @SuppressWarnings("unchecked")
    private static void add(Object jsonObject, String keyName, Object jsonValue) {
        if (jsonObject instanceof Map) {
            ((Map<String, Object>)jsonObject).put(keyName, jsonValue);
        } else {
            ((List<Object>)jsonObject).add(jsonValue);
        }
    }

    /**
     * Return data value.
     * 
     * @param event
     * @param parser
     * @return
     */
    private static Object getData(Event event, JsonParser parser) {
        switch(event) {
            case VALUE_NUMBER:
                return parser.getLong();
            case VALUE_STRING:
                return parser.getString();
            case VALUE_TRUE:
            case VALUE_FALSE:
                return (event == Event.VALUE_TRUE);
            case VALUE_NULL:
                return null;
            default:
                System.out.println("Unrecognized Event : " + event);
                return null;
        }
    }

}
