/**
 * ChatServerEndPoint.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.websocket;

import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

/**
 * ChatServer
 * @author Jiji Sasidharan
 */
@ServerEndpoint(value="/chat", 
    configurator=ChatServerEndPointConfigurator.class,
    encoders={ChatMessageEncoder.class},
    decoders={ChatMessageDecoder.class}
)
public class ChatServerEndPoint {
   
    /**
     * Callback hook for Message Events.
     * 
     * This method will be invoked when a client send a message.
     * 
     * @param message The text message
     * @param userSession The session of the client
     */
    @OnMessage
    public void onMessage(ChatMessage message, Session userSession) {
        System.out.println("Message Received from " + message.getUserName());
        for (Session session : userSession.getOpenSessions()) {
            session.getAsyncRemote().sendObject(message);
        }
    }
}
