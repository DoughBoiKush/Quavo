package com.quavo.osrs.network.protocol.packet;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableTable;
import com.quavo.osrs.Constants;
import com.quavo.osrs.network.protocol.packet.context.PacketContext;
import com.quavo.osrs.network.protocol.packet.decode.PacketDecoder;
import com.quavo.osrs.network.protocol.packet.decode.PacketDecoderIdentifier;
import com.quavo.osrs.network.protocol.packet.encode.PacketEncoder;
import com.quavo.util.FileUtilities;

import java.util.Map.Entry;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class PacketRepository {

	/**
	 * Create an immutable map of incoming packets. The parsed packet ids and their packet counter parts dont ever
	 * change. This is why we use an {@link ImmutableMap}.
	 */
	public static final ImmutableMap<PacketDecoderIdentifier[], PacketDecoder> PACKET_DECODERS = incomingBuilder();

	/**
	 * Create an immutable map of outgoing packets. The parsed packet ids and their packet counter parts dont ever
	 * change. This is why we use an {@link ImmutableMap}.
	 */
	public static final ImmutableMap<Class<?>, PacketEncoder<? extends PacketContext>> PACKET_ENCODERS = outgoingBuilder();

	/**
	 * Parses and builds the outgoing packets.
	 *
	 * @return The {@link ImmutableTable} built by this parser.
	 */
	public static ImmutableMap<Class<?>, PacketEncoder<? extends PacketContext>> outgoingBuilder() {
		final ImmutableMap.Builder<Class<?>, PacketEncoder<? extends PacketContext>> builder = ImmutableMap.builder();
		try {
			Class<?>[] outgoingClasses = FileUtilities.getAllClasses(Constants.PRESENTATION + ".network.protocol.packet.encode.impl");
			for (Class<?> clazz : outgoingClasses) {
				Preconditions.checkArgument(PacketEncoder.class.isAssignableFrom(clazz), "Packet is not extending the outgoing packet class.");

				Class<?> context = Class.forName(clazz.getGenericSuperclass().toString().replace(Constants.PRESENTATION + ".network.protocol.packet.encode.PacketEncoder<", "").replace(">", ""));
				builder.put(context, (PacketEncoder<?>) clazz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder.build();
	}

	/**
	 * Parses and builds the incoming packets.
	 *
	 * @return The {@link ImmutableTable} built by this parser.
	 */
	public static ImmutableMap<PacketDecoderIdentifier[], PacketDecoder> incomingBuilder() {
		final ImmutableMap.Builder<PacketDecoderIdentifier[], PacketDecoder> builder = ImmutableMap.builder();
		try {
			Class<?>[] incomingClasses = FileUtilities.getAllClasses(Constants.PRESENTATION + ".network.protocol.packet.decode.impl");
			for (Class<?> clazz : incomingClasses) {
				Preconditions.checkArgument(PacketDecoder.class.isAssignableFrom(clazz), "Packet is not extending the incoming packet class.");

				PacketDecoder packet = (PacketDecoder) clazz.newInstance();
				builder.put(packet.identifiers(), packet);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder.build();
	}

	/**
	 * Gets an outgoing packet for packet sending.
	 *
	 * @param context The packet context.
	 * @return The {@code PacketEncoder}.
	 */
	public static PacketEncoder<? extends PacketContext> getPacketEncoder(PacketContext context) {
		return PACKET_ENCODERS.get(Preconditions.checkNotNull(context).getClass());
	}

	/**
	 * Gets an incoming packet from the {@link ImmutableMap} we created.
	 *
	 * @param packetId The packet id being requested.
	 * @return The {@link PacketDecoder}.
	 */
	public static PacketDecoder getPacketDecoder(int packetId) {
		for (Entry<PacketDecoderIdentifier[], PacketDecoder> entry : PACKET_DECODERS.entrySet()) {
			for (PacketDecoderIdentifier key : entry.getKey()) {
				if (key.getId() == packetId) {
					return entry.getValue();
				}
			}
		}
		return null;
	}

}
