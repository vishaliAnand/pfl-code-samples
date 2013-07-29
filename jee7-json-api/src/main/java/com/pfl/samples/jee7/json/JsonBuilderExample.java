/**
 * JsonBuilderExample.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.json;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;

/**
 * JsonBuilder example.
 * 
 * @author Jiji_Sasidharan
 */
public class JsonBuilderExample {
    
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
        
        System.out.println("Object: " + personObject);
    }
}
