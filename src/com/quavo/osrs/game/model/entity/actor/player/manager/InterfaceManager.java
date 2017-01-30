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
package com.quavo.osrs.game.model.entity.actor.player.manager;

import com.quavo.osrs.game.model.entity.actor.player.Player;
import com.quavo.osrs.game.model.entity.actor.player.info.DisplayInformation;
import com.quavo.osrs.game.model.inter.DisplayMode;
import com.quavo.osrs.network.protocol.packet.context.impl.GamePanelContext;
import com.quavo.osrs.network.protocol.packet.context.impl.InterfaceContext;
import com.quavo.osrs.network.protocol.packet.context.impl.InterfaceSetContext;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class InterfaceManager {

	/**
	 * The {@link Player}.
	 */
	private final Player player;

	/**
	 * The {@link DisplayInformation}.
	 */
	private DisplayInformation displayInformation;

	/**
	 * Constructs a new object.
	 * 
	 * @param player The player.
	 */
	public InterfaceManager(Player player) {
		this.player = player;
	}

	/**
	 * Sends the interfaces during the player login.
	 * 
	 * @param info The {@link DisplayInformation}.
	 */
	public void login(DisplayInformation info) {
		this.displayInformation = info;

		DisplayMode mode = displayInformation.getDisplayMode();
		sendGamePanel(165);
		sendInterface(165, 162, 1, true);
		sendInterface(165, 163, 22, true);
		sendInterface(165, 160, 23, true);
		sendInterface(165, 593, 7, true);
		sendInterface(165, 320, 8, true);
		sendInterface(165, 76, 9, true);
		sendInterface(165, 149, 10, true);
		sendInterface(165, 387, 11, true);
		// sendInterface(165, 271, 12, true);//TODO prayer interface
		sendInterface(165, 218, 13, true);
		sendInterface(165, 589, 14, true);
		sendInterface(165, 429, 15, true);
		sendInterface(165, 432, 16, true);
		sendInterface(165, 182, 17, true);
		sendInterface(165, 261, 18, true);
		sendInterface(165, 216, 19, true);
		sendInterface(165, 239, 20, true);
		switch (mode) {
		case FIXED_MODE:
			break;
		case RESIZABLE_MODE:
			break;
		case RESIZABLE_PANELS:
			player.sendPacket(new InterfaceSetContext(165, 23, 164, 21));// orbs
			player.sendPacket(new InterfaceSetContext(165, 1, 164, 24));// chat
			for (int i = 1; i <= 14; i++) {// tabs
				player.sendPacket(new InterfaceSetContext(165, i + 6, 164, i + 58));
			}
			break;
		}
		sendGamePanel(mode.getRoot());
	}

	/**
	 * Sends an interface to the client.
	 * 
	 * @param panelId The game panel.
	 * @param interfaceId The interface id.
	 * @param childId The child id.
	 * @param fixed If fixed.
	 */
	public void sendInterface(int panelId, int interfaceId, int childId, boolean fixed) {
		player.sendPacket(new InterfaceContext(panelId, interfaceId, childId, fixed));
	}

	/**
	 * Sends a root game panel interface.
	 * 
	 * @param id The game panel interface id.
	 */
	public void sendGamePanel(int id) {
		player.sendPacket(new GamePanelContext(id));
	}

	/**
	 * Gets the displayInformation.
	 * 
	 * @return the displayInformation
	 */
	public DisplayInformation getDisplayInformation() {
		return displayInformation;
	}

	/**
	 * Sets the displayInformation.
	 * 
	 * @param displayInformation the displayInformation to set
	 */
	public void setDisplayInformation(DisplayInformation displayInformation) {
		this.displayInformation = displayInformation;
	}

}
