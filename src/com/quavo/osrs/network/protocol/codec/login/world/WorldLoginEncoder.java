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
package com.quavo.osrs.network.protocol.codec.login.world;

import com.quavo.osrs.network.handler.outbound.WorldLoginResponse;
import com.quavo.osrs.network.protocol.ClientMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class WorldLoginEncoder extends MessageToByteEncoder<WorldLoginResponse> {

	/**
	 * Constructs a new object.
	 */
	public WorldLoginEncoder() {
		super(WorldLoginResponse.class);
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, WorldLoginResponse msg, ByteBuf out) throws Exception {
		ClientMessage message = msg.getMessage();

		out.writeByte(message.getId());
		if (message == ClientMessage.SUCCESSFUL) {
			out.writeByte(0);
			out.writeByte(0);
			out.writeByte(0);
			out.writeByte(0);
			out.writeByte(0);
			out.writeByte(2);// rights
			out.writeByte(0);
			out.writeShort(1);// index
			out.writeByte(1);
		}

		ctx.pipeline().remove(this);
	}

}
