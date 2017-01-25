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
package com.quavo.util.tools;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * @author _jordan <citellumrsps@gmail.com>
 */
public final class WorldListGenerator {

	/**
	 * Runs the application.
	 * 
	 * @param args Runtime arguments.
	 */
	public static void main(String[] args) {
		try {
			/** SET HERE */
			final int id = 301;
			final int flag = 0x8;
			final String domain = "127.0.0.1";
			final String activity = "Home World";
			final int location = 225;
			final int population = 1;
			/** END */

			DataOutputStream output = new DataOutputStream(new FileOutputStream("./repo/slr.ws"));
			output.writeInt(35);
			output.writeShort(1);
			output.writeShort(id);
			output.writeInt(flag);
			output.writeBytes(domain);
			output.writeBytes(activity);
			output.writeByte(location);
			output.writeShort(population);
			output.flush();
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
