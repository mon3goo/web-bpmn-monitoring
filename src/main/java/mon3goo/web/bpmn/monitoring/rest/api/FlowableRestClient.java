package mon3goo.web.bpmn.monitoring.rest.api;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

@Service
public class FlowableRestClient {	

	public FlowableRestClient() {
//		this.flowableBaseEndpoint=flowableBaseEndpoint;
//		this.flowableUserName=flowableBaseUser;
//		this.flowableUserPswd=flowableBasePswd;
//		this.restTimeout=restTimeout;
	}

	public FlowableRestClient(String _flowableBaseEndpoint,String _flowableUserName, String _flowableUserPswd) {
		this.flowableBaseEndpoint=_flowableBaseEndpoint;
		this.flowableUserName=_flowableUserName;
		this.flowableUserPswd=_flowableUserPswd;
//		this.restTimeout=restTimeout;
	}
	
	private String flowableBaseEndpoint;
	private String flowableUserName;
	private String flowableUserPswd;
	private int restTimeout;

	RestTemplate restTemplate = null;
	HttpHeaders requestHeaders = new HttpHeaders();

	private static final Logger logger= LoggerFactory.getLogger(FlowableRestClient.class);

	public void init()  {

		HttpComponentsClientHttpRequestFactory httpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpRequestFactory.setConnectionRequestTimeout(restTimeout);
		httpRequestFactory.setConnectTimeout(restTimeout);
		httpRequestFactory.setReadTimeout(restTimeout);

		restTemplate=new RestTemplate(httpRequestFactory);
	}

	public Object invokeflowablePOSTRestApi(String method, Object requestPayload, Class responseType) throws JsonProcessingException {

		this.init();

		ObjectWriter ow = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)
				.writer().withRootName("");

//		HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

		logger.info("request - " + ow.writeValueAsString(requestPayload));
		logger.info("url - " + flowableBaseEndpoint + method);
		logger.info("flowableUserName - " + flowableUserName);
		logger.info("flowableUserPswd - " + flowableUserPswd);

//				requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);
		requestHeaders.setContentType(mediaType);
		HttpEntity<String> request = new HttpEntity<String>(ow.writeValueAsString(requestPayload), requestHeaders);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(flowableUserName, flowableUserPswd));
		Object response = restTemplate.postForObject(flowableBaseEndpoint + method, request ,
				responseType);

		logger.info("response - " + ow.writeValueAsString(response));

		return response;

	}
	
	public Object invokeflowableGETRestApi(String method, Object requestPayload, Class responseType) throws JsonProcessingException {

		this.init();

		ObjectWriter ow = new ObjectMapper().configure(SerializationFeature.WRAP_ROOT_VALUE, true)
				.writer().withRootName("");

//		HttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("UTF-8"));

		logger.info("request - " + ow.writeValueAsString(requestPayload));
		logger.info("url - " + flowableBaseEndpoint + method);
		logger.info("flowableUserName - " + flowableUserName);
		logger.info("flowableUserPswd - " + flowableUserPswd);

//				requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		Charset utf8 = Charset.forName("UTF-8");
		MediaType mediaType = new MediaType("application", "json", utf8);
		requestHeaders.setContentType(mediaType);
		HttpEntity<String> request = new HttpEntity<String>(ow.writeValueAsString(requestPayload), requestHeaders);
		restTemplate.getMessageConverters().add(0, new StringHttpMessageConverter(StandardCharsets.UTF_8));
		restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(flowableUserName, flowableUserPswd));
		ResponseEntity<Object> response = restTemplate.getForEntity(flowableBaseEndpoint + method, responseType);
		
		logger.info("response - " + ow.writeValueAsString(response.getBody()));

		return response.getBody();

	}

}