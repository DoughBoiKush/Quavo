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

import io.netty.channel.ChannelHandler;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class XOREncryptionRequest extends NetworkMessage {

	/**
	 * The encrypted key for an update request.
	 */
	private final int key;

	/**
	 * Constructs a new object.
	 * 
	 * @param handler The {@link ChannelHandler} used for this request.
	 * @param key The encryption key.
	 */
	public XOREncryptionRequest(ChannelHandler handler, int key) {
		super(handler);
		this.key = key;
	}

	/**
	 * Gets the key.
	 * 
	 * @return the key
	 */
	public int getKey() {
		return key;
	}

}
