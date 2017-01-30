package com.quavo.osrs.network.protocol.packet;

import com.quavo.util.buf.ByteBufUtils;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * A class which assists in creating a {@link GamePacket}.
 *
 * @author Graham
 */
public final class GamePacketBuilder {

	/**
	 * The current bit index.
	 */
	private int bitIndex;

	/**
	 * The buffer.
	 */
	private ByteBuf buffer;

	/**
	 * The current mode.
	 */
	private AccessMode mode = AccessMode.BYTE_ACCESS;

	/**
	 * Constructs a new {@code GamePacketBuilder} object.
	 */
	public GamePacketBuilder() {
		this.buffer = Unpooled.buffer();
	}

	/**
	 * Constructs a new {@code GamePacketBuilder} object.
	 *
	 * @param buffer The buffer.
	 */
	public GamePacketBuilder(ByteBuf buffer) {
		this.buffer = buffer;
	}

	/**
	 * Checks that this builder is in the bit access mode.
	 *
	 * @throws IllegalStateException If the builder is not in bit access mode.
	 */
	private void checkBitAccess() {
		if (mode != AccessMode.BIT_ACCESS) {
			throw new IllegalArgumentException("For bit-based calls to work, the mode must be bit access.");
		}
	}

	/**
	 * Checks that this builder is in the byte access mode.
	 *
	 * @throws IllegalStateException If the builder is not in byte access mode.
	 */
	private void checkByteAccess() {
		if (mode != AccessMode.BYTE_ACCESS) {
			throw new IllegalArgumentException("For byte-based calls to work, the mode must be byte access.");
		}
	}

	/**
	 * Gets the current length of the builder's buffer.
	 *
	 * @return The length of the buffer.
	 */
	public int getLength() {
		checkByteAccess();
		return buffer.writerIndex();
	}

	/**
	 * Puts a standard data type with the specified value, byte order and transformation.
	 *
	 * @param type The data type.
	 * @param order The byte order.
	 * @param transformation The transformation.
	 * @param value The value.
	 * @throws IllegalArgumentException If the type, order, or transformation is unknown.
	 */
	public void put(DataType type, DataOrder order, DataTransformation transformation, Number value) {
		checkByteAccess();
		long longValue = value.longValue();
		int length = type.getBytes();
		switch (order) {
		case BIG:
			for (int i = length - 1; i >= 0; i--) {
				if (i == 0 && transformation != DataTransformation.NONE) {
					if (transformation == DataTransformation.ADD) {
						buffer.writeByte((byte) (longValue + 128));
					} else if (transformation == DataTransformation.NEGATE) {
						buffer.writeByte((byte) -longValue);
					} else if (transformation == DataTransformation.SUBTRACT) {
						buffer.writeByte((byte) (128 - longValue));
					} else {
						throw new IllegalArgumentException("Unknown transformation.");
					}
				} else {
					buffer.writeByte((byte) (longValue >> i * 8));
				}
			}
			break;
		case INVERSED_MIDDLE:
			if (transformation != DataTransformation.NONE) {
				throw new IllegalArgumentException("Inversed middle endian cannot be transformed.");
			}

			if (type != DataType.INT) {
				throw new IllegalArgumentException("Inversed middle endian can only be used with an integer.");
			}
			buffer.writeByte((byte) (longValue >> 16));
			buffer.writeByte((byte) (longValue >> 24));
			buffer.writeByte((byte) longValue);
			buffer.writeByte((byte) (longValue >> 8));
			break;
		case LITTLE:
			for (int i = 0; i < length; i++) {
				if (i == 0 && transformation != DataTransformation.NONE) {
					if (transformation == DataTransformation.ADD) {
						buffer.writeByte((byte) (longValue + 128));
					} else if (transformation == DataTransformation.NEGATE) {
						buffer.writeByte((byte) -longValue);
					} else if (transformation == DataTransformation.SUBTRACT) {
						buffer.writeByte((byte) (128 - longValue));
					} else {
						throw new IllegalArgumentException("Unknown transformation.");
					}
				} else {
					buffer.writeByte((byte) (longValue >> i * 8));
				}
			}
			break;
		case MIDDLE:
			if (transformation != DataTransformation.NONE) {
				throw new IllegalArgumentException("Middle endian cannot be transformed.");
			}

			if (type != DataType.INT) {
				throw new IllegalArgumentException("Middle endian can only be used with an integer.");
			}

			buffer.writeByte((byte) (longValue >> 8));
			buffer.writeByte((byte) longValue);
			buffer.writeByte((byte) (longValue >> 24));
			buffer.writeByte((byte) (longValue >> 16));
			break;
		default:
			throw new IllegalArgumentException("Unknown order.");
		}
	}

