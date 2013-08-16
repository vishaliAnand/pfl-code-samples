/**
 * ChatMessage.java
 * http://programmingforliving.com
 */
package com.pfl.samples.jee7.websocket;

/**
 * ChatMessage
 * @author Jiji Sasidharan
 */
public class ChatMessage {
	
	private String userName;
	private String message;

	/**
	 * Return userName
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Set userName
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Return message
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Set message
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
}
