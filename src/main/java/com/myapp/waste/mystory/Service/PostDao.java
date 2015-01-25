package com.myapp.waste.mystory.Service;

import java.util.List;

public interface PostDao {

	public int insertPost(Story story);

	public List<Story> selectData(int lastid);

	public List<Story> selectData(String searchQuery) ;
	
	String getLocations(String pattern);
}
