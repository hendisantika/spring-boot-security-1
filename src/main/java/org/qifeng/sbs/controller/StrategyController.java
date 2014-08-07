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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
	//list strategies
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String listOfStrategies(Model model) {
		Log.info("RequestMapping : strategy/list-GET");
		
		List<Strategy> strategies = strategyService.getStrategies();
		model.addAttribute("strategies",strategies);
		
		//strategy object for "add strategy" passing between controller and html 
		if(! model.containsAttribute("strategy")) {
			Log.info("Adding strategy object to model");
			Strategy strategy = new Strategy();
			model.addAttribute("strategy",strategy);
		}
		return "strategy-list";
	}
	//add new strategy
	@RequestMapping(value="/add",method=RequestMethod.POST)
	public String addingStrategy(@ModelAttribute Strategy strategy , Model model) {
		Log.info("RequestMapping : strategy/add-POST");
		strategyService.addStrategy(strategy);
		//return "strategy-list"; 
		return "redirect:/strategy/list";
	}
	//to strategy-edit.html & show the chosen strategy
	@RequestMapping(value="/edit" , method=RequestMethod.GET)
	public String editStrategyPage(@RequestParam(value="id" , required=true)Integer id , Model model) {
		Log.info("RequestMapping : strategy/edit-GET");
		Strategy strategy = strategyService.getStrategy(id);
		model.addAttribute("strategy",strategy);
		
		return "strategy-edit";
	}
	// update a existing strategy
	@RequestMapping(value="/edit" , method=RequestMethod.POST)
	public String editStrategy(@ModelAttribute Strategy strategy , 
			Model model ,  @RequestParam(value="action" , required=true) String action ) {
		Log.info("RequestMapping : strategy/edit-POST");
		
		if(action.equals("save")) {
			Log.info("strategy/edit-POST : save");
			
			strategyService.updateStrategy(strategy);
			
			
		}else if(action.equals("cancel")) {
			Log.info("strategy/edit-POST : cancel");
		}
		
		return "redirect:/strategy/list";
	}
	// delete a strategy
	@RequestMapping(value="/delete" , method=RequestMethod.GET) 
	public String deleteStrategy(@RequestParam(value="id" , required=true) Integer id , 
			@RequestParam(value="phase" , required=true) String phase , Model model) {
		Log.info("strategy/delete-GET");
		Strategy strategy = strategyService.getStrategy(id);
		
		if(phase.equals("stage")) {
			Log.info("strategy/delete-GET : stage");
			
			model.addAttribute("strategy",strategy);
			return "strategy-delete";
		}else if(phase.equals("delete")) {
			Log.info("strategy/delete-GET : delete");
			
			strategyService.deleteStrategy(id);
			return "redirect:/strategy/list";
		}else if(phase.equals("cancel")) {
			Log.info("strategy/delete-GET : cancel");
			
			return "redirect:/strategy/list";
		}
		return "redirect:/strategy/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
