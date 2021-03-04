package mon3goo.web.bpmn.monitoring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="configurated_engine_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfiguredEngineDetail  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ConfiguredEngineDetail() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank(message = "Please give a name to the engine!")
	private String name;
	
	@NotBlank(message = "REST endpoint can't empty!")
	private String restEndPoint;
	
	@NotBlank(message = "Username can't empty!")
	private String user;
	
	@NotBlank(message = "Please set a password!")
	private String psw;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRestEndPoint() {
		return restEndPoint;
	}

	public void setRestEndPoint(String restEndPoint) {
		this.restEndPoint = restEndPoint;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPsw() {
		return psw;
	}

	public void setPsw(String psw) {
		this.psw = psw;
	}
	
}