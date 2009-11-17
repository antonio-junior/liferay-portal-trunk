/**
 * Copyright (c) 2000-2009 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.liferay.portal.kernel.util;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.nio.ByteBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * <a href="StreamUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author Brian Wing Shun Chan
 */
public class StreamUtil {

	public static final int BUFFER_SIZE = GetterUtil.getInteger(
		System.getProperty(
			"com.liferay.portal.kernel.util.StreamUtil.buffer.size"),
		8192);

	public static final boolean USE_NIO = GetterUtil.getBoolean(
		System.getProperty(
			"com.liferay.portal.kernel.util.StreamUtil.use.nio"),
		false);

	public static void cleanUp(Channel channel) {
		try {
			if (channel != null) {
				channel.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public static void cleanUp(Channel inputChannel, Channel outputChannel) {
		cleanUp(inputChannel);
		cleanUp(outputChannel);
	}

	public static void cleanUp(InputStream inputStream) {
		try {
			if (inputStream != null) {
				inputStream.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public static void cleanUp(
		InputStream inputStream, OutputStream outputStream) {

		cleanUp(outputStream);
		cleanUp(inputStream);
	}

	public static void cleanUp(OutputStream outputStream) {
		try {
			if (outputStream != null) {
				outputStream.flush();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}

		try {
			if (outputStream != null) {
				outputStream.close();
			}
		}
		catch (Exception e) {
			if (_log.isWarnEnabled()) {
				_log.warn(e, e);
			}
		}
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream)
		throws IOException {

		transfer(inputStream, outputStream, BUFFER_SIZE);
	}

	public static void transfer(
			InputStream inputStream, OutputStream outputStream, int bufferSize)
		throws IOException {

		if (inputStream == null) {
			throw new IllegalArgumentException("Input stream cannot be null");
		}

		if (outputStream == null) {
			throw new IllegalArgumentException("Output stream cannot be null");
		}

		if (bufferSize <= 0) {
			bufferSize = BUFFER_SIZE;
		}

		if (!USE_NIO) {
			try {
				byte[] bytes = new byte[bufferSize];

				int value = -1;

				while ((value = inputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0 , value);
				}
			}
			finally {
				cleanUp(inputStream, outputStream);
			}
		}
		else {
			ReadableByteChannel inputChannel = Channels.newChannel(
				inputStream);
			WritableByteChannel outputChannel = Channels.newChannel(
				outputStream);

			try {
				transfer(inputChannel, outputChannel);
			}
			finally {
				cleanUp(inputChannel, outputChannel);
			}
		}
	}

	public static void transfer(
			ReadableByteChannel inputChannel, WritableByteChannel outputChannel)
		throws IOException {

		ByteBuffer buffer = ByteBuffer.allocateDirect(BUFFER_SIZE);

		while (inputChannel.read(buffer) != -1) {
			buffer.flip();
			outputChannel.write(buffer);
			buffer.compact();
		}

		buffer.flip();

		while (buffer.hasRemaining()) {
			outputChannel.write(buffer);
		}
	}

	private static Log _log = LogFactoryUtil.getLog(StreamUtil.class);

}