/**
 * ChatServerEndPointConfigurator.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.websocket;

import javax.websocket.server.ServerEndpointConfig.Configurator;

/**
 * ChatServerEndPointConfigurator
 * @author Jiji Sasidharan
 */
public class ChatServerEndPointConfigurator extends Configurator {

	private ChatServerEndPoint chatServer = new ChatServerEndPoint();

	@SuppressWarnings("unchecked")
	public <T> T getEndpointInstance(Class<T> endpointClass)
			throws InstantiationException {
		return (T)chatServer;
	}
}
