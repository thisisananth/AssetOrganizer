package com.jasvideo.ao.web;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.VideoDisplayInfo;

@RestController
public class VideoController {
	Logger log = LoggerFactory.getLogger(VideoController.class);

	@Value("${video.dir}")
	private String videoSaveDirectory;

	@Value("${thumb.dir}")
	private String thumbSaveDirectory;

	@Autowired
	private AssetDAO assetDAO;

	@RequestMapping(value = "/video/{fileName}.{ext}", method = RequestMethod.GET)
	public void getVideo(@PathVariable String fileName,
			@PathVariable String ext, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// logic for getting path to media on server
		log.info("File Name is:" + fileName);
		log.info("Extension is:" + ext);
		File file = new File(videoSaveDirectory + fileName + "." + ext);
		response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
		response.setHeader("Content-Disposition", "attachment; filename="
				+ file.getName().replace(" ", "_"));
		InputStream iStream = new FileInputStream(file);
		IOUtils.copy(iStream, response.getOutputStream());
		response.flushBuffer();

	}

	@RequestMapping(value = "/image/{fileName}.{ext}", method = RequestMethod.GET)
	public void getImage(@PathVariable String fileName,
			@PathVariable String ext, HttpServletRequest request,
			HttpServletResponse response) throws IOException {

		// logic for getting path to media on server
		log.info("File Name is:" + fileName);
		log.info("Extension is:" + ext);

		BufferedImage image = ImageIO.read(new File(thumbSaveDirectory
				+ fileName + "." + ext));
		ImageIO.write(image, ext, response.getOutputStream());

	}

	
}
