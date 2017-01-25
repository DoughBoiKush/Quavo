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
import com.quavo.osrs.network.handler.inbound.GamePacketRequest;
import com.quavo.osrs.network.protocol.packet.GamePacketReader;
import com.quavo.osrs.network.protocol.packet.PacketConstants;
import com.quavo.osrs.network.protocol.packet.PacketType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.burtleburtle.bob.rand.IsaacRandom;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class GamePacketDecoder extends ByteToMessageDecoder {

	/**
	 * The decryptor used to decode the {@link IncomingPacket}.
	 */
	private final IsaacRandom decryptor;

	private PacketState state = PacketState.READ_ID;
	private PacketType type;

	private int opcode, size;

	/**
	 * Creates a new {@link GamePacketDecoder}
	 * 
	 * @param decryptor The decrypting {@link IsaacCipher}.
	 */
	public GamePacketDecoder(IsaacRandom decryptor) {
		this.decryptor = decryptor;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf buf, List<Object> out) throws Exception {
		if (state == PacketState.READ_ID) {
			if (!buf.isReadable())
				return;

			opcode = (buf.readByte()/* - random.nextInt()*/) & 0xFF;
			size = PacketConstants.PACKET_SIZES[opcode];

			if (size == -2) {
				type = PacketType.VARIABLE_SHORT;
			} else if (size == -1) {
				type = PacketType.VARIABLE_BYTE;
			} else {
				type = PacketType.FIXED;
			}

			if (size == -3) {
				// type = PacketType.FIXED;
				// size = buf.readableBytes();
				throw new IllegalStateException("Illegal Opcode: [" + opcode + "]");
			}

			state = type != PacketType.FIXED ? PacketState.READ_SIZE : PacketState.READ_PAYLOAD;
		}

		if (state == PacketState.READ_SIZE) {
			if (!buf.isReadable())
				return;

			if (type == PacketType.VARIABLE_BYTE) {
				size = buf.readUnsignedByte();
			} else if (type == PacketType.VARIABLE_SHORT) {
				if (buf.readableBytes() >= 2) {
					size = buf.readUnsignedShort();
				}
			}

			state = PacketState.READ_PAYLOAD;
		}

		if (state == PacketState.READ_PAYLOAD) {
			if (buf.readableBytes() < size)
				return;

			ByteBuf payload = buf.readBytes(size);
			state = PacketState.READ_ID;

			out.add(new GamePacketRequest(this, opcode, new GamePacketReader(payload)));
		}
	}

}
