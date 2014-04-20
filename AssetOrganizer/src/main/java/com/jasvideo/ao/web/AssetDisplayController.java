package com.jasvideo.ao.web;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jasvideo.ao.dao.AssetDAO;
import com.jasvideo.ao.model.AssetInfo;

@RestController
@RequestMapping("/assetsvc")
public class AssetDisplayController {
	
	
	@Value("${video.dir}")
	private String videoSaveDirectory;
	@Value("${thumb.dir}")
	private String thumbSaveDirectory;
	
	@Autowired
	private AssetDAO assetDAO;
	
	@RequestMapping("/video/category/{categoryId}/genre/{genreId}")
	public AssetInfo getAssets(@PathVariable Integer categoryId,@PathVariable Integer genreId){
		
		AssetInfo assetInfo = new AssetInfo();
		assetInfo.setVideoDir(videoSaveDirectory);
		assetInfo.setThumbDir(thumbSaveDirectory);
		
		if(genreId.equals(-1)){
			//category filter
			assetInfo.setVideos(assetDAO.getVideosForCategory(categoryId));
		}else if(categoryId.equals(-1)){
			// genre filter
			assetInfo.setVideos(assetDAO.getVideosForGenre(genreId));
			
		}else if(!genreId.equals(-1) && !categoryId.equals(-1)){
			//category genre filter
			assetInfo.setVideos(assetDAO.getVideosForCategoryGenre(categoryId, genreId));
		}
		
		return assetInfo;
		
	}
	
	@RequestMapping("/category")
	public Map getCategories(){
		return assetDAO.getCategoryMap();
	}
	@RequestMapping("/genre")
	public Map getGenres(){
		return assetDAO.getGenreMap();
	}
	
	

}