	/**
	 * Puts a standard data type with the specified value and byte order.
	 *
	 * @param type The data type.
	 * @param order The byte order.
	 * @param value The value.
	 */
	public void put(DataType type, DataOrder order, Number value) {
		put(type, order, DataTransformation.NONE, value);
	}

	/**
	 * Puts a standard data type with the specified value and transformation.
	 *
	 * @param type The type.
	 * @param transformation The transformation.
	 * @param value The value.
	 */
	public void put(DataType type, DataTransformation transformation, Number value) {
		put(type, DataOrder.BIG, transformation, value);
	}

	/**
	 * Puts a standard data type with the specified value.
	 *
	 * @param type The data type.
	 * @param value The value.
	 */
	public void put(DataType type, Number value) {
		put(type, DataOrder.BIG, DataTransformation.NONE, value);
	}

	/**
	 * Puts a single bit into the buffer. If {@code flag} is {@code true}, the value of the bit is {@code 1}. If
	 * {@code flag} is {@code false}, the value of the bit is {@code 0}.
	 *
	 * @param flag The flag.
	 */
	public void putBit(boolean flag) {
		putBit(flag ? 1 : 0);
	}

	/**
	 * Puts a single bit into the buffer with the value {@code value}.
	 *
	 * @param value The value.
	 */
	public void putBit(int value) {
		putBits(1, value);
	}

	/**
	 * Puts {@code numBits} into the buffer with the value {@code value}.
	 *
	 * @param numBits The number of bits to put into the buffer.
	 * @param value The value.
	 * @throws IllegalArgumentException If the number of bits is not between 1 and 31 inclusive.
	 */
	public void putBits(int numBits, int value) {
		checkBitAccess();

		int bytePos = bitIndex >> 3;
		int bitOffset = 8 - (bitIndex & 7);
		bitIndex += numBits;

		int requiredSpace = bytePos - buffer.writerIndex() + 1;
		requiredSpace += (numBits + 7) / 8;
		buffer.ensureWritable(requiredSpace);

		for (; numBits > bitOffset; bitOffset = 8) {
			int tmp = buffer.getByte(bytePos);
			tmp &= ~DataConstants.BIT_MASK[bitOffset];
			tmp |= value >> numBits - bitOffset & DataConstants.BIT_MASK[bitOffset];
			buffer.setByte(bytePos++, tmp);
			numBits -= bitOffset;
		}
		if (numBits == bitOffset) {
			int tmp = buffer.getByte(bytePos);
			tmp &= ~DataConstants.BIT_MASK[bitOffset];
			tmp |= value & DataConstants.BIT_MASK[bitOffset];
			buffer.setByte(bytePos, tmp);
		} else {
			int tmp = buffer.getByte(bytePos);
			tmp &= ~(DataConstants.BIT_MASK[numBits] << bitOffset - numBits);
			tmp |= (value & DataConstants.BIT_MASK[numBits]) << bitOffset - numBits;
			buffer.setByte(bytePos, tmp);
		}
	}

	/**
	 * Puts the specified byte array into the buffer.
	 *
	 * @param bytes The byte array.
	 */
	public void putBytes(byte[] bytes) {
		buffer.writeBytes(bytes);
	}

