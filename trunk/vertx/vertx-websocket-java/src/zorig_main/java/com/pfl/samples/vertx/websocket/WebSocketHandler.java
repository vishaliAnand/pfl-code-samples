/*
 * vertx-websocket-java
 * Copyright (C) 2013 Jiji Sasidharan
 * http://programmingforliving.com/
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 3
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */

package com.pfl.samples.vertx.websocket;

import java.util.Set;

import org.vertx.java.core.Handler;
import org.vertx.java.core.Vertx;
import org.vertx.java.core.buffer.Buffer;
import org.vertx.java.core.http.ServerWebSocket;

/**
 * WebSocketHandler
 * @author Jiji_Sasidharan
 *
 */
public class WebSocketHandler implements Handler<ServerWebSocket> {

    private Vertx vertx;
    
    public WebSocketHandler(Vertx vertx) {
        this.vertx = vertx;
    }

    @Override
    public void handle(final ServerWebSocket webSocket) {
        System.out.println("Websocket Connect for [" + webSocket.path() + "]");
        if (!"/chat".equals(webSocket.path())) {
            webSocket.reject();
        }
        
        final Set<String> userRegister = vertx.sharedData().getSet("websocket.chat.sessions");
        userRegister.add(webSocket.textHandlerID());
        System.out.println("User registration done for handler " + webSocket.textHandlerID());

        webSocket.dataHandler(new Handler<Buffer>() {
            @Override
            public void handle(Buffer buffer) {
                System.out.println("Message --> " + buffer.toString());
                for (String textHandlerID : userRegister) {
                    System.out.println("Sending to " + textHandlerID);
                    vertx.eventBus().send(textHandlerID, buffer.toString());
                }
            }
        });
        
        webSocket.closeHandler(new Handler<Void>() {
            @Override
            public void handle(Void arg0) {
                userRegister.remove(webSocket.textHandlerID());
                System.out.println("Removed user handler " + webSocket.textHandlerID());
            }
        });
    }
}
