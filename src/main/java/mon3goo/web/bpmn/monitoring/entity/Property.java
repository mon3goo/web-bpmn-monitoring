package mon3goo.web.bpmn.monitoring.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="monitoring_property")
public class Property  implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected Property() {
	}

	public Property(String _name, String _value) {
		this.name = _name;
		this.value=_value;
	}
	
	public Property(Long _id,String _name, String _value) {
		this.id=_id;
		this.name = _name;
		this.value=_value;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
//	@NotNull
	private String name;
//	@NotNull
	private String value;

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

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}