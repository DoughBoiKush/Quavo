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
package com.quavo.osrs.network.protocol.packet.encode;

import com.quavo.osrs.network.protocol.packet.PacketType;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum PacketEncoderIdentifier {

	GAME_PANEL(92, PacketType.FIXED),
	PING(195, PacketType.FIXED),
	STATIC_REGION(17, PacketType.VARIABLE_SHORT),
	INTERFACE_SET(80, PacketType.FIXED),
	INTERFACE(82, PacketType.FIXED),
	MESSAGE(32, PacketType.VARIABLE_BYTE),
	FIXED_VARP(159, PacketType.FIXED),
	STATIC_VARP(241, PacketType.FIXED),
	VARP_RESET(11, PacketType.FIXED);

	/**
	 * The packet id.
	 */
	private final int id;

	/**
	 * The {@link PacketType}.
	 */
	private final PacketType type;

	/**
	 * Constructs a new object.
	 * 
	 * @param id The packet id.
	 * @param type The {@link PacketType}.
	 */
	PacketEncoderIdentifier(int id, PacketType type) {
		this.id = id;
		this.type = type;
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
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public PacketType getType() {
		return type;
	}

}
