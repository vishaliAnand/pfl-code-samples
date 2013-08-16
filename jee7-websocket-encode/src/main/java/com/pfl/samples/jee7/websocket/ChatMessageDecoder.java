/**
 * ChatMessageDecoder.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.websocket;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

/**
 * ChatMessageDecoder
 * @author Jiji Sasidharan
 */
public class ChatMessageDecoder implements Decoder.Text<ChatMessage> {

    /* (non-Javadoc)
     * @see javax.websocket.Decoder#init(javax.websocket.EndpointConfig)
     */
    public void init(EndpointConfig config) {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder#destroy()
     */
    public void destroy() {
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder.Text#decode(java.lang.String)
     */
    public ChatMessage decode(String s) throws DecodeException {
        JsonObject jsonObject = Json.createReader(new StringReader(s)).readObject();
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUserName(jsonObject.getString("user"));
        chatMessage.setMessage(jsonObject.getString("message"));
        return chatMessage;
    }

    /* (non-Javadoc)
     * @see javax.websocket.Decoder.Text#willDecode(java.lang.String)
     */
    public boolean willDecode(String s) {
        return true;
    }
}
