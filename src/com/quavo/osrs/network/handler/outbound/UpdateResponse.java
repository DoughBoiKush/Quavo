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

import io.netty.buffer.ByteBuf;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class UpdateResponse {

	/**
	 * The file type request.
	 */
	private final int type;

	/**
	 * The id of the file request.
	 */
	private final int id;

	/**
	 * The priority of the request.
	 */
	private final boolean priority;

	/**
	 * The cache container wrapped in a {@link ByteBuf}.
	 */
	private final ByteBuf container;

	/**
	 * Constructs a new object.
	 * 
	 * @param type The file type request.
	 * @param id The file id request.
	 * @param priority The priority of the request.
	 * @param container The cache container.
	 */
	public UpdateResponse(int type, int id, boolean priority, ByteBuf container) {
		this.type = type;
		this.id = id;
		this.priority = priority;
		this.container = container;
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
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the priority.
	 * 
	 * @return the priority
	 */
	public boolean isPriority() {
		return priority;
	}

	/**
	 * Gets the container.
	 * 
	 * @return the container
	 */
	public ByteBuf getContainer() {
		return container;
	}

}
