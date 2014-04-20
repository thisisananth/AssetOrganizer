package com.jasvideo.ao.model;

import java.util.List;

public class AssetInfo {

	private String videoDir;

	public String getVideoDir() {
		return videoDir;
	}

	public void setVideoDir(String videoDir) {
		this.videoDir = videoDir;
	}

	public String getThumbDir() {
		return thumbDir;
	}

	public void setThumbDir(String thumbDir) {
		this.thumbDir = thumbDir;
	}

	
	private String thumbDir;
	private List<VideoDisplayInfo> videos;

	public List<VideoDisplayInfo> getVideos() {
		return videos;
	}

	public void setVideos(List<VideoDisplayInfo> videos) {
		this.videos = videos;
	}

}
