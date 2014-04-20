package com.jasvideo.ao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.jasvideo.ao.model.VideoDisplayInfo;



public class VideoInfoRowMapper implements RowMapper<VideoDisplayInfo> {

	@Override
	public VideoDisplayInfo mapRow(ResultSet rs, int count) throws SQLException {
		VideoDisplayInfo videoInfo  = new VideoDisplayInfo();
		videoInfo.setVideoName(rs.getString("video_name"));
		videoInfo.setVideoFileName(rs.getString("file_name"));
		videoInfo.setThumbFileName(rs.getString("thumbnail_name"));
		videoInfo.setGenreName(rs.getString("genre_name"));
		videoInfo.setCategoryName(rs.getString("category_name"));
		return videoInfo;
	}

}
