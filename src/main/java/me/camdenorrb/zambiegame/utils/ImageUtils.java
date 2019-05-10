package me.camdenorrb.zambiegame.utils;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;


public final class ImageUtils {

	private ImageUtils() {}


	public static BufferedImage load(File file) {
		try {
			return ImageIO.read(file);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static BufferedImage load(InputStream inputStream) {
		try {
			return ImageIO.read(inputStream);
		}
		catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}


}
