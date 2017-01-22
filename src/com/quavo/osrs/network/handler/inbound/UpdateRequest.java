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
public final class UpdateRequest extends NetworkMessage {

	/**
	 * The type request.
	 */
	private final int type;

	/**
	 * The file request.
	 */
	private final int file;

	/**
	 * The priority of the request.
	 */
	private final boolean priority;

	/**
	 * Constructs a new object.
	 * 
	 * @param handler The {@link ChannelHandler} used for this request.
	 * @param type The type request.
	 * @param file The file request.
	 * @param priority The priority of the request.
	 */
	public UpdateRequest(ChannelHandler handler, int type, int file, boolean priority) {
		super(handler);
		this.type = type;
		this.file = file;
		this.priority = priority;
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
	 * Gets the file.
	 * 
	 * @return the file
	 */
	public int getFile() {
		return file;
	}

	/**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	public boolean isPriority() {
		return priority;
	}

}
