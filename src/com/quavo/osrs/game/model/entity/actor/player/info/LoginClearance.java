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

import java.util.Arrays;
import java.util.Optional;

import io.netty.buffer.ByteBuf;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public enum LoginClearance {

	/**
	 * Authenticator required.
	 */
	AUTHENTICATOR,

	/**
	 * No security clearance.
	 */
	NORMAL,

	/**
	 * Computer is trusted.
	 */
	TRUSTED_COMPUTER,

	/**
	 * Authenticator is trusted.
	 */
	TRUSTED_AUTHENTICATOR;

	/**
	 * Gets and returns a connection type wrapped in a {@link Optional}.
	 * 
	 * @param id The id used for getting a clearance level.
	 * @return The clearance level.
	 */
	public static Optional<LoginClearance> getType(int id) {
		return Arrays.stream(LoginClearance.values()).filter(a -> a.ordinal() == id).findAny();
	}

	/**
	 * Reads the data from a {@link ByteBuf} for a login clearance.
	 * 
	 * @param buffer The {@link ByteBuf}.
	 */
	public void readNoReturn(ByteBuf buffer) {
		switch (this) {
		case NORMAL:
			buffer.readerIndex(buffer.readerIndex() + 8);
			break;
		case TRUSTED_COMPUTER:
			buffer.readInt();
			buffer.readerIndex(buffer.readerIndex() + 4);
			break;
		case AUTHENTICATOR:
		case TRUSTED_AUTHENTICATOR:
			buffer.readMedium();
			buffer.readerIndex(buffer.readerIndex() + 5);
			break;
		}
	}

	/**
	 * Reads the data from a {@link ByteBuf} for a login clearance.
	 * 
	 * @param buffer The {@link ByteBuf}.
	 * @return The created login clearance type.
	 */
	public LoginClearance read(ByteBuf buffer) {
		switch (this) {
		case NORMAL:
			buffer.readerIndex(buffer.readerIndex() + 8);
			break;
		case TRUSTED_COMPUTER:
			buffer.readInt();
			buffer.readerIndex(buffer.readerIndex() + 4);
			break;
		case AUTHENTICATOR:
		case TRUSTED_AUTHENTICATOR:
			buffer.readMedium();
			buffer.readerIndex(buffer.readerIndex() + 5);
			break;
		}
		return this;
	}

}
