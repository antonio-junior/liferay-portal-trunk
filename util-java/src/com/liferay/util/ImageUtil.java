/**
 * Copyright (c) 2000-2006 Liferay, Inc. All rights reserved.
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

package com.liferay.util;

import com.tjtieto.wap.wapix.WBMPMaster;

import java.awt.Color;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import net.jmge.gif.Gif89Encoder;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * <a href="ImageUtil.java.html"><b><i>View Source</i></b></a>
 *
 * @author  Brian Wing Shun Chan
 *
 */
public class ImageUtil {

	public static void encodeGIF(BufferedImage image, OutputStream out)
		throws IOException {

		Gif89Encoder encoder = new Gif89Encoder(image);

		encoder.encode(out);
	}

	public static void encodeWBMP(BufferedImage image, OutputStream out)
		throws InterruptedException, IOException {

		WBMPMaster wbmpMaster = new WBMPMaster();

		int height = image.getHeight();
		int width = image.getWidth();

		int[] pixels = wbmpMaster.grabPixels(image);
		pixels = WBMPMaster.processPixels(
			1, pixels, width, height, 128, Color.white, false);

		WBMPMaster.encodePixels(out, pixels, width, height);
	}

	public static byte[] read(ClassLoader classLoader, String name) {
		return read(classLoader.getResourceAsStream(name));
	}

	public static byte[] read(InputStream is) {
		byte[] byteArray = null;

		if (is == null) {
			return byteArray;
		}

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		try {
			byte[] bytes = new byte[512];

			for (int i = is.read(bytes, 0, 512); i != -1;
					i = is.read(bytes, 0, 512)) {

				baos.write(bytes, 0, i);
			}

			byteArray = baos.toByteArray();
		}
		catch (Exception e) {
			_log.warn(e);
		}
		finally {
			try {
				is.close();
			}
			catch (Exception e) {
			}

			try {
				baos.close();
			}
			catch (Exception e) {
			}
		}

		return byteArray;
	}

	public static BufferedImage scale(BufferedImage image, double factor) {
		AffineTransformOp op = new AffineTransformOp(
			AffineTransform.getScaleInstance(
				factor, factor), null);

		return op.filter(image, null);
	}

	public static BufferedImage scale(
		BufferedImage image, int maxHeight, int maxWidth) {

		int imageHeight = image.getHeight();
		int imageWidth = image.getWidth();

		if (maxHeight == 0) {
			maxHeight = imageHeight;
		}

		if (maxWidth == 0) {
			maxWidth = imageWidth;
		}

		if ((imageHeight <= maxHeight) && (imageWidth <= maxWidth)) {
			return image;
		}

		double factor = 0.1;

		int heightDelta = imageHeight - maxHeight;
		int widthDelta = imageWidth - maxWidth;

		if (heightDelta > widthDelta) {
			factor = (double)maxHeight / imageHeight;
		}
		else {
			factor = (double)maxWidth / imageWidth;
		}

		return scale(image, factor);
	}

	private static Log _log = LogFactory.getLog(ImageUtil.class);

}