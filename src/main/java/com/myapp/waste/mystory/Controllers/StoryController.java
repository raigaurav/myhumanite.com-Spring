package com.myapp.waste.mystory.Controllers;

import java.io.*;
import java.util.*;

import com.google.gson.*;
import com.myapp.waste.mystory.Service.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class StoryController {

	@Autowired
	PostDaoImpl postDaoImpl;
	int lastid;

	@RequestMapping(value="/stories")
	public String displayImage(Model model)
	{

		List<Story> stories = postDaoImpl.selectData(lastid);

		List<String> heading = new ArrayList<String>();
		List<String> location = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> story = new ArrayList<String>();
		List<String> picpath = new ArrayList<String>();
		List<Integer> likes = new ArrayList<Integer>();

		for(Story st : stories)
		{
			heading.add(st.getHeading());
			location.add(st.getLocation());
			name.add(st.getUsername());
			story.add(st.getStory());
			picpath.add(st.getPicpath());
			likes.add(st.getLike());
		}
		model.addAttribute("heading", heading);
		model.addAttribute("story",story);
		model.addAttribute("picPath", picpath);
		model.addAttribute("location", location);
		model.addAttribute("likes", likes);
		
		lastid = stories.get(stories.size()-1).getId();

		return "stories";
	}
	
	@RequestMapping(value="/mystory")
	public String mystory()
	{
		lastid = 0;
		return "mystory";
	}


	@RequestMapping(value="/newpost")
	public String newpost()
	{
		return "newpost";
	}


	@ResponseBody
	@RequestMapping(value="/getLocations",method=RequestMethod.GET)
	public String getLocations(@RequestParam("Value") String pattern)
	{ 
		String loc = postDaoImpl.getLocations(pattern);
		return loc;

	}

	@RequestMapping(value="/filteredpost",method=RequestMethod.GET)
	public String filterposts(@RequestParam("searchBox") String searchQuery,Model model)
	{	
		List<Story> stories = postDaoImpl.selectData(searchQuery);

		List<String> heading = new ArrayList<String>();
		List<String> location = new ArrayList<String>();
		List<String> name = new ArrayList<String>();
		List<String> story = new ArrayList<String>();
		List<String> picpath = new ArrayList<String>();

		for(Story st : stories)
		{
			heading.add(st.getHeading());
			location.add(st.getLocation());
			name.add(st.getUsername());
			story.add(st.getStory());
			picpath.add(st.getPicpath());
		}
		model.addAttribute("heading", heading);
		model.addAttribute("story",story);
		model.addAttribute("picPath", picpath);
		model.addAttribute("location", location);

		return "filteredpost";
	}

}
