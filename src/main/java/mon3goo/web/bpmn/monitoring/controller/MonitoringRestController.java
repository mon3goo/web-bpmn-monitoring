package mon3goo.web.bpmn.monitoring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import mon3goo.web.bpmn.monitoring.entity.ConfiguredEngine;
import mon3goo.web.bpmn.monitoring.model.FlowableProcessDefinitionList;
import mon3goo.web.bpmn.monitoring.model.FlowableQueryFilter;
import mon3goo.web.bpmn.monitoring.repository.ConfiguredEngineRepo;
import mon3goo.web.bpmn.monitoring.rest.api.FlowableRestClient;

@Controller
//@SessionAttributes("engineId")
public class MonitoringRestController {

	private final Logger logger = LoggerFactory.getLogger(MonitoringRestController.class);

	@Value("${engine.query.definitions.size}")
	private int definitionsQuerySize;
	
	private int engineId;

	@ModelAttribute("engineId")
	public int engineId() {
		return engineId;
	}
	
	@Autowired
	ConfiguredEngineRepo configuredEngineRepo;

	@GetMapping("/fetchProcessDefinition")
	public @ResponseBody FlowableProcessDefinitionList fetchConfiguredData(HttpServletRequest request,
			HttpServletResponse response,Model model, 
			@RequestParam(value = "engineId") final int engineId) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		FlowableRestClient flowableRestClient = new FlowableRestClient(engine.getEngineDetail().getRestEndPoint(),
				engine.getEngineDetail().getUser(),
				engine.getEngineDetail().getPsw());

		FlowableProcessDefinitionList flowableProcessDefinitionList = (FlowableProcessDefinitionList)
				 flowableRestClient.invokeflowableGETRestApi("/service/repository/process-definitions?latest=true", 
						 null, 
						 FlowableProcessDefinitionList.class);
		
		this.engineId=engineId;
		
		return  flowableProcessDefinitionList; 	
	}
	
	@GetMapping("/fetchActiveTasks")
	public @ResponseBody int fetchActiveTasksData(HttpServletRequest request,
			HttpServletResponse response,Model model, 
			@RequestParam(value = "engineId") final int engineId) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		FlowableRestClient flowableRestClient = new FlowableRestClient(engine.getEngineDetail().getRestEndPoint(),
				engine.getEngineDetail().getUser(),
				engine.getEngineDetail().getPsw());

		FlowableProcessDefinitionList flowableProcessDefinitionList = (FlowableProcessDefinitionList)
				 flowableRestClient.invokeflowableGETRestApi("/service/runtime/tasks?active=true", 
						 null, 
						 FlowableProcessDefinitionList.class);
		
		this.engineId=engineId;
		
		return flowableProcessDefinitionList.getTotal(); 	
	}
	
	@GetMapping("/fetchProcessDefinitionArea")
	public String fetchProcessDefinitionAreaData(@RequestParam(value = "engineId") final int engineId,
			Model model) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		FlowableRestClient flowableRestClient = new FlowableRestClient(engine.getEngineDetail().getRestEndPoint(),
				engine.getEngineDetail().getUser(),
				engine.getEngineDetail().getPsw());

		FlowableProcessDefinitionList flowableProcessDefinitionList = (FlowableProcessDefinitionList)
				 flowableRestClient.invokeflowableGETRestApi("/service/repository/process-definitions?latest=true&size="+definitionsQuerySize, 
						 null, 
						 FlowableProcessDefinitionList.class);
		
		model.addAttribute("flowableProcessDefinitionList", flowableProcessDefinitionList);
		
		this.engineId=engineId;
		
		return "/fragments/engine_process_definition_list";
	}
	
	@GetMapping("/fetchProcessInstancesByDefinition")
	public @ResponseBody int fetchProcessInstancesByDefinitionData(@RequestParam(value = "processDefinitionKey") final String processDefinitionKey,
			Model model) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		FlowableRestClient flowableRestClient = new FlowableRestClient(engine.getEngineDetail().getRestEndPoint(),
				engine.getEngineDetail().getUser(),
				engine.getEngineDetail().getPsw());

		FlowableQueryFilter queryFilter = new FlowableQueryFilter();
		queryFilter.setProcessDefinitionKey(processDefinitionKey);
		
		FlowableProcessDefinitionList processInstancesByDefinition = (FlowableProcessDefinitionList)
				 flowableRestClient.invokeflowablePOSTRestApi("/service/query/process-instances", 
						 queryFilter, 
						 FlowableProcessDefinitionList.class);
		
		model.addAttribute("processInstancesByDefinition", processInstancesByDefinition);
		
		return processInstancesByDefinition.getTotal();
	}
	
	@GetMapping("/fetchDeadLetterJobsByDefinition")
	public @ResponseBody int fetchDeadLetterJobsByDefinitionData(@RequestParam(value = "processDefinitionId") final String processDefinitionId,
			Model model) throws Exception {

		ConfiguredEngine engine =configuredEngineRepo.findById((long) engineId).get();
		
		FlowableRestClient flowableRestClient = new FlowableRestClient(engine.getEngineDetail().getRestEndPoint(),
				engine.getEngineDetail().getUser(),
				engine.getEngineDetail().getPsw());

		FlowableQueryFilter queryFilter = new FlowableQueryFilter();
		queryFilter.setProcessDefinitionId(processDefinitionId);
		
		FlowableProcessDefinitionList flowableProcessDefinitionList = (FlowableProcessDefinitionList)
				 flowableRestClient.invokeflowableGETRestApi("/service/management/deadletter-jobs?processDefinitionId="+processDefinitionId, 
						 null, 
						 FlowableProcessDefinitionList.class);
		
		return flowableProcessDefinitionList.getTotal();
	}

}