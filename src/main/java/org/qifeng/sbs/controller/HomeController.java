/**
 * 
 */
package org.qifeng.sbs.controller;

import org.qifeng.sbs.bean.TestBean;
import org.qifeng.sbs.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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
	
	@RequestMapping("/")
    public ModelAndView index() {
    	return new ModelAndView("home");
    }
	@RequestMapping("/getParam")
	public String getParam(@RequestParam(value="id",required=true) Integer id) {
		
		System.out.println("id : "+id);
		
		return "home";
	}
}
