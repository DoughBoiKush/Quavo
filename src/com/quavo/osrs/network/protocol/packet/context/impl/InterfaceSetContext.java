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
public final class InterfaceSetContext implements PacketContext {

	/**
	 * The from interface.
	 */
	private final int fromInterfaceId;

	/**
	 * The from child.
	 */
	private final int fromChildId;

	/**
	 * The to interface.
	 */
	private final int toInterfaceId;

	/**
	 * The to child.
	 */
	private final int toChildId;

	/**
	 * Constructs a new object.
	 * 
	 * @param fromInterfaceId The starting interface id.
	 * @param fromChildId The starting child id.
	 * @param toInterfaceId The finishing interface id.
	 * @param toChildId The finishing interface id.
	 */
	public InterfaceSetContext(int fromInterfaceId, int fromChildId, int toInterfaceId, int toChildId) {
		this.fromInterfaceId = fromInterfaceId;
		this.fromChildId = fromChildId;
		this.toInterfaceId = toInterfaceId;
		this.toChildId = toChildId;
	}

	/**
	 * Gets the fromInterfaceId.
	 * 
	 * @return the fromInterfaceId
	 */
	public int getFromInterfaceId() {
		return fromInterfaceId;
	}

	/**
	 * Gets the fromChildId.
	 * 
	 * @return the fromChildId
	 */
	public int getFromChildId() {
		return fromChildId;
	}

	/**
	 * Gets the toInterfaceId.
	 * 
	 * @return the toInterfaceId
	 */
	public int getToInterfaceId() {
		return toInterfaceId;
	}

	/**
	 * Gets the toChildId.
	 * 
	 * @return the toChildId
	 */
	public int getToChildId() {
		return toChildId;
	}

}
