package com.capstone.progettofinale.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.capstone.progettofinale.ProgettofinaleApplication;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public abstract class AbstractService {

	private String pathSegment;

	public AbstractService(String s) {
		this.pathSegment = s;
	}


	@Async
	public void restartApp() {
		ProgettofinaleApplication.restart();
	}

	@Async
	protected void storeImg(String url, String filePath) {
		try {
			FileUtils.copyURLToFile(new URL(url),
					new File("target/classes/static/imgs/" + pathSegment + "/" + filePath),
					4000, 4000);
			log.info("immagine salvata correttamente: " + filePath);

		} catch (IOException e) {
			log.error("salvataggio immagine: " + e.getMessage(), e);
		}
	}

	protected String getSegment() {
		return this.pathSegment + "/";
	}

	protected String getImagePath(String path) {
		try {
			return java.net.URLEncoder.encode(path, "UTF-8").replace("+", "%20");
		} catch (UnsupportedEncodingException e) {
			log.error("errore : " + e.getMessage(), e);
			return path;
		}
	}

	public byte[] getImageSource(String img) throws IOException {
		InputStream in = getClass().getResourceAsStream("/static/imgs/" + getSegment() + img);
		return IOUtils.toByteArray(in);
	}

}
