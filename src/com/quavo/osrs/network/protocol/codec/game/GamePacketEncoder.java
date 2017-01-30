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

import com.quavo.osrs.network.handler.outbound.GamePacketResponse;
import com.quavo.osrs.network.protocol.packet.PacketType;
import com.quavo.osrs.network.protocol.packet.context.PacketContext;
import com.quavo.osrs.network.protocol.packet.encode.PacketEncoder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import net.burtleburtle.bob.rand.IsaacRandom;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class GamePacketEncoder extends MessageToByteEncoder<GamePacketResponse> {

	/**
	 * The {@link IsaacRandom} used for encoding packets.
	 */
	@SuppressWarnings("unused")
	private final IsaacRandom encoder;

	/**
	 * Constructs a new object.
	 * 
	 * @param encoder The {@link IsaacRandom} for encoding packets.
	 */
	public GamePacketEncoder(IsaacRandom encoder) {
		this.encoder = encoder;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, GamePacketResponse msg, ByteBuf out) throws Exception {
		PacketEncoder<PacketContext> packet = msg.getPacket();
		PacketType type = packet.getPacket().getType();
		ByteBuf buffer = packet.getBuilder().getBuffer();

		out.writeByte(packet.getPacket().getId()/* + encoder.nextInt() */);
		if (type == PacketType.VARIABLE_BYTE) {
			out.writeByte(buffer.writerIndex());
		} else if (type == PacketType.VARIABLE_SHORT) {
			out.writeShort(buffer.writerIndex());
		}
		out.writeBytes(buffer);
	}

}
