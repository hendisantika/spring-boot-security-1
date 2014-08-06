/**
 * 
 */
package org.qifeng.sbs.controller;

import java.util.List;

import org.qifeng.sbs.model.Strategy;
import org.qifeng.sbs.service.StrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
	
	@Autowired
	private StrategyService strategyService;
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public ModelAndView listOfStrategies() {
		ModelAndView modelAndView = new ModelAndView("strategy-list");
		List<Strategy> strategies = strategyService.getStrategies();
		modelAndView.addObject("strategies",strategies);
		return modelAndView;
	}
//	@RequestMapping(value="/list",  method=RequestMethod.GET)
//	public String listOfStrategies(Model model) {
//
//		List<Strategy> strategies = strategyService.getStrategies();
//		model.addAttribute("strategies", strategies);
//
//		if(! model.containsAttribute("strategy")) {
//			Strategy strategy = new Strategy();
//			model.addAttribute("strategy", strategy);
//		}
//		return "strategy-list";
//	}

	
}
