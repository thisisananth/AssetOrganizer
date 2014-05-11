package com.jasvideo.ao.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.VideoDisplayInfo;
@Controller
public class PlayerController {
	
	@Autowired
	private AssetDAO assetDAO;
	
	Logger log = LoggerFactory.getLogger(PlayerController.class);
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String getVideos() {
		return "play_video";

	}

	@RequestMapping(value = "/preview", method = RequestMethod.GET)
	public String getVideos(@RequestParam("id") String uuid, Model model) {

		VideoDisplayInfo videoInfo = assetDAO.getVideoByUUID(uuid);

		model.addAttribute("videoName", videoInfo.getVideoFileName());
		String fileName = videoInfo.getVideoFileName();
		log.info("File Name:" + fileName);

		String[] fileNames = fileName.split("\\.");
		log.info("FileNames size:" + fileNames.length);

		String ext = "";
		if (fileNames.length > 0) {
			ext = fileNames[fileNames.length - 1];

		}
		log.info("ext:" + ext);
		model.addAttribute("ext", ext);
		model.addAttribute("video",videoInfo);
		model.addAttribute("playerName","VG Player");
		
		
		
		return "play_video";

	}

}
