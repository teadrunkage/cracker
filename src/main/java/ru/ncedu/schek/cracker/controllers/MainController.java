package ru.ncedu.schek.cracker.controllers;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@Controller
public class MainController {
	@Autowired
	ModelService modelService;

	static final String URL_MODEL = "http://localhost:8080/models/";
	static final String URL_CREATE_MODEL = "http://localhost:8080/model/";

	static final String URL_UPDATE_MODEL = "http://localhost:8080/model/";
	static final String URL_MODEL_PREFIX = "http://localhost:8080/model/";

	public static final String USER_NAME = "admin";
	public static final String PASSWORD = "admin123A";

	//запрос на получение списка моделей у Service
	@RequestMapping(value= "/models/", method= RequestMethod.GET)
	public List<Model> listAllModels() {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		// Authentication
		String auth = USER_NAME + ":" + PASSWORD;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

			headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
			// Request to return JSON format
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("my_other_key", "my_other_value");
		//get result as model
		HttpEntity<List<Model>> entity = new HttpEntity<List<Model>>(headers);
		RestTemplate restTemplate = new RestTemplate();

		//запрос на получение списка моделей
		ResponseEntity<Model[]> response = restTemplate.exchange(URL_MODEL,
				HttpMethod.GET, entity, Model[].class);
		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			Model[] list = response.getBody();
			if (list != null) {
				for (Model e : list) {
					System.out.println("Model: " + e.getModelName() + " - " + e.getModelId());
				}
				return Arrays.asList(list);
			}
		}
		return null;
	}
	//String modelName,long priceMin,long priceMax
	@RequestMapping(value = "/model/", method = RequestMethod.GET)
	public Model createModel() {
		Model model= new Model("Kvadro",12000, 32000);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_XML_VALUE);
		headers.setContentType(MediaType.APPLICATION_XML);

		RestTemplate restTemplate = new RestTemplate();
		// Data attached to the request.
		HttpEntity<Model> requestBody = new HttpEntity<>(model, headers);
		// Send request with POST method.
		ResponseEntity<Model> result = restTemplate.postForEntity(URL_CREATE_MODEL, requestBody, Model.class);

		if (result.getStatusCode() == HttpStatus.CREATED) {
			Model e = result.getBody();
			System.out.println("(Client Side) Employee Created: "+ e.getModelId()+" Name: "+e.getModelName());
			return  e;
		}
		return  null;
	}

	@RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
	public Model updateModel() {

		String md1="md1";
		Model updateInfo= new Model("Kvadro",12000, 32000);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Accept", MediaType.APPLICATION_JSON_VALUE);

		RestTemplate restTemplate = new RestTemplate();
		// Data attached to the request.
		HttpEntity<Model> requestBody = new HttpEntity<>(updateInfo, headers);
		// Send request with PUT method.
		restTemplate.exchange(URL_UPDATE_MODEL, HttpMethod.PUT,  requestBody, Void.class);
		String resourceUrl = URL_MODEL_PREFIX + "/" + md1;

		Model e = restTemplate.getForObject(resourceUrl, Model.class);

		if (e != null) {
			System.out.println("(Client side) Model after update: ");
			System.out.println("Model: " + e.getModelId() + " - " + e.getModelName());
			return  e;
		}
		return null;
	}


	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		return "index";
	}

}