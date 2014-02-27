var vertx = require('vertx');
var console = require('vertx/console');

var port = 80;

var server = vertx.http.createHttpServer(); 

//http handler to serve html, js, css etc 
server.requestHandler(function(req) {
	req.response.sendFile('.' + (req.path() === '/' ? '/index.html' : req.path()));
});

//websocket handler
server.websocketHandler(function(websocket) {
	
	if (websocket.path() != '/chat') {
		websocket.reject();
		return;
	} 
	
	var wsSessions = vertx.sharedData.getSet('websocket.sessions');
	console.log("Request received - " + websocket.textHandlerID());
	wsSessions.add(websocket.textHandlerID());
	
	websocket.dataHandler (function(buffer){
		console.log('Got a message - ' + buffer.toString());
		wsSessions.toArray().forEach(function(handlerId){
			console.log('Sending to ' + handlerId);
			vertx.eventBus.send(handlerId, buffer.toString());
		});
	});
	
	websocket.closeHandler (function() {
		console.log('Socket Closed. Removing ' + websocket.binaryHandlerID());
		wsSessions.remove(websocket.textHandlerID());
	});
});
// start
server.listen(port);
