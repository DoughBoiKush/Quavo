/**
 * MIT License
 *
 * Copyright (c) 2017 Quavo
 * Copyright (c) 2017 Jordan Abraham
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.quavo.osrs.network.handler.outbound;

import com.quavo.osrs.network.protocol.ClientMessage;
import com.quavo.osrs.network.protocol.codec.login.LoginType;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class LoginResponse {

	/**
	 * The {@link ClientMessage} to send back to the client.
	 */
	private final ClientMessage message;

	/**
	 * The {@link LoginType} used for this response.
	 */
	private final LoginType type;

	/**
	 * Constructs a new object.
	 * 
	 * @param message The {@link ClientMessage} to send back to the client.
	 * @param type The {@link LoginType}.
	 */
	public LoginResponse(ClientMessage message, LoginType type) {
		this.message = message;
		this.type = type;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public ClientMessage getMessage() {
		return message;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public LoginType getType() {
		return type;
	}

}
