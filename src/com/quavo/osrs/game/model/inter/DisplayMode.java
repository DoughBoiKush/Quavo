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
package com.quavo.osrs.game.model.inter;

import java.util.Arrays;
import java.util.Optional;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum DisplayMode {

	/**
	 * Fixed screen display mode.
	 */
	FIXED_MODE(548),

	/**
	 * Resizable screen display mode.
	 */
	RESIZABLE_MODE(161),

	/**
	 * Resizable with the flat panels.
	 */
	RESIZABLE_PANELS(164);

	/**
	 * The root interface id.
	 */
	private final int root;

	/**
	 * Constructs a new object.
	 * 
	 * @param root The root interface id.
	 */
	DisplayMode(int root) {
		this.root = root;
	}

	/**
	 * Gets and returns a connection type wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a display mode.
	 * @return The display mode.
	 */
	public static Optional<DisplayMode> getDisplayMode(int id) {
		return Arrays.stream(DisplayMode.values()).filter(a -> a.ordinal() == id).findAny();
	}

	/**
	 * Checks if the display mode is currently in resizable mode.
	 * 
	 * @return <true> if the display is resizable.
	 */
	public boolean isResizableMode() {
		return (this == DisplayMode.RESIZABLE_MODE);
	}

	/**
	 * Gets the root.
	 * 
	 * @return the root
	 */
	public int getRoot() {
		return root;
	}

}
