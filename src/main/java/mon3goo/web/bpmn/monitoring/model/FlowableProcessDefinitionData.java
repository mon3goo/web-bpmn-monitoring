package mon3goo.web.bpmn.monitoring.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class FlowableProcessDefinitionData {
	
	private String id;
	private String url;
	private String key;
	private int version;
	private String name;
	private String description;
	private String tenantId;
	private String deploymentId;
	private String deploymentUrl;
	private String resource;
	private String diagramResource;
	private String category;
	private boolean graphicalNotationDefined;
	private boolean suspended;
	private boolean startFormDefined;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public int getVersion() {
		return version;
	}
	public void setVersion(int version) {
		this.version = version;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTenantId() {
		return tenantId;
	}
	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}
	public String getDeploymentId() {
		return deploymentId;
	}
	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}
	public String getDeploymentUrl() {
		return deploymentUrl;
	}
	public void setDeploymentUrl(String deploymentUrl) {
		this.deploymentUrl = deploymentUrl;
	}
	public String getResource() {
		return resource;
	}
	public void setResource(String resource) {
		this.resource = resource;
	}
	public String getDiagramResource() {
		return diagramResource;
	}
	public void setDiagramResource(String diagramResource) {
		this.diagramResource = diagramResource;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public boolean isGraphicalNotationDefined() {
		return graphicalNotationDefined;
	}
	public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
		this.graphicalNotationDefined = graphicalNotationDefined;
	}
	public boolean isSuspended() {
		return suspended;
	}
	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}
	public boolean isStartFormDefined() {
		return startFormDefined;
	}
	public void setStartFormDefined(boolean startFormDefined) {
		this.startFormDefined = startFormDefined;
	}
	
}
