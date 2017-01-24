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
package com.quavo.osrs.network.protocol.cache;

import java.io.IOException;
import java.nio.ByteBuffer;

import com.quavo.osrs.Constants;

import net.openrs.cache.Cache;
import net.openrs.cache.ChecksumTable;
import net.openrs.cache.Container;
import net.openrs.cache.FileStore;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class CacheManager {

	/**
	 * The stored {@link Cache} used for this manager.
	 */
	private static Cache cache;

	/**
	 * The {@link ByteBuffer} for the {@link ChecksumTable}.
	 */
	private static ByteBuffer checksumBuffer;

	/**
	 * The {@link ChecksumTable} for this manager.
	 */
	private static ChecksumTable checksumTable;

	/**
	 * Loads and stores the cache.
	 */
	public static void load() {
		try {
			cache = new Cache(FileStore.open(Constants.CACHE_PATH));
			checksumTable = cache.createChecksumTable();
			checksumBuffer = new Container(Container.COMPRESSION_NONE, checksumTable.encode()).encode();
			System.out.println("Loaded " + cache.getTypeCount() + " cache indices.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets the cache.
	 * 
	 * @return the cache
	 */
	public static Cache getCache() {
		return cache;
	}

	/**
	 * Gets the checksumBuffer.
	 * 
	 * @return the checksumBuffer
	 */
	public static ByteBuffer getChecksumBuffer() {
		return checksumBuffer;
	}

	/**
	 * Gets the checksumTable.
	 * 
	 * @return the checksumTable
	 */
	public static ChecksumTable getChecksumTable() {
		return checksumTable;
	}

}