	/**
	 * Puts the bytes from the specified buffer into this packet's buffer.
	 *
	 * @param buffer The source {@link ByteBuf}.
	 */
	public void putBytes(ByteBuf buffer) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.markReaderIndex();
		try {
			buffer.readBytes(bytes);
		} finally {
			buffer.resetReaderIndex();
		}
		putBytes(bytes);
	}

	/**
	 * Puts the bytes into the buffer with the specified transformation.
	 *
	 * @param transformation The transformation.
	 * @param bytes The byte array.
	 */
	public void putBytes(DataTransformation transformation, byte[] bytes) {
		if (transformation == DataTransformation.NONE) {
			putBytes(bytes);
		} else {
			for (byte b : bytes) {
				put(DataType.BYTE, transformation, b);
			}
		}
	}

	/**
	 * Puts the specified byte array into the buffer in reverse.
	 *
	 * @param bytes The byte array.
	 */
	public void putBytesReverse(byte[] bytes) {
		checkByteAccess();
		for (int i = bytes.length - 1; i >= 0; i--) {
			buffer.writeByte(bytes[i]);
		}
	}

	/**
	 * Puts the bytes from the specified buffer into this packet's buffer, in reverse.
	 *
	 * @param buffer The source {@link ByteBuf}.
	 */
	public void putBytesReverse(ByteBuf buffer) {
		byte[] bytes = new byte[buffer.readableBytes()];
		buffer.markReaderIndex();
		try {
			buffer.readBytes(bytes);
		} finally {
			buffer.resetReaderIndex();
		}
		putBytesReverse(bytes);
	}

	/**
	 * Puts the specified byte array into the buffer in reverse with the specified transformation.
	 *
	 * @param transformation The transformation.
	 * @param bytes The byte array.
	 */
	public void putBytesReverse(DataTransformation transformation, byte[] bytes) {
		if (transformation == DataTransformation.NONE) {
			putBytesReverse(bytes);
		} else {
			for (int i = bytes.length - 1; i >= 0; i--) {
				put(DataType.BYTE, transformation, bytes[i]);
			}
		}
	}

	/**
	 * Puts a smart into the buffer.
	 *
	 * @param value The value.
	 */
	public void putSmart(int value) {
		checkByteAccess();
		if (value >= 128) {
			buffer.writeShort(value + 32768);
		} else {
			buffer.writeByte(value);
		}
	}

	/**
	 * Puts a large smart into the buffer.
	 *
	 * @param value The value.
	 */
	public void putLargeSmart(int value) {
		checkByteAccess();
		if (value >= Short.MAX_VALUE) {
			buffer.writeInt(value - Integer.MAX_VALUE - 1);
		} else {
			buffer.writeShort(value >= 0 ? value : 32767);
		}
	}

	/**
	 * Puts a string into the buffer.
	 *
	 * @param str The string.
	 */
	public void putString(String str) {
		checkByteAccess();

		buffer.writeBytes(str.getBytes());
		buffer.writeByte(ByteBufUtils.STRING_TERMINATOR);
	}

	/**
	 * Puts a jagex string into the buffer.
	 *
	 * @param str The string.
	 */
	public void putJagString(String str) {
		checkByteAccess();

		ByteBufUtils.writeJagString(buffer, str);
	}

	/**
	 * Switches this builder's mode to the bit access mode.
	 *
	 * @throws IllegalStateException If the builder is already in bit access mode.
	 */
	public void switchToBitAccess() {
		if (mode == AccessMode.BIT_ACCESS) {
			return;
		}

		mode = AccessMode.BIT_ACCESS;
		bitIndex = buffer.writerIndex() * 8;
	}

	/**
	 * Switches this builder's mode to the byte access mode.
	 *
	 * @throws IllegalStateException If the builder is already in byte access mode.
	 */
	public void switchToByteAccess() {
		if (mode == AccessMode.BYTE_ACCESS) {
			return;
		}

		mode = AccessMode.BYTE_ACCESS;
		buffer.writerIndex((bitIndex + 7) / 8);
	}

	/**
	 * Gets the buffer.
	 *
	 * @return the buffer
	 */
	public ByteBuf getBuffer() {
		return buffer;
	}

}
