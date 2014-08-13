/**
 * 
 */
package org.qifeng.sbs.controller;

import java.util.Collection;
import java.util.HashSet;

import org.qifeng.sbs.bean.TestBean;
import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
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
	
	@RequestMapping(value = "/")
    public String mainPage() {
        Collection<GrantedAuthority> authorities = getAuthorities();
        String rolename;
        
        for (GrantedAuthority authority : authorities) {
            rolename = authority.getAuthority();
            
            if (rolename.equals("ROLE_ADMIN")) {
                LOG.debug("Directing to home page for: [" + rolename + "]");
                return "home-admin";
            }
            if (rolename.equals("ROLE_TRADER")) {
            	LOG.debug("Directing to home page for: [" + rolename + "]");
                return "home-trader";
            }
            if (rolename.equals("ROLE_USER")) {
            	LOG.debug("Directing to home page for: [" + rolename + "]");
                return "home-user";
            }
        }
        
        LOG.error("Role not found - directing to home page for ROLE_USER");
        return "home-user";
    }

    @RequestMapping(value = "/index")
    public String indexPage() {
        return "redirect:/";
    }
    
    private Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof User) {
            authorities = ((User) principal).getAuthorities();
        } else {
        	LOG.error("Principal is not an instance of com.dtr.oas.model.User");
        }
        return authorities;
    }
	
	
	@RequestMapping("/getParam")
	public String getParam(@RequestParam(value="id",required=true) Integer id) {
		
		System.out.println("id : "+id);
		
		return "home";
	}
			
}
