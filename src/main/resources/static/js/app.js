/**
 * 
 */
var appUrl="/mon3goo.web.bpmn.monitoring/";
var selectedEngines=new Array();

function updateConfigArea(url) {

	$("#inner_monitoring_area").remove();

	invokeSynchAjax(function(returnedJson) {
		$("#configuration_area").append(returnedJson);
	});
	
}

function loadDefaultData() {
	selectedEngines=new Array();

	urlGlobal=appUrl+"fetchConfiguredData";

	updateConfigArea(urlGlobal);
	
}

function showEngineConfig() {

	urlGlobal=appUrl+"loadNewEngineScreen";

	updateConfigArea(urlGlobal);
}

function newEngineConfig() {

	urlGlobal=appUrl+"newEngineConfig";
	
    var configuredEngine = new Object();
    
    var engineDetail = new Object();
    
	engineDetail.name = $("#engineName").val();
	engineDetail.psw=$("#enginePsw").val();
	engineDetail.restEndPoint=$("#engineRestEndPoint").val();
	engineDetail.user=$("#engineUserName").val();
	
	configuredEngine.engineDetail = engineDetail;
	
	dataGlobal=configuredEngine;
	
	invokePostAjax(); 
	
	loadDefaultData ();
	
}

function checkEngineAction(engineId)
{

	fLen = selectedEngines.length;

	if (document.getElementById("chkConfiguredEngine_" + engineId).checked==true)
	{
		selectedEngines.push(engineId);
	}
	else
	{
		for (i = 0; i < fLen; i++) {
			if (selectedEngines[i]==engineId)
			{
				selectedEngines.splice(i,1);
				break;
			}
		}
	}

	fLen = selectedEngines.length;
	if (fLen>=1) {
		$( "#btn_delete_engine" ).prop( "disabled", false );
	} else {
		$( "#btn_delete_engine" ).prop( "disabled", true );
	}	
	
}

function deleteMultipleEngines() {

	var baseGlobalUrl=appUrl+"deleteEngine";
	
	selectedEngines.forEach(function(engine){
  		urlGlobal=baseGlobalUrl + "?engineId="+engine;
		invokeSynchAjax();
	});
	loadDefaultData();
}

function closeEngineConfigArea() {

	$("#inner_monitoring_area").remove();

	loadDefaultData();
}