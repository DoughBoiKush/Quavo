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
package com.quavo.osrs.network.handler.inbound;

import com.quavo.osrs.network.handler.NetworkMessage;
import com.quavo.osrs.network.protocol.codec.login.LoginType;

import io.netty.channel.ChannelHandler;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class LoginRequest extends NetworkMessage {

	/**
	 * The {@link LoginType} for a login request.
	 */
	private final LoginType type;

	/**
	 * The version of the client attempting to login.
	 */
	private final int version;

	/**
	 * Constructs a new object.
	 * 
	 * @param handler The {@link ChannelHandler} used for this request.
	 * @param type The {@link LoginType}.
	 * @param version The client version.
	 */
	public LoginRequest(ChannelHandler handler, LoginType type, int version) {
		super(handler);
		this.type = type;
		this.version = version;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public LoginType getType() {
		return type;
	}

	/**
	 * Gets the version.
	 * 
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

}
