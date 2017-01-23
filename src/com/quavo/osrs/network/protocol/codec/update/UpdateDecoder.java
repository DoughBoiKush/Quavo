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
package com.quavo.osrs.network.protocol.codec.update;

import java.util.List;
import java.util.Optional;

import com.quavo.osrs.network.handler.inbound.UpdateRequest;
import com.quavo.osrs.network.handler.inbound.XOREncryptionRequest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class UpdateDecoder extends ByteToMessageDecoder {

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (!in.isReadable() || in.readableBytes() < 4) {
			return;
		}

		Optional<UpdateType> request = UpdateType.getType(in.readUnsignedByte());
		if (request.isPresent()) {
			UpdateType updateType = request.get();
			switch (updateType) {
			case LOW_PRIORITY_UPDATE:
			case HIGH_PRIORITY_UPDATE:
				int uid = in.readUnsignedMedium();
				int type = (uid >> 16);
				int id = (uid & 0xffff);

				out.add(new UpdateRequest(this, type, id, updateType == UpdateType.HIGH_PRIORITY_UPDATE));
				break;
			case XOR_ENCRYPTION_UPDATE:
				int key = in.readUnsignedByte();
				in.readUnsignedShort();
				out.add(new XOREncryptionRequest(this, key));
				break;
			}
		} else {
			in.readUnsignedMedium();
		}
	}

}
