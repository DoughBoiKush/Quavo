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
package com.quavo.osrs.game.model.entity.actor.player.info;

import com.quavo.osrs.game.model.inter.DisplayMode;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class DisplayInformation {

	/**
	 * The {@link DisplayMode}.
	 */
	private DisplayMode displayMode;

	/**
	 * The screen width.
	 */
	private int width;

	/**
	 * The screen height.
	 */
	private int height;

	/**
	 * Constructs a new object.
	 * 
	 * @param displayMode The {@link DisplayMode}.
	 * @param width The screen width.
	 * @param height The screen height.
	 */
	public DisplayInformation(DisplayMode displayMode, int width, int height) {
		this.displayMode = displayMode;
		this.width = width;
		this.height = height;
	}

	/**
	 * Sets the display information of a game client.
	 * 
	 * @param mode The {@link DisplayMode}.
	 * @param width The game width.
	 * @param height The game height.
	 */
	public void setDisplayInformation(DisplayMode mode, int width, int height) {
		this.displayMode = mode;
		this.width = width;
		this.height = height;
	}

	/**
	 * Gets the displayMode.
	 * 
	 * @return the displayMode
	 */
	public DisplayMode getDisplayMode() {
		if (displayMode == null) {
			return DisplayMode.FIXED_MODE;
		}
		return displayMode;
	}

	/**
	 * Sets the displayMode.
	 * 
	 * @param displayMode the displayMode to set
	 */
	public void setDisplayMode(DisplayMode displayMode) {
		this.displayMode = displayMode;
	}

	/**
	 * Gets the width.
	 * 
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Sets the width.
	 * 
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}

	/**
	 * Gets the height.
	 * 
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Sets the height.
	 * 
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}

}
