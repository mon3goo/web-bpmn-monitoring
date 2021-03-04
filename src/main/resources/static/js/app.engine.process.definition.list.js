/**
 * 
 */
var appUrl="/mon3goo.web.bpmn.monitoring/";

$(document).ready(function(){
   $( ".processDefinitionName" ).each(function( index ) {
  		var definitionKey= $( this ).attr( 'definitionKey' );
  		var definitionId= $( this ).attr( 'definitionId' );
  		
  		urlGlobal=appUrl+"fetchProcessInstancesByDefinition?processDefinitionKey="+definitionKey;
		invokeAjax(function(returnedJson) {
			$("#definitionActiveInstances_"+definitionKey).text(returnedJson);
		});
  		urlGlobal=appUrl+"fetchDeadLetterJobsByDefinition?processDefinitionId="+definitionId;
		invokeAjax(function(returnedJson) {
			$("#definitionActiveInstancesWithError_"+definitionKey).text(returnedJson);
		});
  		
	});
});