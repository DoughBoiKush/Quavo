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

import com.quavo.osrs.game.model.entity.actor.player.Player;
import com.quavo.osrs.network.handler.NetworkMessage;
import com.quavo.osrs.network.protocol.packet.GamePacketReader;

import io.netty.channel.ChannelHandler;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class GamePacketRequest extends NetworkMessage {

	/**
	 * The {@link Player}.
	 */
	private final Player player;

	/**
	 * The packet id.
	 */
	private final int id;

	/**
	 * The {@link GamePacketReader}.
	 */
	private final GamePacketReader reader;

	/**
	 * Constructs a new object.
	 * 
	 * @param handler The {@link ChannelHandler} used for this request.
	 * @param player The player.
	 * @param id The packet id.
	 * @param size The packet size.
	 * @param reader The {@link GamePacketReader}.
	 */
	public GamePacketRequest(ChannelHandler handler, Player player, int id, GamePacketReader reader) {
		super(handler);
		this.player = player;
		this.id = id;
		this.reader = reader;
	}

	/**
	 * Gets the player.
	 * 
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
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
	 * Gets the reader.
	 * 
	 * @return the reader
	 */
	public GamePacketReader getReader() {
		return reader;
	}

}
