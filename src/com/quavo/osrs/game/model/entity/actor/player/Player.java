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
package com.quavo.osrs.game.model.entity.actor.player;

import com.quavo.osrs.game.model.entity.actor.Actor;
import com.quavo.osrs.game.model.entity.actor.player.info.DisplayInformation;
import com.quavo.osrs.game.model.entity.actor.player.manager.InterfaceManager;
import com.quavo.osrs.game.model.entity.actor.player.manager.SocialManager;
import com.quavo.osrs.game.world.region.Viewport;
import com.quavo.osrs.network.handler.listener.GamePacketListener;
import com.quavo.osrs.network.protocol.packet.context.PacketContext;
import com.quavo.osrs.network.protocol.packet.context.impl.StaticRegionContext;
import com.quavo.osrs.network.protocol.packet.context.impl.VarpContext;
import com.quavo.osrs.network.protocol.packet.context.impl.VarpResetContext;

import io.netty.channel.Channel;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class Player extends Actor {

	/**
	 * The {@link Channel}.
	 */
	private final Channel channel;

	/**
	 * The {@link Viewport}.
	 */
	private final Viewport viewport = new Viewport(this);

	/**
	 * The {@link InterfaceManager}.
	 */
	private final InterfaceManager interfaceManager = new InterfaceManager(this);

	/**
	 * The {@link SocialManager}.
	 */
	private final SocialManager socialManager = new SocialManager(this);

	/**
	 * If the player is online.
	 */
	private boolean online;

	/**
	 * Constructs a new object.
	 * 
	 * @param channel The channel.
	 */
	public Player(Channel channel) {
		this.channel = channel;
	}

	/**
	 * Initiates the player.
	 * 
	 * @param info The display information.
	 */
	public void init(DisplayInformation info) {
		sendPacket(new StaticRegionContext(true));
		interfaceManager.login(info);
	}

	/**
	 * Refreshes the player.
	 */
	public void refresh() {
		sendPacket(new VarpResetContext());
		sendVarp(18, 1);
		sendVarp(21, 67108864);
		sendVarp(22, 33554432);
		sendVarp(23, 64);
		sendVarp(43, 1);
		sendVarp(101, 0);
		sendVarp(166, 2);
		sendVarp(167, 0);
		sendVarp(168, 4);
		sendVarp(169, 4);
		sendVarp(170, 0);
		sendVarp(171, 0);
		sendVarp(173, 1);
		sendVarp(281, 1000);
		sendVarp(287, 0);
		sendVarp(300, 1000);
		sendVarp(406, 20);
		sendVarp(447, -1);
		sendVarp(486, 1073741824);
		sendVarp(520, 1);
		sendVarp(553, -2147483648);
		sendVarp(788, 128);
		sendVarp(810, 33554432);
		sendVarp(830, 4194304);
		sendVarp(849, -1);
		sendVarp(850, -1);
		sendVarp(851, -1);
		sendVarp(852, -1);
		sendVarp(853, -1);
		sendVarp(854, -1);
		sendVarp(855, -1);
		sendVarp(856, -1);
		sendVarp(872, 4);
		sendVarp(904, 246);
		sendVarp(913, 4194304);
		sendVarp(951, -1);
		sendVarp(952, -1);
		sendVarp(953, -1);
		sendVarp(954, -1);
		sendVarp(955, -1);
		sendVarp(956, -1);
		sendVarp(959, -1);
		sendVarp(960, -1);
		sendVarp(961, -1);
		sendVarp(962, -1);
		sendVarp(963, -1);
		sendVarp(1010, 2048);
		sendVarp(1017, 8192);
		sendVarp(1050, 4096);
		sendVarp(1055, 256);
		sendVarp(1065, -1);
		sendVarp(1067, -1711276032);
		sendVarp(1074, 0);
		sendVarp(1075, -1);
		sendVarp(1107, 0);
		sendVarp(1151, -1);
		sendVarp(1224, 172395585);
		sendVarp(1225, 379887846);
		sendVarp(1226, 12);
		sendGameMessage("Welcome to Quavo OSRS [#131].");
		
		online = true;
	}

	private void sendVarp(int id, int value) {
		sendPacket(new VarpContext(id, value));
	}

	/**
	 * Sends a game message.
	 * 
	 * @param message The message.
	 */
	public void sendGameMessage(String message) {
		socialManager.sendGameMessage("Welcome to Quavo OSRS [#131].");
	}

	/**
	 * Sends a packet.
	 * 
	 * @param context The {@link PacketContext}.
	 */
	public void sendPacket(PacketContext context) {
		GamePacketListener.sendGamePacket(this, context);
		if (channel.isRegistered()) {
			channel.flush();
		}
	}

	/**
	 * Gets the viewport.
	 * 
	 * @return the viewport
	 */
	public Viewport getViewport() {
		return viewport;
	}

	/**
	 * Gets the channel.
	 * 
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * Gets the interfaceManager.
	 * 
	 * @return the interfaceManager
	 */
	public InterfaceManager getInterfaceManager() {
		return interfaceManager;
	}

	/**
	 * Gets the socialManager.
	 * 
	 * @return the socialManager
	 */
	public SocialManager getSocialManager() {
		return socialManager;
	}

	/**
	 * Gets the online.
	 * 
	 * @return the online
	 */
	public boolean isOnline() {
		return online;
	}

	/**
	 * Sets the online.
	 * 
	 * @param online the online to set
	 */
	public void setOnline(boolean online) {
		this.online = online;
	}

}
