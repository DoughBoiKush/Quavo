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

import com.quavo.osrs.network.protocol.codec.login.world.WorldLoginDecoder;
import com.quavo.util.buf.ByteBufUtils;

import io.netty.buffer.ByteBuf;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class MachineInformation {

	/**
	 * The operating system architecture type.
	 */
	private final int osArch;

	/**
	 * If the machine using the client is a 64 bit CPU.
	 */
	private final boolean is64Bit;

	/**
	 * The operating system build.
	 */
	private final int osBuild;

	/**
	 * The java vendor.
	 */
	private final int vendor;

	/**
	 * Constructs a new object.
	 * 
	 * @param osArch The os architecture.
	 * @param is64Bit If the cpu is a 64 bit.
	 * @param osBuild The os build.
	 * @param vendor The java vendor.
	 */
	public MachineInformation(int osArch, boolean is64Bit, int osBuild, int vendor) {
		this.osArch = osArch;
		this.is64Bit = is64Bit;
		this.osBuild = osBuild;
		this.vendor = vendor;
	}

	/**
	 * Decodes the machine information of the user during the {@link WorldLoginDecoder}.
	 * 
	 * @param buffer The {@link ByteBuf}.
	 * @return The created machine information.
	 */
	public static MachineInformation decode(ByteBuf buffer) {
		buffer.readByte();
		int osArch = buffer.readByte();
		boolean is64Bit = buffer.readByte() == 1;
		int osBuild = buffer.readByte();
		int vendor = buffer.readByte();
		buffer.readByte();
		buffer.readByte();
		buffer.readByte();
		buffer.readByte();
		buffer.readShort();
		buffer.readByte();
		buffer.readMedium();
		buffer.readShort();
		ByteBufUtils.readJagString(buffer);
		ByteBufUtils.readJagString(buffer);
		ByteBufUtils.readJagString(buffer);
		ByteBufUtils.readJagString(buffer);
		buffer.readByte();
		buffer.readShort();
		ByteBufUtils.readJagString(buffer);
		ByteBufUtils.readJagString(buffer);
		buffer.readByte();
		buffer.readByte();
		return new MachineInformation(osArch, is64Bit, osBuild, vendor);
	}

	/**
	 * Gets the osArch.
	 * 
	 * @return the osArch
	 */
	public int getOSArch() {
		return osArch;
	}

	/**
	 * Gets the is64Bit.
	 * 
	 * @return the is64Bit
	 */
	public boolean isIs64Bit() {
		return is64Bit;
	}

	/**
	 * Gets the osBuild.
	 * 
	 * @return the osBuild
	 */
	public int getOSBuild() {
		return osBuild;
	}

	/**
	 * Gets the vendor.
	 * 
	 * @return the vendor
	 */
	public int getVendor() {
		return vendor;
	}

}
