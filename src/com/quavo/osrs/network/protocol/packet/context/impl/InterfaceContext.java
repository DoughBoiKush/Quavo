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
package com.quavo.osrs.network.protocol.packet.context.impl;

import com.quavo.osrs.network.protocol.packet.context.PacketContext;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class InterfaceContext implements PacketContext {

	/**
	 * The game panel.
	 */
	private final int panelId;

	/**
	 * The interface id.
	 */
	private final int interfaceId;

	/**
	 * The panel child id.
	 */
	private final int childId;

	/**
	 * Represents if the interface is set to be fixed or static on the game panel.
	 */
	private final boolean fixed;

	/**
	 * Constructs a new object.
	 *
	 * @param panelId The game panel id.
	 * @param interfaceId The interface id.
	 * @param childId The panel child id.
	 * @param fixed If the interface is fixed on the panel.
	 */
	public InterfaceContext(int panelId, int interfaceId, int childId, boolean fixed) {
		this.panelId = panelId;
		this.interfaceId = interfaceId;
		this.childId = childId;
		this.fixed = fixed;
	}

	/**
	 * Gets the panelId.
	 * 
	 * @return the panelId
	 */
	public int getPanelId() {
		return panelId;
	}

	/**
	 * Gets the interfaceId.
	 * 
	 * @return the interfaceId
	 */
	public int getInterfaceId() {
		return interfaceId;
	}

	/**
	 * Gets the childId.
	 * 
	 * @return the childId
	 */
	public int getChildId() {
		return childId;
	}

	/**
	 * Gets the fixed.
	 * 
	 * @return the fixed
	 */
	public boolean isFixed() {
		return fixed;
	}

}
