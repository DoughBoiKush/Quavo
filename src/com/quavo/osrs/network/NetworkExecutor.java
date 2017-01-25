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

import com.quavo.osrs.Constants;
import com.quavo.osrs.network.protocol.codec.connection.ConnectionDecoder;
import com.quavo.osrs.network.protocol.codec.connection.ConnectionEncoder;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class NetworkExecutor {

	/**
	 * Starts the network for a {@link Server}.
	 * 
	 * @param server The {@link Server} to use for building the network.
	 * @return <True> If the network started successfully.
	 */
	public static void start() {
		EventLoopGroup boss = new NioEventLoopGroup();
		EventLoopGroup worker = new NioEventLoopGroup();
		ServerBootstrap bootstrap = new ServerBootstrap();

		bootstrap.group(boss, worker);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new ChannelInitializer<SocketChannel>() {

			@Override
			protected void initChannel(SocketChannel ch) throws Exception {
				ChannelPipeline pipeline = ch.pipeline();

				pipeline.addLast("decoder", new ConnectionDecoder());
				pipeline.addLast("encoder", new ConnectionEncoder());
				pipeline.addLast("adapter", new NetworkMessageHandler());
			}

		});
		bootstrap.childOption(ChannelOption.TCP_NODELAY, true);

		try {
			bootstrap.bind(Constants.HOST_NAME, Constants.HOST_PORT).sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("Server successfully bootstrapped on port " + Constants.HOST_PORT + " and address " + Constants.HOST_NAME + ".");
	}

}
