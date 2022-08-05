package com.common;

import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.Locale;

import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartFile;

public class Utility {

	public static final String GMT = "GMT";

	public static final String COMMA = ",";

	public static final Locale VN_LOCALE = new Locale("vi", "vn");

	private static final long IMAGE_MAX_SIZE = 3 * 1024 * 1024;

	public static final int ZERO = 0;

	public static final String IMAGE_EXTENSION = "png";

	public static boolean isNull(Object obj) {
		return obj == null;
	}

	public static ZonedDateTime getZoneDateTime(String zone) {
		return ZonedDateTime.of(getLocalDateTime(), ZoneId.of(zone));
	}

	public static LocalDateTime getLocalDateTime() {
		return LocalDateTime.now();
	}

	public static Timestamp getGMTTime() {
		return Timestamp.valueOf(getZoneDateTime(GMT).toLocalDateTime());
	}

	public static int toOffset(int page) {
		return (page == 0) ? 0 : page + 1;
	}

	public static boolean isEmpty(String string) {

		if (isNull(string)) {
			return true;
		}

		if (string.trim().isEmpty()) {
			return true;
		}

		return false;
	}
	
	public static boolean isEmpty(Object arr[]) {

        if(isNull(arr)){
            return true;
        }

		if (arr.length <= 0) {
			return true;
		}

		return false;
	}

	public static String getFileExtension(File file) {
		String name = file.getName();
		int lastIndexOf = name.lastIndexOf(".");
		if (lastIndexOf == -1) {
			return "";
		}
		return name.substring(lastIndexOf);
	}

	public static boolean isImageFile(MultipartFile file) {

		if (file.getSize() > IMAGE_MAX_SIZE) {
			return false;
		}
		
		if (! file.getContentType().contains("image")) {
			return false;
		}

		return true;
	}

	public static void close(Closeable object) {
		if (!isNull(object)) {
			try {
				object.close();
			} catch (IOException e) {

			}
		}
	}

	public static void copyFile(File sourceFile, URI filePath) throws IOException {

		if (!sourceFile.canRead()) {
			throw new IOException();
		}

		File copyFile = new File(filePath);

		if (!copyFile.exists()) {

			File parentFolders = new File(copyFile.getParent());

			if (!parentFolders.exists()) {
				parentFolders.mkdirs();
			}

			copyFile.createNewFile();
		}

		FileInputStream sourceFileStream = new FileInputStream(sourceFile);
		FileOutputStream copyFileStream = new FileOutputStream(copyFile);

		byte[] bytes = new byte[1024];

		while (sourceFileStream.read(bytes) != -1) {
			copyFileStream.write(bytes);
		}

		close(copyFileStream);
		close(sourceFileStream);
	}

	public static void copyFileByFilePath(File sourceFile, URI filePath)
			throws IOException, MaxUploadSizeExceededException {
		if (sourceFile.getPath().length() > IMAGE_MAX_SIZE) {
			throw new MaxUploadSizeExceededException(sourceFile.getPath().length());
		}

		String base64 = sourceFile.getPath().toString().split(",")[1];

		byte[] imageBytes = Base64.getDecoder().decode(base64.getBytes(StandardCharsets.UTF_8));


		File copyFileStream = new File(filePath);
		
		OutputStream imageStream = new BufferedOutputStream(new FileOutputStream(copyFileStream));
		imageStream.write(imageBytes);
		imageStream.close();
		
	}
}
