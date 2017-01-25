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

import com.quavo.osrs.game.node.actor.player.Player;
import com.quavo.osrs.network.protocol.packet.GamePacketBuilder;
import com.quavo.osrs.network.protocol.packet.PacketType;
import com.quavo.osrs.network.protocol.packet.context.PacketContext;

import io.netty.buffer.Unpooled;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public abstract class PacketEncoder<T extends PacketContext> {

	/**
	 * Represents the packet id.
	 */
	protected final int id;

	/**
	 * Represents the packet type.
	 */
	protected final PacketType packetType;

	/**
	 * Represents the buffer for building the packet.
	 */
	protected final GamePacketBuilder builder;

	/**
	 * Constructs a new object.
	 *
	 * @param id The packet id.
	 * @param packetType The packet type.
	 */
	public PacketEncoder(int id, PacketType packetType) {
		this.id = id;
		this.packetType = packetType;
		this.builder = new GamePacketBuilder(Unpooled.buffer());
	}

	/**
	 * Constructs a new object.
	 * 
	 * @param id The packet id.
	 * @param size The packet size.
	 * @param packetType The packet type.
	 */
	public PacketEncoder(int id, int size, PacketType packetType) {
		this.id = id;
		this.packetType = packetType;
		this.builder = new GamePacketBuilder(Unpooled.buffer(size));
	}

	/**
	 * Writes a packet.
	 *
	 * @param player The player.
	 * @param context The packet wrapper.
	 */
	public abstract void encode(Player player, T context);

	/**
	 * Gets the id.
	 * 
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * Gets the packetType.
	 * 
	 * @return the packetType
	 */
	public PacketType getPacketType() {
		return packetType;
	}

	/**
	 * Gets the builder.
	 * 
	 * @return the builder
	 */
	public GamePacketBuilder getBuilder() {
		return builder;
	}

}
