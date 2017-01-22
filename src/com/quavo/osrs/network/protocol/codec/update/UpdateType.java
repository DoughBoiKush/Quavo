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
package com.quavo.osrs.network.protocol.codec.update;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum UpdateType {

	/**
	 * Low priority file request.
	 */
	LOW_PRIORITY_UPDATE(0),

	/**
	 * High priority file request.
	 */
	HIGH_PRIORITY_UPDATE(1),

	/**
	 * Encryption update request.
	 */
	XOR_ENCRYPTION_UPDATE(4);

	/**
	 * The id for each update type.
	 */
	private final int id;

	/**
	 * Constructs a new object.
	 * 
	 * @param id The update type id.
	 */
	UpdateType(int id) {
		this.id = id;
	}
	
	/**
	 * Gets and returns a update type wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a update type.
	 * @return The update type.
	 */
	public static Optional<UpdateType> getType(int id) {
		return Arrays.stream(UpdateType.values()).filter(a -> a.id == id).findAny();
	}

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
