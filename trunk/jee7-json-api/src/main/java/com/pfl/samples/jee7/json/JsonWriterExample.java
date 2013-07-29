/**
 * JsonWriterExample.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.json;

import java.io.StringWriter;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonWriter;

/**
 * JsonWriter Example
 * 
 * @author Jiji_Sasidharan
 */
public class JsonWriterExample {
    
    public static void main(String[] args) {
        JsonObjectBuilder personObjectBuilder = Json.createObjectBuilder();
        JsonObject personObject = personObjectBuilder
                .add("name", "John")
                .add("age", 35)
                .add("isMarried", true)
                .add("address", 
                     Json.createObjectBuilder().add("street", "somestreet")
                                               .add("city", "somecity")
                                               .add("zipCode", "11111")
                                               .build()
                    )
                .add("phoneNumber", 
                     Json.createArrayBuilder().add("00-000-0000")
                                              .add("11-111-1111")
                                              .build()
                    )
                .build();
        
        StringWriter stringWriter = new StringWriter();
        
        JsonWriter writer = Json.createWriter(stringWriter);
        writer.writeObject(personObject);
        writer.close();
        
        System.out.println("Object: " + stringWriter.getBuffer().toString());
    }

}
