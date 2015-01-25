package com.myapp.waste.mystory.Controllers;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.sql.rowset.serial.SerialException;

import com.myapp.waste.mystory.Service.*;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class UserActivityController {
	@Autowired
	RegisterDaoImpl registerDaoImpl;
	@Autowired
	CampaignDaoImpl campaignDaoImpl;

	@RequestMapping(value="/profile")
	public String profile(@RequestParam("userid") String username,Model model) throws SQLException{

		if(username.equals(null)) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			username = authentication.getName();
		}
		Profile userDetails = registerDaoImpl.userProfile(username);
		model.addAttribute("first_name",userDetails.getFirst_name());
		model.addAttribute("last_name",userDetails.getLast_name());
		model.addAttribute("usercity",userDetails.getCity());
		model.addAttribute("country",userDetails.getCountry());
		model.addAttribute("interest",userDetails.getInterest());
		model.addAttribute("score",userDetails.getScore());
		model.addAttribute("profilepicpath", userDetails.getProfilepicpath());
		model.addAttribute("userid", username);
		
		//Getting user campaings
		List<Campaign> myactivity = campaignDaoImpl.myactivity(username);
		List<Integer> idcampaign = new ArrayList<Integer>();
		List<String> host = new ArrayList<String>();
		List<String> heading = new ArrayList<String>();
		List<String> purpose = new ArrayList<String>();
		List<String> category = new ArrayList<String>();
		List<String> location = new ArrayList<String>();
		List<String> city = new ArrayList<String>();
		List<String> date = new ArrayList<String>();

		for(Campaign cmp : myactivity)
		{
			idcampaign.add(cmp.getIdcampaign());
			host.add(cmp.getUsername());
			heading.add(cmp.getHeading());
			purpose.add(cmp.getPurpose());
			category.add(cmp.getCategory());
			location.add(cmp.getLocation());
			city.add(cmp.getCity());
			date.add(cmp.getDate());
		}
		model.addAttribute("idcampaign",idcampaign);
		model.addAttribute("host", host);
		model.addAttribute("heading", heading);
		model.addAttribute("category",category);
		model.addAttribute("location", location);
		model.addAttribute("city", city);
		model.addAttribute("date", date);

		return "profile";
	}


	@RequestMapping(value="/joinCampaign")
	public String joincampaign(@RequestParam("idcampaign") int idcampaign){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		campaignDaoImpl.joincampaign(idcampaign, username);
		return "redirect:profile?userid="+username;
	}
	
	@RequestMapping(value="/mycampaigns")
	public String mycampaigns(Model model)
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		List<Campaign> mycampaigns = campaignDaoImpl.mycampaigns(username);
		List<Integer> idcampaign = new ArrayList<Integer>();
		List<String> host = new ArrayList<String>();
		List<String> heading = new ArrayList<String>();
		List<String> purpose = new ArrayList<String>();
		List<String> category = new ArrayList<String>();
		List<String> location = new ArrayList<String>();
		List<String> city = new ArrayList<String>();
		List<String> date = new ArrayList<String>();

		for(Campaign cmp : mycampaigns)
		{
			idcampaign.add(cmp.getIdcampaign());
			host.add(cmp.getUsername());
			heading.add(cmp.getHeading());
			purpose.add(cmp.getPurpose());
			category.add(cmp.getCategory());
			location.add(cmp.getLocation());
			city.add(cmp.getCity());
			date.add(cmp.getDate());
		}
		model.addAttribute("idcampaign",idcampaign);
		model.addAttribute("host", host);
		model.addAttribute("heading", heading);
		model.addAttribute("category",category);
		model.addAttribute("location", location);
		model.addAttribute("city", city);
		model.addAttribute("date", date);
		
		return "mycampaigns";
	}
	
	@RequestMapping(value="/uploadpic",method=RequestMethod.POST)
	public String uploadpic(@RequestParam("uploadFile") MultipartFile file) throws IOException, SerialException, SQLException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		String fileName=null;
		if(!file.isEmpty())
		{
			try{
				
				String ext = null;
				if(file.getOriginalFilename().lastIndexOf(".") != -1 && file.getOriginalFilename().lastIndexOf(".") != 0){
					ext =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				}
				fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + username+ext;
				File dir = new File("/home/gaurav/workspace/myhumanite/src/main/webapp/resources/profileimages");
				if(!dir.exists())
					dir.mkdirs();
				byte [] bytes = file.getBytes();

				//Create File on server
				File serverFile = new File(dir + File.separator + fileName);

				BufferedOutputStream stream = new BufferedOutputStream(
						new FileOutputStream(serverFile));
				stream.write(bytes);
				stream.close();
			}
			catch (Exception e) {   
				e.printStackTrace();
			}
		} 
		registerDaoImpl.uploadProfilePic(fileName,username);
		return "redirect:profile?userid="+username;
	}
	
	@RequestMapping(value="/updateProfile",method=RequestMethod.GET)
	public String updateProfile(@RequestParam("first_name") String first_name,
			@RequestParam("last_name") String last_name,@RequestParam("usercity") String usercity,
			@RequestParam("country") String country,@RequestParam("interest") String interest) throws SerialException, IOException, SQLException
	{
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		registerDaoImpl.updateUserProfile(first_name, username, last_name, usercity, country, interest);
		return "redirect:profile?userid="+username;
	}
}