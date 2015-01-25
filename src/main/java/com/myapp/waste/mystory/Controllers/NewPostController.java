package com.myapp.waste.mystory.Controllers;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import com.myapp.waste.mystory.Service.*;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
public class NewPostController {

	@Autowired
	PostDaoImpl postDaoImpl;

	@RequestMapping(value="/uploadImage",method= RequestMethod.POST)
	@ResponseBody
	public void saveImage(HttpServletRequest request,@RequestParam("file") MultipartFile file) {

		if(!file.isEmpty())
		{
			try{
				Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
				String username = authentication.getName();
				String ext = null;
				if(file.getOriginalFilename().lastIndexOf(".") != -1 && file.getOriginalFilename().lastIndexOf(".") != 0){
					ext =  file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
				}
				String fileName = new SimpleDateFormat("yyyyMMddhhmm").format(new Date()) + username+ext;
				File dir = new File("/home/gaurav/workspace/myhumanite/src/main/webapp/resources/temp");
				if(!dir.exists())
					dir.mkdirs();
				try {
					// Cleaning temp directory so that it have only one image
					FileUtils.cleanDirectory(dir);
				} catch (IOException e) {
					e.printStackTrace();
				} 

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
	}

	@RequestMapping(value="/savepost",method= RequestMethod.POST)
	public String savepost(@RequestParam("heading") String heading,@RequestParam("location") String location,
			@RequestParam("story") String story,@RequestParam("type") String type)
	{

		File dir1 = new File("/home/gaurav/workspace/myhumanite/src/main/webapp/resources/temp");

		// Getting name of image in temp directory
		File img[] = dir1.listFiles();
		String picpath = img[0].getName();
		File dir2 = new File("/home/gaurav/workspace/myhumanite/src/main/webapp/resources/images");
		try {
			FileUtils.copyDirectory(dir1, dir2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();
		int likes = 0;
		Story st = new Story(heading,location,username,picpath,story,type,likes);
		postDaoImpl.insertPost(st);
		return "redirect:mystory"; 		
	}
}
