package com.myapp.waste;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myapp.waste.mystory.Service.Register;
import com.myapp.waste.mystory.Service.RegisterDaoImpl;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@Autowired
	RegisterDaoImpl registerDaoImpl;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = {"/","/home"}, method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	@RequestMapping(value="/register")
	public String register(){
		return "register";
	}
	
	//Registration of new user
	@ResponseBody
	@RequestMapping(value="/newuser",method = RequestMethod.POST)
	public String newuser(@RequestParam("first_name") String first_name,@RequestParam("last_name") String last_name,
			@RequestParam("email")String username,@RequestParam("password")String password){
		Register reg = new Register(username,first_name,last_name,md5(password),1);
		registerDaoImpl.registration(reg);
		return "success";
	}
	
	//converting password into md5 format
	public static String md5(String input) {
        
        String md5 = null;
         
        if(null == input) return null;
         
        try {
             
        //Create MessageDigest object for MD5
        MessageDigest digest = MessageDigest.getInstance("MD5");
         
        //Update input string in message digest
        digest.update(input.getBytes(), 0, input.length());
 
        //Converts message digest value in base 16 (hex)
        md5 = new BigInteger(1, digest.digest()).toString(16);
 
        } catch (NoSuchAlgorithmException e) {
 
            e.printStackTrace();
        }
        return md5;
    }
	
	@ResponseBody
	@RequestMapping(value="/userExists",method = RequestMethod.GET)
	public boolean userExists(@RequestParam("email") String username){
		String msg;
		boolean check = registerDaoImpl.userExists(username);
		return check;
	}
}
