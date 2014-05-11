package com.jasvideo.ao.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.jasvideo.ao.model.VideoDisplayInfo;
import com.jasvideo.ao.model.VideoInfo;

@Component
public class AssetDAO {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Map<Integer, String> getCategoryMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();

		String query = "select id,category_name as name from category";
		map = jdbcTemplate.query(query, new CategoryGenreResultSetExtractor());
		return map;

	}

	public Map<Integer, String> getGenreMap() {
		Map<Integer, String> map = new HashMap<Integer, String>();

		String query = "select id,genre_name as name from genre";
		map = jdbcTemplate.query(query, new CategoryGenreResultSetExtractor());
		return map;

	}

	public void saveVideoInfo(VideoInfo videoInfo) {

		String query = "insert into video_info(video_name,file_name,thumbnail_name,category_id,genre_id,created_time,uuid,player_style)"
				+ "values(?,?,?,?,?,localtimestamp,?,?)";

		jdbcTemplate.update(
				query,
				new Object[] { videoInfo.getVideoName(),
						videoInfo.getFileName(), videoInfo.getThumbName(),
						videoInfo.getCategoryId(), videoInfo.getGenreId(),videoInfo.getVideoUUID(),videoInfo.getStyle() });

	}
	
	public VideoDisplayInfo getVideoByUUID(String uuid){
		String query = "select video_name,file_name,thumbnail_name,genre_name,category_name,player_style style from video_info vid,genre gen, category cat where vid.genre_id = gen.id AND vid.category_id=cat.id"+
				" AND vid.uuid =?  ";
		List videos = jdbcTemplate.query(query, new Object[] { uuid },
				new VideoInfoRowMapper());
		
		
		
		if(videos!=null && videos.size() > 0){
			return (VideoDisplayInfo) videos.get(0);
			
		}
		else{
			return null;
		}
		
	}

	public List<VideoDisplayInfo> getVideosForCategory(Integer categoryId) {

		String query = "select video_name,file_name,thumbnail_name,genre_name,category_name from video_info vid,genre gen, category cat where vid.genre_id = gen.id AND vid.category_id=cat.id"
				+ " AND vid.category_id=?  ";

		List videos = jdbcTemplate.query(query, new Object[] { categoryId },
				new VideoInfoRowMapper());
		return videos;
	}

	public List<VideoDisplayInfo> getVideosForGenre(Integer genreId) {

		String query = "select video_name,file_name,thumbnail_name,genre_name,category_name from video_info vid,genre gen, category cat where vid.genre_id = gen.id AND vid.category_id=cat.id"
				+ " AND vid.genre_id=?  ";

		List videos = jdbcTemplate.query(query, new Object[] { genreId },
				new VideoInfoRowMapper());
		return videos;
	}

	public List<VideoDisplayInfo> getVideosForCategoryGenre(Integer categoryId,Integer genreId) {

		String query = "select video_name,file_name,thumbnail_name,genre_name,category_name from video_info vid,genre gen, category cat where vid.genre_id = gen.id AND vid.category_id=cat.id"
				+ " AND vid.category_id=? AND vid.genre_id=? ";

		List videos = jdbcTemplate.query(query, new Object[] { categoryId,genreId },
				new VideoInfoRowMapper());
		return videos;
	}

}
