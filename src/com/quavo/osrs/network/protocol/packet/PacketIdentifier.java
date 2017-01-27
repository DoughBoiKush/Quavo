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
package com.quavo.osrs.network.protocol.packet;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum PacketIdentifier {

	/**
	 * The keep alive packet. This keeps the client from disconnecting.
	 */
	PING(71, 0, PacketType.FIXED),

	/** UNKNOWN PACKETS */
	P121(121, -1, PacketType.VARIABLE_BYTE),
	P62(62, 1, PacketType.FIXED),
	P148(148, 6, PacketType.FIXED);

	/**
	 * The id for each packet.
	 */
	private final int id;

	/**
	 * The size for each packet.
	 */
	private final int size;

	/**
	 * The {@link PacketType}.
	 */
	private final PacketType type;

	/**
	 * Constructs a new object.
	 * 
	 * @param id The packet id.
	 * @param size The packet size.
	 * @param type The {@link PacketType}.
	 */
	PacketIdentifier(int id, int size, PacketType type) {
		this.id = id;
		this.size = size;
		this.type = type;
	}
	
	/**
	 * Gets and returns a packet wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a packet type.
	 * @return The packet type.
	 */
	public static Optional<PacketIdentifier> getPacket(int id) {
		return Arrays.stream(PacketIdentifier.values()).filter(a -> a.id == id).findAny();
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
	 * Gets the size.
	 * 
	 * @return the size
	 */
	public int getSize() {
		return size;
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
