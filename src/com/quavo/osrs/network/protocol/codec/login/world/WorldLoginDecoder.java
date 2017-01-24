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
package com.quavo.osrs.network.protocol.codec.login.world;

import java.math.BigInteger;
import java.util.List;

import com.quavo.osrs.game.model.inter.DisplayMode;
import com.quavo.osrs.game.node.actor.player.info.LoginClearance;
import com.quavo.osrs.game.node.actor.player.info.MachineInformation;
import com.quavo.osrs.network.handler.inbound.WorldLoginRequest;
import com.quavo.osrs.network.protocol.codec.login.LoginDecoder;
import com.quavo.osrs.network.protocol.codec.login.LoginType;
import com.quavo.util.IsaacRandomPair;
import com.quavo.util.buf.ByteBufUtils;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import net.burtleburtle.bob.rand.IsaacRandom;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class WorldLoginDecoder extends ByteToMessageDecoder {

	/**
	 * The login rsa exponent.
	 */
	private static final BigInteger EXPONENT = new BigInteger("10576590401440322986110047695651341967882733497127746373629378048138048538187119036342734495313507419048289616041709032673071798161365641163600617162178497");

	/**
	 * The login rsa modulus.
	 */
	private static final BigInteger MODULUS = new BigInteger("11206719348431650513171671018397173875527561036041859828114669681511079464644350457240662773502649504136578282907910504211944100665125647292825199995029019");

	/**
	 * The {@link LoginType} used for this login request from the {@link LoginDecoder}.
	 */
	private final LoginType type;

	/**
	 * Constructs a new object.
	 * 
	 * @param type The {@link LoginType} used for this login request from the {@link LoginDecoder}.
	 */
	public WorldLoginDecoder(LoginType type) {
		this.type = type;
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (!in.isReadable()) {
			return;
		}

		ByteBuf buffer = ByteBufUtils.encipherRSA(in, EXPONENT, MODULUS);
		int id = buffer.readByte();
		if (id != 1) {
			return;
		}
		int clearanceId = buffer.readByte();
		int[] clientKeys = new int[4];
		for (int index = 0; index < clientKeys.length; index++) {
			clientKeys[index] = buffer.readInt();
		}
		LoginClearance clearance = LoginClearance.getType(clearanceId).get().read(buffer);
		String password = ByteBufUtils.readString(buffer);
		buffer = ByteBufUtils.decipherXTEA(in, clientKeys);
		String username = ByteBufUtils.readString(buffer);
		DisplayMode mode = DisplayMode.getDisplayMode(buffer.readByte()).get();
		buffer.readShort();
		buffer.readShort();
		buffer.skipBytes(24);
		String token = ByteBufUtils.readString(buffer);
		buffer.readInt();
		MachineInformation machineInformation = MachineInformation.decode(buffer);
		buffer.readInt();
		buffer.readInt();
		buffer.readInt();
		buffer.readInt();
		buffer.readByte();
		int[] crc = new int[16];
		for (int index = 0; index < crc.length; index++) {
			crc[index] = buffer.readInt();
		}
		int[] serverKeys = new int[4];
		for (int index = 0; index < serverKeys.length; index++) {
			serverKeys[index] = clientKeys[index] + 50;
		}
		IsaacRandomPair isaacPair = new IsaacRandomPair(new IsaacRandom(serverKeys), new IsaacRandom(clientKeys));

		out.add(new WorldLoginRequest(this, type, username, password, clearance, mode, machineInformation, crc, token, isaacPair));
	}

}
