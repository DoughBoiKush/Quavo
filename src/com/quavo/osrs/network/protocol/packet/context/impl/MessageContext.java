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
package com.quavo.osrs.network.protocol.packet.context.impl;

import com.quavo.osrs.network.protocol.packet.context.PacketContext;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class MessageContext implements PacketContext {

	/**
	 * The type of message being sent.
	 */
	private final int type;

	/**
	 * The message.
	 */
	private final String message;

	/**
	 * If the message is filtered.
	 */
	private final boolean filter;

	/**
	 * Constructs a new object.
	 * 
	 * @param type The message type.
	 * @param message The message.
	 */
	public MessageContext(int type, String message) {
		this(type, message, false);
	}

	/**
	 * Constructs a new object.
	 * 
	 * @param type The message type.
	 * @param message The message.
	 * @param filter If the message is filtered.
	 */
	public MessageContext(int type, String message, boolean filter) {
		this.type = type;
		this.message = message;
		this.filter = filter;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * Gets the message.
	 * 
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * Gets the filter.
	 * 
	 * @return the filter
	 */
	public boolean isFilter() {
		return filter;
	}

}
