package mon3goo.web.bpmn.monitoring.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name="configured_engine")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ConfiguredEngine  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public ConfiguredEngine() {
		this.engineDetail= new ConfiguredEngineDetail();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "engineId", referencedColumnName = "id")
    private ConfiguredEngineDetail engineDetail;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ConfiguredEngineDetail getEngineDetail() {
		return engineDetail;
	}

	public void setEngineDetail(ConfiguredEngineDetail engineDetail) {
		this.engineDetail = engineDetail;
	}
    
    

}