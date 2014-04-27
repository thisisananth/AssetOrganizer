package com.jasvideo.ao.web;

import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.EnrichForm;
import com.jasvideo.ao.model.VideoInfo;
@Controller
public class EnrichController {
	
	private static final Logger log = LoggerFactory
			.getLogger(EnrichController.class);
	@Autowired
	private AssetDAO assetDAO;
	
	@InitBinder("enrichForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(new EnrichValidator());
	}
	
	@RequestMapping(value = "/enrich", method = RequestMethod.POST)
	public String save(@ModelAttribute("enrichForm") @Valid EnrichForm enrichForm,
			BindingResult result, Model model){
		
		Map<Integer, String> categories = assetDAO.getCategoryMap();
		Map<Integer, String> genres = assetDAO.getGenreMap();
		
		
				// Check for validation error
				if (result.hasErrors()) {
					
					model.addAttribute("categories", categories);
					model.addAttribute("genres", genres);

					return "enrich_page_new";
				}
				
		
				
		
		model.addAttribute("enrichForm", enrichForm);
		log.info("No of videos in the form:"+enrichForm.getVideos().size());
		model.addAttribute("categories", categories);
		model.addAttribute("genres", genres);

		
		for(VideoInfo video : enrichForm.getVideos()){
			log.info("Vidoe Filename:"+video.getFileName());
			log.info("Vidoe name:"+video.getVideoName());
		}
		
		return "publish_page_new";
	}

}
