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
package com.quavo.osrs.network;

import com.quavo.osrs.network.handler.NetworkMessage;
import com.quavo.osrs.network.handler.NetworkMessageListener;
import com.quavo.osrs.network.handler.NetworkMessageRepository;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class NetworkMessageHandler extends SimpleChannelInboundHandler<NetworkMessage> {

	/**
	 * Constructs a new object.
	 */
	public NetworkMessageHandler() {
		super(true);// auto release reference counts.
	}

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, NetworkMessage msg) throws Exception {
		NetworkMessageListener<NetworkMessage> listener = NetworkMessageRepository.getNetworkListener(msg);

		listener.handleMessage(ctx, msg);

		ChannelPipeline pipeline = ctx.pipeline();
		ChannelHandler handler = msg.getHandler();

		if (pipeline.context(handler) != null) {

			// flush for specific handler.
			//pipeline.context(handler).flush();
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		if (cause.getMessage().equals("An existing connection was forcibly closed by the remote host")) {
			return;
		}

		cause.printStackTrace();
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}

}
