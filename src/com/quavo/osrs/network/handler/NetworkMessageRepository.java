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
package com.quavo.osrs.network.handler;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableMap;
import com.quavo.osrs.Constants;
import com.quavo.utilities.FileUtilities;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class NetworkMessageRepository {

	/**
	 * The {@link ImmutableMap} of {@link NetworkMessageListener}'s used to process protocol requests and
	 * responses.
	 */
	private static final ImmutableMap<Class<?>, NetworkMessageListener<NetworkMessage>> LISTENERS = buildNetworkListeners();

	/**
	 * Builds the network listeners for this repository.
	 * 
	 * @return The created {@link ImmutableMap} of network listeners.
	 */
	private static ImmutableMap<Class<?>, NetworkMessageListener<NetworkMessage>> buildNetworkListeners() {
		final ImmutableMap.Builder<Class<?>, NetworkMessageListener<NetworkMessage>> builder = ImmutableMap.builder();

		try {
			Class<?>[] classes = FileUtilities.getAllClasses(Constants.PRESENTATION + ".network.handler.listener");
			for (Class<?> clazz : classes) {
				Preconditions.checkNotNull(clazz, "Listener class is not usable.");// returns type too.

				Class<?> message = Class.forName(clazz.getGenericInterfaces()[0].toString().replace(Constants.PRESENTATION + ".network.handler.NetworkMessageListener<", "").replace(">", ""));

				// this is a safe cast.
				builder.put(message, (NetworkMessageListener<NetworkMessage>) clazz.newInstance());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return builder.build();
	}

	/**
	 * Finds and returns a {@link NetworkMessageListener} for a {@link NetworkMessage} found in the network
	 * channel.
	 * 
	 * @param message The {@link NetworkMessage} used.
	 * @return The {@link NetworkMessageListener}.
	 */
	public static NetworkMessageListener<NetworkMessage> getNetworkListener(NetworkMessage message) {
		Preconditions.checkArgument(LISTENERS.containsKey(message.getClass()), "Listener not found for the message request " + message.getClass().getSimpleName() + ".");

		return LISTENERS.get(message.getClass());
	}

	/**
	 * Gets the listeners.
	 * 
	 * @return the listeners
	 */
	public static ImmutableMap<Class<?>, NetworkMessageListener<NetworkMessage>> getListeners() {
		return LISTENERS;
	}

}
