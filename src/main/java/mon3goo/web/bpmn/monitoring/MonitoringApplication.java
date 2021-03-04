package mon3goo.web.bpmn.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan({"mon3goo.web"})
@SpringBootApplication
@Configuration
public class MonitoringApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}
	
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(MonitoringApplication.class);
    }
    
}