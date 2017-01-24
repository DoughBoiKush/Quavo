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
package com.quavo.util.buf;

import java.math.BigInteger;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class ByteBufUtils {

	/**
	 * The string terminator.
	 */
	public static final int STRING_TERMINATOR = 0;

	/**
	 * Encrypts a {@link ByteBuf} using with a exponent and modulus.
	 * 
	 * @param buffer The {@link ByteBuf} to encrypt.
	 * @param exponent The exponent.
	 * @param modulus The modulus.
	 * @return The encrypted buffer.
	 */
	public static ByteBuf encipherRSA(ByteBuf buffer, BigInteger exponent, BigInteger modulus) {
		byte[] bytes = new byte[buffer.readShort()];
		buffer.readBytes(bytes);
		return Unpooled.wrappedBuffer(new BigInteger(bytes).modPow(exponent, modulus).toByteArray());
	}

	/**
	 * Deciphers the specified {@link ByteBuf} with the given key.
	 * 
	 * @param buffer The {@link ByteBuf}.
	 * @param key The key.
	 * @return The new {@link ByteBuf}.
	 */
	public static ByteBuf decipherXTEA(ByteBuf buffer, int[] key) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.readBytes(bytes);
		ByteBuf xteaBuffer = Unpooled.wrappedBuffer(bytes);
		decipherXTEA(xteaBuffer, 0, bytes.length, key);
		return xteaBuffer;
	}

	/**
	 * Deciphers the specified {@link ByteBuf} with the given key.
	 * 
	 * @param buffer The {@link ByteBuf}.
	 * @param key The key.
	 * @throws IllegalArgumentException if the key is not exactly 4 elements long.
	 */
	private static void decipherXTEA(ByteBuf buffer, int start, int end, int[] key) {
		if (key.length != 4) {
			throw new IllegalArgumentException();
		}
		int numQuads = (end - start) / 8;
		for (int i = 0; i < numQuads; i++) {
			int sum = 0x9E3779B9 * 32;
			int v0 = buffer.getInt(start + i * 8);
			int v1 = buffer.getInt(start + i * 8 + 4);
			for (int j = 0; j < 32; j++) {
				v1 -= (((v0 << 4) ^ (v0 >>> 5)) + v0) ^ (sum + key[(sum >>> 11) & 3]);
				sum -= 0x9E3779B9;
				v0 -= (((v1 << 4) ^ (v1 >>> 5)) + v1) ^ (sum + key[sum & 3]);
			}
			buffer.setInt(start + i * 8, v0);
			buffer.setInt(start + i * 8 + 4, v1);
		}
	}

	/**
	 * Converts data from a {@link ByteBuf} into a readable string.
	 *
	 * @param buffer The {@link ByteBuf}.
	 * @return The string.
	 */
	public static String readString(ByteBuf buffer) {
		StringBuilder bldr = new StringBuilder();
		byte b;
		while (buffer.isReadable() && (b = buffer.readByte()) != STRING_TERMINATOR) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

	/**
	 * Converts data from a {@link ByteBuf} into a readable jag string.
	 *
	 * @param buffer The {@link ByteBuf}.
	 * @return The string.
	 */
	public static String readJagString(ByteBuf buffer) {
		StringBuilder bldr = new StringBuilder();
		byte b;
		buffer.readByte();
		while (buffer.isReadable() && (b = buffer.readByte()) != STRING_TERMINATOR) {
			bldr.append((char) b);
		}
		return bldr.toString();
	}

}
