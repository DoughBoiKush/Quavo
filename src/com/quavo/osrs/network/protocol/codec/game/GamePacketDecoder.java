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
package com.quavo.osrs.network.protocol.codec.game;

import java.util.List;
import java.util.Optional;

import com.quavo.osrs.game.model.entity.actor.player.Player;
import com.quavo.osrs.network.handler.inbound.GamePacketRequest;
import com.quavo.osrs.network.protocol.packet.GamePacketReader;
import com.quavo.osrs.network.protocol.packet.PacketType;
import com.quavo.osrs.network.protocol.packet.decode.PacketDecoderIdentifier;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.burtleburtle.bob.rand.IsaacRandom;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class GamePacketDecoder extends ByteToMessageDecoder {

	/**
	 * The player.
	 */
	private final Player player;

	/**
	 * Suppressed because isaac is impossible with no packets right now.
	 */
	@SuppressWarnings("unused")
	private final IsaacRandom decoder;

	/**
	 * Constructs a new object.
	 * 
	 * @param player The player.
	 * @param decoder The {@link IsaacRandom} decoder.
	 */
	public GamePacketDecoder(Player player, IsaacRandom decoder) {
		this.player = player;
		this.decoder = decoder;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (!in.isReadable() || !player.getChannel().isRegistered()) {
			return;
		}
		int opcode = in.readUnsignedByte();
		Optional<PacketDecoderIdentifier> data = PacketDecoderIdentifier.getPacket(opcode);
		if (data.isPresent()) {
			PacketDecoderIdentifier packet = data.get();

			int size = packet.getSize();
			if (packet.getType() == PacketType.VARIABLE_BYTE) {
				if (in.readableBytes() < 1) {
					return;
				}
				size = in.readUnsignedByte();
			} else if (packet.getType() == PacketType.VARIABLE_SHORT) {
				if (in.readableBytes() < 2) {
					return;
				}
				size = in.readUnsignedShort();
			}

			if (in.readableBytes() >= size) {
				if (size < 0) {
					return;
				}

				byte[] bytes = new byte[size];
				in.readBytes(bytes, 0, size);
				out.add(new GamePacketRequest(this, player, packet.getId(), new GamePacketReader(Unpooled.wrappedBuffer(bytes))));
			}

		} else {
			System.out.println("No data present for incoming packet: " + opcode + ".");
			in.readBytes(new byte[in.readableBytes()]);
		}
	}

}
