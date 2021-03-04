/**
 * 
 */
var appUrl="/mon3goo.web.bpmn.monitoring/";

$(document).ready(function(){
     var currentRow=$("#enginesList").closest("tr"); 
     var col1=currentRow.find("td:eq(0)").text(); // get current row 1st TD value
     var col2=currentRow.find("td:eq(1)").text(); // get current row 2nd TD
     var col3=currentRow.find("td:eq(2)").text(); // get current row 3rd TD
     var data=col1+"\n"+col2+"\n"+col3;
     
     $("table > tbody > tr").each(function () {
		fetchProcessDefinition($(this).find('td').eq(1).text());
	});
});

function fetchProcessDefinition(engineId) {
	var _engineId=engineId.replace(/\s+/g, '');;
	urlGlobal=appUrl+"fetchProcessDefinition?engineId="+engineId;
	invokeAjax(function(returnedJson) {
		var engine=returnedJson;
		$("#engineDefinitions_"+_engineId).text(engine.total);
	});
	
	urlGlobal=appUrl+"fetchActiveTasks?engineId="+engineId;
	invokeAjax(function(returnedJson) {
		$("#engineActiveTasks_"+_engineId).text(returnedJson);
	});
}

function loadEngineDetail(engineId) {

	$("#inner_monitoring_area").remove();

	urlGlobal=appUrl+"loadEngineDetail?engineId="+engineId;
	invokeSynchAjax(function(returnedJson) {
		$("#configuration_area").append(returnedJson);
	});
	
	urlGlobal=appUrl+"fetchProcessDefinitionArea?engineId="+engineId;
	invokeSynchAjax(function(returnedJson) {
		$("#engineDefinitionList").append(returnedJson);
	});
	
}