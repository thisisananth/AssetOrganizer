package com.jasvideo.ao.web;

import java.util.Iterator;
import java.util.List;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.jasvideo.ao.model.EnrichForm;
import com.jasvideo.ao.model.VideoInfo;

public class EnrichValidator implements Validator {

	@Override
	public boolean supports(Class<?> arg0) {
		return EnrichForm.class.equals(arg0);
	}

	@Override
	public void validate(Object form, Errors errors) {
		EnrichForm enrichForm = (EnrichForm) form;		
		List<VideoInfo> videos =enrichForm.getVideos();
		Iterator<VideoInfo> videoIter = videos.iterator();
		boolean videoNameError = false;
		boolean categoryError = false;
		boolean genreError = false;
		while(videoIter.hasNext()){
			VideoInfo video = videoIter.next();
			if(!videoNameError && (video.getVideoName()==null||video.getVideoName().trim().equals(""))){
				errors.reject("errors.videoName.null", "Please enter a value for videoName");
				videoNameError = true;
			}
			if(!categoryError &&(video.getCategoryId()==null||video.getCategoryId().equals(-1))){
				errors.reject("errors.categoryId.null", "Please select a category");
				categoryError = true;
			}
			if(!genreError && (video.getGenreId()==null||video.getGenreId().equals(-1))){
				errors.reject("errors.genreId.null", "Please select a genre");
				genreError = true;
			}
			
		}
	}

}
