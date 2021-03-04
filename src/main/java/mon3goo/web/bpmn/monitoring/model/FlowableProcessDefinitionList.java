package mon3goo.web.bpmn.monitoring.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
@JsonInclude(JsonInclude.Include.ALWAYS)
public class FlowableProcessDefinitionList {
	
	@JsonProperty("data")
	private List<FlowableProcessDefinitionData> flowableProcessDefinitionData;
	private int total;
	private int start;
	private String sort;
	private String order;
	private int size;
	
	public List<FlowableProcessDefinitionData> getFlowableProcessDefinitionData() {
		return flowableProcessDefinitionData;
	}
	public void setFlowableProcessDefinitionData(List<FlowableProcessDefinitionData> flowableProcessDefinitionData) {
		this.flowableProcessDefinitionData = flowableProcessDefinitionData;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
	
}
