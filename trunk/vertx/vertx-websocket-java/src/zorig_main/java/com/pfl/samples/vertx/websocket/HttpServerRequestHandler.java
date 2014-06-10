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

import org.vertx.java.core.Handler;
import org.vertx.java.core.http.HttpServerRequest;

/**
 * HttpRequestHandler
 * @author Jiji_Sasidharan
 */
public class HttpServerRequestHandler implements Handler<HttpServerRequest> {

    @Override
    public void handle(HttpServerRequest req) {
        System.out.println("Request received for [" + req.path() + "]");
        req.response().sendFile("C:\\Dev\\pfl_code_samples\\vertx-websocket-js\\src\\main\\webapp" + ("/".equals(req.path().trim()) ? "/index.html" : req.path()));
    }
}
