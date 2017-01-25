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

import com.google.common.base.Preconditions;
import com.quavo.osrs.game.node.actor.player.Player;
import com.quavo.osrs.network.handler.NetworkMessageListener;
import com.quavo.osrs.network.handler.inbound.GamePacketRequest;
import com.quavo.osrs.network.handler.outbound.GamePacketResponse;
import com.quavo.osrs.network.protocol.packet.PacketRepository;
import com.quavo.osrs.network.protocol.packet.context.PacketContext;
import com.quavo.osrs.network.protocol.packet.encode.PacketEncoder;

import io.netty.channel.ChannelHandlerContext;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class GamePacketListener implements NetworkMessageListener<GamePacketRequest> {

	@Override
	public void handleMessage(ChannelHandlerContext ctx, GamePacketRequest msg) {
		System.out.println("INCOMING PACKET: " + msg.getId());
	}

	public static boolean sendGamePacket(Player player, PacketContext context) {
		// safe cast
		PacketEncoder<PacketContext> packet = (PacketEncoder<PacketContext>) PacketRepository.getPacketEncoder(context);
		packet.encode(player, context);

		Preconditions.checkArgument(packet.getId() != -1);

		player.getChannel().write(new GamePacketResponse(packet));
		return true;
	}

}
