/**
 * 
 */
package org.qifeng.sbs.controller;

import java.util.List;
import java.util.Locale;

import javax.validation.Valid;

import org.qifeng.sbs.exception.UserNotFoundException;
import org.qifeng.sbs.model.Strategy;
import org.qifeng.sbs.service.StrategyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * @author jackho
 *
 */
@Controller
@RequestMapping("/strategy")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@PreAuthorize("denyAll")
public class StrategyController {
	
	private static Logger Log = LoggerFactory.getLogger(StrategyController.class);
	
	@Autowired
	private StrategyService strategyService;
	
	@Autowired
	private MessageSource messageSource;
	
	//list strategies
	@RequestMapping(value={"/","/list"},method=RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_STRATEGY_LIST_GET')")
	public String listOfStrategies(Model model) {
		Log.info("RequestMapping : strategy/list-GET");
		
		List<Strategy> strategies = strategyService.getStrategies();
		model.addAttribute("strategies",strategies);
		
		// if there was an error in /add, we do not want to overwrite
        // the existing strategy object containing the errors.
		
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
	@PreAuthorize("hasRole('CTRL_STRATEGY_ADD_POST')")
	public String addingStrategy(@Valid @ModelAttribute Strategy strategy , 
								 BindingResult result,
								 Model model,
								 RedirectAttributes redirectAttrs ) {
		Log.info("RequestMapping : strategy/add-POST");
		
		if (result.hasErrors()) {
			Log.info("strategy/add-POST error : " +result.toString());
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.strategy",result);
			redirectAttrs.addFlashAttribute("strategy",strategy);
			return "redirect:/strategy/list";
		} else {
			strategyService.addStrategy(strategy);
			String message = "Strategy " + strategy.getId() + " was successfully added";
			model.addAttribute("message",message);
			return "redirect:/strategy/list";				
		}
	}
	
	//to strategy-edit.html & show the chosen strategy
	@RequestMapping(value="/edit" , method=RequestMethod.GET)
	@PreAuthorize("hasRole('CTRL_STRATEGY_EDIT_GET')")
	public String editStrategyPage(@RequestParam(value="id" , required=true)Integer id , Model model) {
		Log.info("RequestMapping : strategy/edit-GET Id to query = "+id);
		
		if (! model.containsAttribute("strategy")) {
			Strategy strategy = strategyService.getStrategy(id);
			Log.info("Strategy/edit-GET: "+strategy.toString());
			model.addAttribute("strategy",strategy);
		}
		
		return "strategy-edit";
	}
	// update a existing strategy
	@RequestMapping(value="/edit" , method=RequestMethod.POST)
	 @PreAuthorize("hasRole('CTRL_STRATEGY_EDIT_POST')")
	public String editStrategy(@Valid @ModelAttribute Strategy strategy , 
							   BindingResult result,
							   Model model , 
							   RedirectAttributes redirectAttrs,
							   @RequestParam(value="action" , required=true) String action ) {
		Log.info("RequestMapping : strategy/edit-POST: "+action);
		
		System.out.println(action);
		System.out.println(messageSource.getMessage("button.action.cancel", null , Locale.US));
		
		if (action.equals( messageSource.getMessage("button.action.cancel", null , Locale.US))) {
			String message = "Strategy " + strategy.getId() + "edit cancelled";
			model.addAttribute("message", message);
		} else if (result.hasErrors()) {
			Log.info("Strategy-edit error: " + result.toString());
			
			redirectAttrs.addFlashAttribute("org.springframework.validation.BindingResult.strategy",result);
			redirectAttrs.addFlashAttribute("strategy",strategy);
			return "redirect:/strategy/edit?id=" + strategy.getId();
		} else if ( action.equals( messageSource.getMessage("button.action.save", null, Locale.US))) {
			Log.info("Strategy/edit-POST: " + strategy.toString());
			
			strategyService.updateStrategy(strategy);
			String message = "Strategy " + strategy.getId() + "was succesfully edited";
			model.addAttribute("message",message);
			
		}
		return "redirect:/strategy/list";
		
	}
	// delete a strategy
	@RequestMapping(value="/delete" , method=RequestMethod.GET) 
	@PreAuthorize("hasRole('CTRL_STRATEGY_DELETE_GET')")
	public String deleteStrategy(@RequestParam(value="id" , required=true) Integer id , 
			@RequestParam(value="phase" , required=true) String phase , Model model) {
		
		Strategy strategy = strategyService.getStrategy(id);
		Log.info("strategy/delete-GET | id = " + id + " | phase = " + phase + "|" + strategy.toString());
		
		if (phase.equals( messageSource.getMessage("button.action.cancel", null, Locale.US))) {
			String message = "Strategy delete was cancelled.";
			model.addAttribute("message" , message);
			return "redirect:/strategy/list";
		} else if (phase.equals( messageSource.getMessage("button.action.stage", null , Locale.US))) {
			String message = "Strategy " + strategy.getId() + "queued for display.";
			model.addAttribute("strategy" , strategy);
			model.addAttribute("message" , message);
			return "strategy-delete";
		} else if (phase.equals( messageSource.getMessage("button.action.delete", null , Locale.US))) {
			strategyService.deleteStrategy(id);
			String message = "Strategy " + strategy.getId() + "was succesfully  deleted";
			model.addAttribute("message" , message);
			return "redirect:/strategy/list";
		}
		 return "redirect:/strategy/list";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
}
