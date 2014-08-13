/**
 * 
 */
package org.qifeng.sbs.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author jackho
 *
 */
@Controller
public class ErrorController {
	
	private static Logger LOG = LoggerFactory.getLogger(ErrorController.class);
	
	@RequestMapping(value = {"/error/accessDeniedPage"}, method = RequestMethod.GET)
	public String sendTo403(Model model) {
		LOG.debug("RequestMapping: /error/403-GET");
		return "error/403";
	}
}
