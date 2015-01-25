package com.myapp.waste.mystory.Controllers;

import java.io.*;
import java.util.*;
import com.myapp.waste.mystory.Service.Campaign;
import com.myapp.waste.mystory.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VolunteerController {

	@Autowired
	CampaignDaoImpl campaignDaoImpl;

	@RequestMapping(value="/campaignsearch",method=RequestMethod.GET)
	public String campaignsearch(@RequestParam("searchbar")String query,Model model){
		List<Campaign> campaigns = campaignDaoImpl.selectCampaigns(query);
		if(campaigns.isEmpty()){
			model.addAttribute("emptymessage","No results for your search");
		}
		else
		{
			List<Integer> idcampaign = new ArrayList<Integer>();
			List<String> username = new ArrayList<String>();
			List<String> heading = new ArrayList<String>();
			List<String> purpose = new ArrayList<String>();
			List<String> category = new ArrayList<String>();
			List<String> location = new ArrayList<String>();
			List<String> city = new ArrayList<String>();
			List<String> date = new ArrayList<String>();

			for(Campaign cmp : campaigns)
			{
				idcampaign.add(cmp.getIdcampaign());
				username.add(cmp.getUsername());
				heading.add(cmp.getHeading());
				purpose.add(cmp.getPurpose());
				category.add(cmp.getCategory());
				location.add(cmp.getLocation());
				city.add(cmp.getCity());
				date.add(cmp.getDate());
			}
			model.addAttribute("idcampaign",idcampaign);
			model.addAttribute("host", username);
			model.addAttribute("heading", heading);
			model.addAttribute("category",category);
			model.addAttribute("location", location);
			model.addAttribute("city", city);
			model.addAttribute("date", date);
		}
		return "campaignsearch";
	}	

	@RequestMapping(value="/volunteer")
	public String volunteer(Model model){

		return "volunteer";
	}

	@RequestMapping(value="/newcampaign")
	public String newcampaign(){
		return "newcampaign";
	}

	@RequestMapping(value="/campaign-details")
	public String campaigndetails(@RequestParam("idcampaign") int id,Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		
		int check = campaignDaoImpl.checkIfJoined(id, username);
		model.addAttribute("check",check);
		
		Campaign cmp = campaignDaoImpl.getCampaignDetails(id);
		model.addAttribute("idcampaign",cmp.getIdcampaign());
		model.addAttribute("heading", cmp.getHeading());
		model.addAttribute("host", cmp.getUsername());
		model.addAttribute("purpose", cmp.getPurpose());
		model.addAttribute("category", cmp.getCategory());
		model.addAttribute("city", cmp.getCity());
		model.addAttribute("location", cmp.getLocation());
		model.addAttribute("date", cmp.getDate());
		
		if(username.equals(cmp.getUsername()))
		{
		List<String> volunteerList = campaignDaoImpl.getVolunteers(id);
		model.addAttribute("volunteerList",volunteerList);
		}
		return "campaign-details";
		
	}

	@RequestMapping(value="/saveCampaign")
	public String savecampaign(@RequestParam("heading") String heading,
			@RequestParam("purpose") String purpose,@RequestParam("category") String category,
			@RequestParam("location") String location,@RequestParam("city") String city,
			@RequestParam("date") String date) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		Campaign cmp = new Campaign(username, heading, purpose, category, location, city, date);
		campaignDaoImpl.insertCampaign(cmp);
		return "redirect:volunteer";
	}

}
