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
package com.quavo.osrs.game.world.region;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class Position {

	/**
	 * Represents the possible region sizes on the <b>Runescape</b> map.
	 */
	public static final int[] REGION_SIZES = new int[] { 104, 120, 136, 168 };

	/**
	 * Represents the X coordinate on the grid.
	 */
	private final short x;

	/**
	 * Represents the Y coordinate on the grid.
	 */
	private final short y;

	/**
	 * Represents the height of the coordinates {@link #x} and {@link #y}.
	 */
	private final byte plane;

	/**
	 * Constructs a new object.
	 *
	 * @param x The position X-coordinate.
	 * @param y The position Y-coordinate.
	 * @param plane The plane.
	 */
	public Position(int x, int y, int plane) {
		this.x = (short) x;
		this.y = (short) y;
		this.plane = (byte) plane;
	}

	/**
	 * Constructs a new object.
	 *
	 * @param position The position to transfer.
	 */
	public Position(Position position) {
		this.x = position.x;
		this.y = position.y;
		this.plane = position.plane;
	}

	/**
	 * Checks if this location is within range of another in a specific radius.
	 *
	 * @param position The position to compare.
	 * @param radius The radius.
	 * @return True if the position is in range.
	 */
	public boolean withinRange(Position position, int radius) {
		if (plane != position.plane) {
			return false;
		}
		int deltaX = (position.x - x);
		int deltaY = (position.y - y);
		return ((deltaX < (radius + 1) && deltaX > -(radius + 1) && deltaY < (radius + 1) && deltaY > -(radius + 1)));
	}

	/**
	 * Checks if the other location is within viewing distance.
	 *
	 * @param position The other location.
	 * @return If you're within the other distance.
	 */
	public boolean withinDistance(Position position) {
		if (position.plane != plane) {
			return false;
		}
		return Math.abs(position.x - x) <= 14 && Math.abs(position.y - y) <= 14;
	}

	/**
	 * Returns if a player is within a specified distance.
	 *
	 * @param position The other location.
	 * @param dist The amount of distance.
	 * @return If you're within the other distance.
	 */
	public boolean withinDistance(Position position, int dist) {
		if (position.plane != plane) {
			return false;
		}
		int deltaX = position.x - x, deltaY = position.y - y;
		return deltaX <= dist && deltaX >= -dist && deltaY <= dist && deltaY >= -dist;
	}

	/**
	 * Returns the X coordinate corresponding to the chunk of this position.
	 *
	 * @return The X in the chunk of this position.
	 */
	public int getChunkX() {
		return (x >> 3);
	}

	/**
	 * Returns the Y coordinate corresponding to the chunk of this position.
	 *
	 * @return The Y in the chunk of this position.
	 */
	public int getChunkY() {
		return (y >> 3);
	}

	/**
	 * Returns the X coordinate in the corresponding region.
	 *
	 * @return The X in the region of this position.
	 */
	public int getRegionX() {
		return (x >> 6);
	}

	/**
	 * Returns the Y coordinate in the corresponding region.
	 *
	 * @return The Y in the region of this position.
	 */
	public int getRegionY() {
		return (y >> 6);
	}

	/**
	 * Returns the local X coordinate.
	 *
	 * @param position The position.
	 * @param mapSize The actor map size.
	 * @return The local X coordinate.
	 */
	public int getLocalX(Position position, int mapSize) {
		return x - 8 * (position.getChunkX() - (REGION_SIZES[mapSize] >> 4));
	}

	/**
	 * Returns the local Y coordinate.
	 *
	 * @param position The position.
	 * @param mapSize The actor map size.
	 * @return The local Y coordinate.
	 */
	public int getLocalY(Position position, int mapSize) {
		return y - 8 * (position.getChunkY() - (REGION_SIZES[mapSize] >> 4));
	}

	/**
	 * Gets the local x-coordinate.
	 *
	 * @param position The location containing the regional coordinates.
	 * @return The local x-coordinate.
	 */
	public int getSceneX(Position position) {
		return x - ((position.getRegionX() - 6) << 3);
	}

	/**
	 * Gets the local y-coordinate.
	 *
	 * @param position The location containing the regional coordinates.
	 * @return The local y-coordinate.
	 */
	public int getSceneY(Position position) {
		return y - ((position.getRegionY() - 6) * 8);
	}

	/**
	 * Returns the X coordinate in the corresponding region.
	 *
	 * @return The X in the region of this {@link Location}.
	 */
	public int getXInRegion() {
		return x & 0x3F;
	}

	/**
	 * Returns the Y coordinate in the corresponding region.
	 *
	 * @return The Y in the region of this {@link Location}.
	 */
	public int getYInRegion() {
		return y & 0x3F;
	}

	/**
	 * Returns the unique <b>hash</b> of this position.
	 *
	 * @return The <b>hash</b>.
	 */
	public int getPositionHash() {
		return y + (x << 14) + (plane << 28);
	}

	/**
	 * Returns the hash of the region that this position is located on.
	 *
	 * @return The corresponding region hash of this position.
	 */
	public int getRegionHash() {
		return getRegionY() + (getRegionX() << 8) + (plane << 16);
	}

	/**
	 * Gets the x.
	 *
	 * @return the x
	 */
	public short getX() {
		return x;
	}

	/**
	 * Gets the y.
	 *
	 * @return the y
	 */
	public short getY() {
		return y;
	}

	/**
	 * Gets the plane.
	 *
	 * @return the plane
	 */
	public byte getPlane() {
		if (plane > 3) {
			return 3;
		}
		return plane;
	}

}
