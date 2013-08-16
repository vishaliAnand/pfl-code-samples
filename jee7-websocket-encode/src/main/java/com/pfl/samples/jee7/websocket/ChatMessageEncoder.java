/**
 * ChatMessageEncoder.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.websocket;

import javax.json.Json;
import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * ChatMessageEncoder
 * @author Jiji Sasidharan
 */
public class ChatMessageEncoder implements Encoder.Text<ChatMessage> {

    /* (non-Javadoc)
     * @see javax.websocket.Encoder#init(javax.websocket.EndpointConfig)
     */
    public void init(EndpointConfig config) {
        
    }

    /* (non-Javadoc)
     * @see javax.websocket.Encoder#destroy()
     */
    public void destroy() {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Encoder.Text#encode(java.lang.Object)
     */
    public String encode(ChatMessage chatMessage) throws EncodeException {
        return Json.createObjectBuilder()
                        .add("user", chatMessage.getUserName())
                        .add("message", chatMessage.getMessage())
                   .build().toString();
    }
}
