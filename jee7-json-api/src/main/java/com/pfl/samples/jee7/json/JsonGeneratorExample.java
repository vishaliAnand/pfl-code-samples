/**
 * JsonGeneratorExample.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.json;

import java.io.StringWriter;

import javax.json.Json;
import javax.json.JsonValue;
import javax.json.stream.JsonGenerator;

/**
 * JsonGenerator Example.
 * 
 * @author Jiji_Sasidharan
 */
public class JsonGeneratorExample {

    /**
     * main
     * 
     * @param args
     */
    public static void main(String[] args) {
        example1();
        example2();
    }
    
    /**
     * example 1
     */
    private static void example1() {
        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = Json.createGenerator(writer);

        jsonGenerator
            .writeStartObject()
                .write("name", "John")
                .write("age", 35)
                .write("isMarried", true)
                .write("email", JsonValue.NULL)
                .writeStartObject("address")
                    .write("stree", "#234, Pembroke Road")
                    .write("city", "Dublin")
                    .write("zipCode", "D4")
                .writeEnd()
                .writeStartArray("phoneNumbers")
                    .write("89-923-2342")
                    .write("89-213-236")
                .writeEnd()
            .writeEnd();
        jsonGenerator.close();
        System.out.println(writer.toString());
    }

    /**
     * example 2
     */
    private static void example2() {
        StringWriter writer = new StringWriter();
        JsonGenerator jsonGenerator = Json.createGenerator(writer);

        jsonGenerator.writeStartArray();
        for (int i = 0; i < 5; i++) {
            jsonGenerator
                .writeStartObject()
                    .write("name", "John - " + i)
                .writeEnd();
            jsonGenerator.flush();
            System.out.println("Iteration " + i + ": " + writer.toString());
        }
        jsonGenerator.writeEnd();
        jsonGenerator.close();
        
        System.out.println(writer.toString());
    }
}
