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
package com.quavo.osrs.network.protocol.codec.connection;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum ConnectionType {

	/**
	 * Represents the login connection.
	 */
	LOGIN_CONNECTION(14),

	/**
	 * Represents the handshake connection.
	 */
	HANDSHAKE_CONNECTION(15);

	/**
	 * The protocol id for each connection type.
	 */
	private final int id;

	/**
	 * Constructs a new object.
	 * 
	 * @param id The connection id value for each type.
	 */
	ConnectionType(int id) {
		this.id = id;
	}

	/**
	 * Gets and returns a connection type wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a connection type.
	 * @return The connection type.
	 */
	public static Optional<ConnectionType> getType(int id) {
		return Arrays.stream(ConnectionType.values()).filter(a -> a.id == id).findAny();
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
