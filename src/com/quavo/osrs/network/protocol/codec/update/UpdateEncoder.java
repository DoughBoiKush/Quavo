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
package com.quavo.osrs.network.protocol.codec.update;

import com.quavo.osrs.network.handler.outbound.UpdateResponse;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class UpdateEncoder extends MessageToByteEncoder<UpdateResponse> {

	/**
	 * Constructs a new object.
	 */
	public UpdateEncoder() {
		super(UpdateResponse.class);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, UpdateResponse msg, ByteBuf out) throws Exception {
		int type = msg.getType();
		int id = msg.getId();
		ByteBuf container = msg.getContainer();

		int compression = container.readUnsignedByte();
		int length = container.readInt();

		out.writeByte(type);
		out.writeShort(id);
		out.writeByte(compression);
		out.writeInt(length);

		int bytes = container.readableBytes();
		if (bytes > 504) {
			bytes = 504;
		}
		out.writeBytes(container.readBytes(bytes));
		while ((bytes = container.readableBytes()) != 0) {
			if (bytes == 0) {
				break;
			} else if (bytes > 511) {
				bytes = 511;
			}
			out.writeByte(0xff);
			out.writeBytes(container.readBytes(bytes));
		}
	}

}
