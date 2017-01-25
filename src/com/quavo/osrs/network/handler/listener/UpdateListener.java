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
package com.quavo.osrs.network.handler.listener;

import java.io.IOException;

import com.quavo.osrs.network.handler.NetworkMessageListener;
import com.quavo.osrs.network.handler.inbound.UpdateRequest;
import com.quavo.osrs.network.handler.outbound.UpdateResponse;
import com.quavo.osrs.network.protocol.cache.CacheManager;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class UpdateListener implements NetworkMessageListener<UpdateRequest> {

	@Override
	public void handleMessage(ChannelHandlerContext ctx, UpdateRequest msg) {
		int type = msg.getType();
		int id = msg.getId();
		ByteBuf container = null;

		try {
			if (type == 0xff && id == 0xff) {
				container = Unpooled.wrappedBuffer(CacheManager.getChecksumBuffer());
			} else {
				container = Unpooled.wrappedBuffer(CacheManager.getCache().getStore().read(type, id));
				if (type != 0xff) {
					container = container.slice(0, container.readableBytes() - 2);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		ctx.write(new UpdateResponse(type, id, msg.isPriority(), container));
	}

}
