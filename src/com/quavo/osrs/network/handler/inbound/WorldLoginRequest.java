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
package com.quavo.osrs.network.handler.inbound;

import com.quavo.osrs.game.model.inter.DisplayMode;
import com.quavo.osrs.game.node.actor.player.Player;
import com.quavo.osrs.game.node.actor.player.info.LoginClearance;
import com.quavo.osrs.game.node.actor.player.info.MachineInformation;
import com.quavo.osrs.network.handler.NetworkMessage;
import com.quavo.osrs.network.protocol.codec.login.LoginType;
import com.quavo.util.IsaacRandomPair;

import io.netty.channel.ChannelHandler;
import net.burtleburtle.bob.rand.IsaacRandom;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class WorldLoginRequest extends NetworkMessage {

	/**
	 * The {@link LoginType}.
	 */
	private final LoginType type;

	/**
	 * The username of the {@link Player}.
	 */
	private final String username;

	/**
	 * The password of the {@link Player}.
	 */
	private final String password;

	/**
	 * The {@link LoginClearance} security type.
	 */
	private final LoginClearance clearance;

	/**
	 * The {@link DisplayMode} of the game client.
	 */
	private final DisplayMode displayMode;

	/**
	 * The {@link MachineInformation} of the user.
	 */
	private final MachineInformation machineInformation;

	/**
	 * The entries crc.
	 */
	private final int[] crc;

	/**
	 * The game client token.
	 */
	private final String token;

	/**
	 * The {@link IsaacRandomPair} used for encoding and decoding game packets using {@link IsaacRandom}.
	 */
	private final IsaacRandomPair isaacPair;

	/**
	 * Constructs a new object.
	 * 
	 * @param handler The {@link ChannelHandler} used for this request.
	 * @param type The login type.
	 * @param username The username.
	 * @param password The password.
	 * @param clearance The login security clearance.
	 * @param displayMode The display mode.
	 * @param machineInformation The machine information.
	 * @param crc The crc.
	 * @param token The game client token.
	 * @param isaacPair The isaac random pair.
	 */
	public WorldLoginRequest(ChannelHandler handler, LoginType type, String username, String password, LoginClearance clearance, DisplayMode displayMode, MachineInformation machineInformation, int[] crc, String token, IsaacRandomPair isaacPair) {
		super(handler);
		this.type = type;
		this.username = username;
		this.password = password;
		this.clearance = clearance;
		this.displayMode = displayMode;
		this.machineInformation = machineInformation;
		this.crc = crc;
		this.token = token;
		this.isaacPair = isaacPair;
	}

	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public LoginType getType() {
		return type;
	}

	/**
	 * Gets the username.
	 * 
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Gets the password.
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Gets the clearance.
	 * 
	 * @return the clearance
	 */
	public LoginClearance getClearance() {
		return clearance;
	}

	/**
	 * Gets the displayMode.
	 * 
	 * @return the displayMode
	 */
	public DisplayMode getDisplayMode() {
		return displayMode;
	}

	/**
	 * Gets the machineInformation.
	 * 
	 * @return the machineInformation
	 */
	public MachineInformation getMachineInformation() {
		return machineInformation;
	}

	/**
	 * Gets the crc.
	 * 
	 * @return the crc
	 */
	public int[] getCRC() {
		return crc;
	}

	/**
	 * Gets the token.
	 * 
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Gets the isaacPair.
	 * 
	 * @return the isaacPair
	 */
	public IsaacRandomPair getIsaacPair() {
		return isaacPair;
	}

}
