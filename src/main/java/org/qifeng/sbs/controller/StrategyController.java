/**
 * 
 */
package org.qifeng.sbs.controller;

import java.util.List;

import org.qifeng.sbs.model.Strategy;
import org.qifeng.sbs.service.StrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author jackho
 *
 */
@Controller
@RequestMapping("/strategy")
public class StrategyController {
	
	private static Logger Log = LoggerFactory.getLogger(StrategyController.class);
	
	@Autowired
	private StrategyService strategyService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView listOfStrategies() {
		ModelAndView modelAndView = new ModelAndView("strategy-list");
		List<Strategy> strategies = strategyService.getStrategies();
		modelAndView.addObject("strategies",strategies);
		Log.info("listOfStrategies done");
		return modelAndView;
	}

	
}
