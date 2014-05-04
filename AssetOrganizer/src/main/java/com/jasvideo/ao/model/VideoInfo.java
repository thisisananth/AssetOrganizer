package com.jasvideo.ao.model;

import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

public class VideoInfo {

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getVideoName() {
		return videoName;
	}

	public void setVideoName(String videoName) {
		this.videoName = videoName;
	}

	public String getThumbName() {
		return thumbName;
	}

	public void setThumbName(String thumbName) {
		this.thumbName = thumbName;
	}

	private String fileName;

	private String videoName;
	private String thumbName;

	private Integer categoryId;
	
	private UUID videoUUID;

	public UUID getVideoUUID() {
		return videoUUID;
	}

	public void setVideoUUID(UUID videoUUID) {
		this.videoUUID = videoUUID;
	}

	public Integer getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Integer categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getGenreId() {
		return genreId;
	}

	public void setGenreId(Integer genreId) {
		this.genreId = genreId;
	}

	private Integer genreId;
	
	private MultipartFile thumbnail;

	public MultipartFile getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(MultipartFile thumbnail) {
		this.thumbnail = thumbnail;
	}

}
