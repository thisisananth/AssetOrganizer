package com.jasvideo.ao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

public class CategoryGenreResultSetExtractor implements ResultSetExtractor<Map<Integer, String>> {

	@Override
	public Map<Integer, String> extractData(ResultSet rs)
			throws SQLException, DataAccessException {
		Map<Integer,String> hashMap = new HashMap<Integer, String>();
		while(rs.next()){
			hashMap.put(rs.getInt("id"), rs.getString("name"));
		}
		
		
		return hashMap;
	}

	
}
