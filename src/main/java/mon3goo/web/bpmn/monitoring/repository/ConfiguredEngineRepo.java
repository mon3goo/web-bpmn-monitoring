package mon3goo.web.bpmn.monitoring.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import mon3goo.web.bpmn.monitoring.entity.ConfiguredEngine;

//@Repository
public interface ConfiguredEngineRepo extends CrudRepository<ConfiguredEngine, Long> {

	List<ConfiguredEngine> findAll();
	
//	ConfiguredEngine findById_Engine(Long id);
	
}
