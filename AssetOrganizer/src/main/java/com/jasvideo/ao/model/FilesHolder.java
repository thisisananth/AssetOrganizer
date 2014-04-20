package com.jasvideo.ao.model;

import org.springframework.web.multipart.MultipartFile;

public class FilesHolder {
	
	private MultipartFile videoFile;
	public MultipartFile getVideoFile() {
		return videoFile;
	}
	public void setVideoFile(MultipartFile videoFile) {
		this.videoFile = videoFile;
	}
	public MultipartFile getThumbnail() {
		return thumbnail;
	}
	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}
	private MultipartFile thumbnail;

}
