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
package com.quavo.osrs.network.protocol.codec.login;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum LoginType {

	/**
	 * A new login connection.
	 */
	NEW_CONNECTION_LOGIN(16),

	/**
	 * A reconnecting login.
	 */
	RECONNECTION_LOGIN(18);

	/**
	 * The id for each login type.
	 */
	private final int id;

	/**
	 * Constructs a new object.
	 * 
	 * @param id The id for each type.
	 */
	LoginType(int id) {
		this.id = id;
	}
	
	/**
	 * Gets and returns a connection type wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a login type.
	 * @return The login type.
	 */
	public static Optional<LoginType> getType(int id) {
		return Arrays.stream(LoginType.values()).filter(a -> a.id == id).findAny();
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
