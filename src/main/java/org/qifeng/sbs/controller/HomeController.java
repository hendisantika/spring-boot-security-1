/**
 * 
 */
package org.qifeng.sbs.controller;

import org.qifeng.sbs.bean.TestBean;
import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jackho
 *
 */
@Controller
public class HomeController {
	
	private static Logger LOG = LoggerFactory.getLogger(HomeController.class);
	
//	@RequestMapping("/")
//    public ModelAndView index() {
//    	return new ModelAndView("home-admin");
//    }
	@RequestMapping(value="/")
	String mainPage() {
		String roleName = getRole();
		LOG.debug("Directing to home page for: ["+ roleName +"]   ");
		
		if (roleName.equals("ROLE_ADMIN")) {
			return "home-admin";
		} else if (roleName.equals("ROLE_TRADER")){
			return "home-trader";
		} else {
			return "home-user";
		}
	}
	
	private String getRole() {
		String roleName = "";
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof User) {
			roleName = ((User) principal).getRole().getRolename();
		} else {
			LOG.error("Principal is not an instance of org.qifeng.sbs.model.User");
		}
		return roleName;
	}
	
	@RequestMapping("/getParam")
	public String getParam(@RequestParam(value="id",required=true) Integer id) {
		
		System.out.println("id : "+id);
		
		return "home";
	}
			
}
