package com.jasvideo.ao.web;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.EnrichForm;
import com.jasvideo.ao.model.VideoInfo;

@Controller
public class PublishController {
	
	private static final Logger log = LoggerFactory
			.getLogger(PublishController.class);
	@Autowired
	private AssetDAO assetDAO;
	
	@RequestMapping(value = "/publish", method = RequestMethod.POST)
	public String publish(@ModelAttribute("enrichForm") EnrichForm enrichForm,
			BindingResult result, Model model){
		
		List<VideoInfo> videos = enrichForm.getVideos();
		
		for(VideoInfo videoInfo:videos){
			
			log.info("FileName:"+ videoInfo.getFileName());
			assetDAO.saveVideoInfo(videoInfo);
		}
		
		
		return "publish_success";
	}
	
	@RequestMapping(value = "/show", method = RequestMethod.GET)
	public String getVideos(){
		return "play_video";
		
	}

}