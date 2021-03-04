package mon3goo.web.bpmn.monitoring.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import mon3goo.web.bpmn.monitoring.entity.ConfiguredEngine;
import mon3goo.web.bpmn.monitoring.repository.ConfiguredEngineRepo;
import mon3goo.web.generics.jpa.entities.MenuOption;
import mon3goo.web.generics.jpa.repositories.MenuOptionsRepo;

@Controller
@SessionAttributes("engineId")
public class MonitoringBaseController implements WebMvcConfigurer {

	private final Logger logger = LoggerFactory.getLogger(MonitoringBaseController.class);

	@Autowired
	MenuOptionsRepo monitoringMenuRepo;

	@Autowired
	ConfiguredEngineRepo configuredEngineRepo;

	@RequestMapping("/")
	public String viewConfigurationPanel(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {

		//fetches the horizontal navbar options
		List<MenuOption> menuOptions=monitoringMenuRepo.findByScopeAndDirection("wkf_monitoring","h");	

		model.addAttribute("menuOptions", menuOptions);

		return "home";
	}

	@RequestMapping("/fetchConfiguredData")
	public String fetchConfiguredData(HttpServletRequest request,
			HttpServletResponse response,Model model) throws Exception {

		List<ConfiguredEngine> engines=configuredEngineRepo.findAll();	

		if (engines.size()==0) 
			return "/fragments/emptyConfiguration";

		model.addAttribute("engines", engines);

		return "/fragments/configuration";
	}

	@GetMapping("/loadNewEngineScreen")
	public String loadNewEngineScreen(Model model) throws Exception {

		model.addAttribute("engineConfig", new ConfiguredEngine());
		model.addAttribute("isNew", true);

		return "/fragments/engine_configuration";
	}

	@PostMapping("/newEngineConfig")
	public ResponseEntity<?> newEngineConfigSubmit(
			@Valid @RequestBody ConfiguredEngine configuredEngine, BindingResult bindingResult) {
		
        if (bindingResult.hasErrors()) {

            return ResponseEntity.badRequest().body("error");
        }
		
//		model.addAttribute("configuredEngine", configuredEngine);
		
		configuredEngineRepo.save(configuredEngine);
		
		return ResponseEntity.ok("ok");
	}

	
	@GetMapping("/deleteEngine")
	public ResponseEntity<?> fetchConfiguredData(HttpServletRequest request,
			HttpServletResponse response,Model model, 
			@RequestParam(value = "engineId") final int engineId) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		configuredEngineRepo.delete(engine);
		
		return  ResponseEntity.ok(""); 	
	}
	
	@GetMapping("/loadEngineDetail")
	public String loadEngineDetailData(@RequestParam(value = "engineId") final int engineId,
			Model model) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		model.addAttribute("engineConfig", engine);
		model.addAttribute("isNew", false);
		
		return "/fragments/engine_configuration";
	}
	
}