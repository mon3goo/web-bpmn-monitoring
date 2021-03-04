package mon3goo.web.bpmn.monitoring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mon3goo.web.bpmn.monitoring.entity.Property;

//@Repository
public interface PropertyRepo extends CrudRepository<Property, Long> {

	List<Property> findAll();
	
}
