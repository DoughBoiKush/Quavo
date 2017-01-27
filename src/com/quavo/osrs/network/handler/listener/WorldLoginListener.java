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

import com.quavo.osrs.game.model.entity.actor.player.Player;
import com.quavo.osrs.network.handler.NetworkMessageListener;
import com.quavo.osrs.network.handler.inbound.WorldLoginRequest;
import com.quavo.osrs.network.handler.outbound.WorldLoginResponse;
import com.quavo.osrs.network.protocol.ClientMessage;
import com.quavo.osrs.network.protocol.cache.CacheManager;
import com.quavo.osrs.network.protocol.codec.game.GamePacketDecoder;
import com.quavo.osrs.network.protocol.codec.game.GamePacketEncoder;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPipeline;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class WorldLoginListener implements NetworkMessageListener<WorldLoginRequest> {

	@Override
	public void handleMessage(ChannelHandlerContext ctx, WorldLoginRequest msg) {
		ClientMessage message = evaluateLogin(msg);
		if (message != ClientMessage.SUCCESSFUL) {
			ctx.write(new WorldLoginResponse(message));
			return;
		}

		Player player = new Player(ctx.channel());
		ctx.write(new WorldLoginResponse(player, message, msg.getIsaacPair()));
		

		ChannelPipeline pipeline = ctx.pipeline();
		pipeline.remove("login.encoder");
		
		// this isnt set automatically.
		pipeline.addBefore("adapter", "game.encoder", new GamePacketEncoder(msg.getIsaacPair().getEncoderRandom()));
		pipeline.replace("world.decoder", "game.decoder", new GamePacketDecoder(player, msg.getIsaacPair().getDecoderRandom()));
		
		player.init();
	}

	/**
	 * Checks if the user is able to login into the game.
	 * 
	 * @param msg The {@link WorldLoginRequest}.
	 * @return <true> if the user is able to login.
	 */
	private ClientMessage evaluateLogin(WorldLoginRequest msg) {
		ClientMessage message = ClientMessage.SUCCESSFUL;

		if (!msg.getToken().equals("ElZAIrq5NpKN6D3mDdihco3oPeYN2KFy2DCquj7JMmECPmLrDP3Bnw")) {
			message = ClientMessage.OUT_OF_DATE;
		}

		for (int index = 0; index < msg.getCRC().length; index++) {
			if (CacheManager.getChecksumTable().getEntry(index).getCrc() != msg.getCRC()[index]) {
				message = ClientMessage.OUT_OF_DATE;
				break;
			}
		}

		return message;
	}

}
