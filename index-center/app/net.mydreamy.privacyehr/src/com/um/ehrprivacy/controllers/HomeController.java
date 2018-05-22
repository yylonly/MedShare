package com.um.ehrprivacy.controllers;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.JWTVerifyException;
import com.um.ehrprivacy.utils.HandleLoginOperation;

/**
 * Handles requests for the application home page.
 */
@Controller
@SessionAttributes("userId")
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String homeTracker(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	@RequestMapping(value = "/tracker", method = RequestMethod.GET)
	public String tracker(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "tracker";
	}
	
	@RequestMapping(value = "/trackerlogin", method = RequestMethod.POST)
	public String trackerLogin(HttpServletRequest request, Model model, HttpSession session){
		
		String trackerid = request.getParameter("trackerid");
		String password = request.getParameter("password");
		// verify the username and password of tracker.
		if(HandleLoginOperation.verifyLegitimacyOfTracker(trackerid, password)){
			
			String userip = request.getParameter("userip");
			session.setAttribute("trackername", trackerid);
			session.setAttribute("trackerip", userip);
			
			model.addAttribute("trackerid",trackerid);
			
			return "trackerlog";
		}else {
			return "tracker";
		}
	}
	
	
	/**
	 *  Handle the login operation.
	 *  	1. Doctor login.
	 *  	2. Patient login.
	 *  
	 * @param username
	 * @param password
	 * @param model
	 * @return
	 * @throws JWTVerifyException 
	 * @throws IOException 
	 * @throws NoSuchAlgorithmException 
	 * @throws InvalidKeyException 
	 * @throws JoseException 
	 */
	@RequestMapping(value="login", method = RequestMethod.POST)
	public ModelAndView handleLogin1(HttpServletRequest request, HttpSession session) throws InvalidKeyException, NoSuchAlgorithmException, IOException, JWTVerifyException{
		
		// 1. Get doctor id and password from requests.
		String tokens = request.getParameter("tokens");
		// 2. Generate tokens based on the secret code.
		String CLIENT_SECRET = "PGD-EHR";
		try {
			
			if(tokens == null){
				return new ModelAndView("home").addObject("errormsg", "User name or password was wrong!");
			}
			
            Map<String,Object> decodedPayload = new JWTVerifier(CLIENT_SECRET,"doctor").verify(tokens);
            
            if(decodedPayload == null){
            	return new ModelAndView("home").addObject("errormsg", "User name or password was wrong!");
            }

            // Get custom fields from decoded Payload
            session.setAttribute("userId", decodedPayload.get("username"));
            session.setAttribute("clientip", decodedPayload.get("clientip"));
            return new ModelAndView("success");
            
        } catch (SignatureException signatureException) {
            System.err.println("Invalid signature!");
            return new ModelAndView("home").addObject("errormsg", "Invalid signature!");
        } catch (IllegalStateException illegalStateException) {
            System.err.println("Invalid Token! " + illegalStateException);
            return new ModelAndView("home").addObject("errormsg", "Invalid Token! " + illegalStateException);
        }
	}
	
	/* Logout */
	@RequestMapping(value="logout", method = RequestMethod.GET)
	public String handleLogout(Model model) {
		return "home";
	}
	
	/* Logout */
	@RequestMapping(value="trackerlogout", method = RequestMethod.GET)
	public String handletrackerLogout(Model model) {
		return "tracker";
	}
}
