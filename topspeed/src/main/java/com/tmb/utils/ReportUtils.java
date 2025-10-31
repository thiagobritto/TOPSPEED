package com.tmb.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;

public class ReportUtils {

	private static BufferedImage logoCache;

	private ReportUtils() {
		// static
	}

	public static BufferedImage loadLogo() {
		if (logoCache != null) {
			return logoCache;
		}

		try (InputStream logoStream = ReportUtils.class.getResourceAsStream("/images/logos/logo.png")) {
			logoCache = ImageIO.read(logoStream);
			return logoCache;
		} catch (IOException e) {
			throw new RuntimeException("Erro ao carregar logo do relatório.", e);
		}

	}
}
