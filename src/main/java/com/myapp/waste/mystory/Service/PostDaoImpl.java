package com.myapp.waste.mystory.Service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import com.google.gson.*;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

public class PostDaoImpl implements PostDao {
	private JdbcTemplate jdbcTemplate;

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	
	public int insertPost(Story st) {

		String smt = "Insert into Story(heading,location,username,picpath,story,type,likes) Values(?,?,?,?,?,?,?)";
		return jdbcTemplate.update(smt,new Object[]{st.getHeading(),st.getLocation(),st.getUsername(),st.getPicpath(),st.getStory(),st.getType(),st.getLike()});
	}

	public List<Story> selectData(int lastid) {
		String smt = "Select * from Story WHERE id >'"+lastid+"' LIMIT 2";
		List<Story> stories = this.jdbcTemplate.query(smt, new RowMapper<Story>(){
			public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
				Story story = new Story(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getInt(8));
				return story;
			}
		});

		return stories;
	}
	
	public List<Story> selectData(String searchQuery) {
		String smt = "Select * from Story where location = '"+searchQuery+"'";
		List<Story> stories = this.jdbcTemplate.query(smt, new RowMapper<Story>(){
			public Story mapRow(ResultSet rs, int rowNum) throws SQLException {
				Story story = new Story(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),
						rs.getString(6),rs.getString(7),rs.getInt(8));
				return story;
			}
		});

		return stories;
	}
	
	public String getLocations(String pattern)
	{
		String smt = "Select DISTINCT(location) from Story";
		List<String> loc = this.jdbcTemplate.query(smt, new RowMapper<String>(){
			@Override
			public String mapRow(ResultSet rs, int arg1) throws SQLException {
				String tmp = rs.getString(1);
				return tmp;
			}
		});
		List<String> locFiltered = new ArrayList<String>();
		for(String l : loc)
		{
			if(l.toLowerCase().startsWith(pattern.toLowerCase()))
				locFiltered.add(l);
		}
		Gson gson = new Gson();
		String locJson = gson.toJson(locFiltered);
			return locJson;
	}

}
