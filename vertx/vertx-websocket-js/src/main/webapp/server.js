var vertx = require('vertx');
var console = require('vertx/console');

var port = 80;

// create an HTTP server.
var server = vertx.http.createHttpServer(); 

// http handler to serve html, js, css etc.
server.requestHandler(function(req) {
    req.response.sendFile('.' + (req.path() === '/' ? '/index.html' : req.path()));
});

// websocket handler to server ws:// requests.
server.websocketHandler(function(websocket) {
    
    // accept the websocket connection only for the url '/chat'.
    if (websocket.path() != '/chat') {
        websocket.reject();
        return;
    } 
    
    console.log("Request received - " + websocket.textHandlerID());

    // Add the textHandlerID of the websocket to a shared Set. The Websocket will 
    // register itself to eventBus with textHandlerId for text messages. We will
    // use this later when we want to send messages from one websocke to another.
    var wsSessions = vertx.sharedData.getSet('websocket.chat.sessions');
    wsSessions.add(websocket.textHandlerID());
    
    // handles the message from client over websocket.
    websocket.dataHandler (function(buffer){
        console.log('Got a message - ' + buffer.toString());

        // iterate over the textHandlerIDs and publish the message to eventBus.
        wsSessions.toArray().forEach(function(textHandlerID){
            console.log('Sending to ' + textHandlerID);
            vertx.eventBus.send(textHandlerID, buffer.toString());
        });
    });
    
    // handles websocket close event.
    websocket.closeHandler (function() {
        console.log('Socket Closed. Removing ' + websocket.binaryHandlerID());
        // removes the textHandlerID from the shared set.
        wsSessions.remove(websocket.textHandlerID());
    });
});

// start the server
server.listen(port);
