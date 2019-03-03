package ru.ncedu.schek.cracker.repository.ModelService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.PhoneRepository;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class ModelServiceImpl implements ModelService {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private PhoneRepository phoneRepository;

	static final String URL_MODEL0 = "http://localhost:8080/models";
	static final String URL_MODEL1 = "http://localhost:8081/models";
	static final String URL_MODEL_ID = "http://localhost:8080/model/{id}";

	public static final String USER_NAME = "admin";
	public static final String PASSWORD = "admin";

	@Override
	public List<Model> listAllModels() {
		return modelRepository.findAll();
	}

	// ответ на запрос получения списка моделей у Service
	// Сохранение сущностей в репозитории
	public void saveAllModels() {
		// Авторизация не работает, нужно открыть доступ "/models" для всех
		// Или переписать авторизацию!
		HttpHeaders headers = new HttpHeaders();
		// Authentication
		String auth = USER_NAME + ":" + PASSWORD;
		byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
		String authHeader = "Basic " + new String(encodedAuth);
		headers.set("Authorization", authHeader);

		RestTemplate restTemplate = new RestTemplate();
		// Для отладки
		/*
		 * String result = restTemplate.getForObject(URL_MODEL, String.class);
		 * System.out.println(result);
		 */
		Set<String> urlSet = new HashSet<String>();
		urlSet.add(URL_MODEL0);
		urlSet.add(URL_MODEL1);
		for (String URL_MODEL : urlSet) {
			try {
				Model[] list = restTemplate.getForObject(URL_MODEL, Model[].class);

				if (list != null) {
					for (Model e : list) {
						modelRepository.save(e);
						for (Phone p : e.getPhones()) {
							p.setModel(e);
							phoneRepository.save(p);
						}
					}
				}
			} catch (Exception e) {
				System.out.println("I am falling!");
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
	}

	// НЕ ПРОВЕРЕНО - НЕ ИСПОЛЬЗУЕТСЯ
	// запрос на получение модели у Service
	@Override
	public Model getModel() {
		// HttpHeaders
		HttpHeaders headers = new HttpHeaders();
		// Authentication
		/*
		 * String auth = USER_NAME + ":" + PASSWORD; byte[] encodedAuth =
		 * Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII"))); String
		 * authHeader = "Basic " + new String(encodedAuth); headers.set("Authorization",
		 * authHeader);
		 */

		headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
		// Request to return JSON format
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("my_other_key", "my_other_value");
		// get result as model
		HttpEntity<Model> entity = new HttpEntity<Model>(headers);
		RestTemplate restTemplate = new RestTemplate();

		// запрос на получение списка моделей
		ResponseEntity<Model> response = restTemplate.exchange(URL_MODEL_ID, HttpMethod.GET, entity, Model.class);
		HttpStatus statusCode = response.getStatusCode();
		System.out.println("Response Satus Code: " + statusCode);
		// Status Code: 200
		if (statusCode == HttpStatus.OK) {
			// Response Body Data
			Model model = response.getBody();
			if (model != null) {
				System.out.println("Model: " + model.getModelName() + " - " + model.getModelId());
				return model;
			}
		}
		return null;
	}

	@Override
	public Model findById(long id) {
		for (Model model : modelRepository.findAll()) {
			if (model.getModelId() == id) {
				return model;
			}
		}
		return null;
	}

	@Override
	public boolean isModelExist(Model model) {
		return findByName(model.getModelName()) != null;
	}

	@Override
	public Model findByName(String name) {
		for (Model model : modelRepository.findAll()) {
			if (model.getModelName().equalsIgnoreCase(name)) {
				return model;
			}
		}
		return null;
	}

	@Override
	public void saveModel(Model model) {
		modelRepository.save(model);
	}

	@Override
	public void updateModel(Model currentModel) {
		int index = modelRepository.findAll().indexOf(currentModel);// находит индекс переданной модели
		modelRepository.findAll().set(index, currentModel);
	}

	@Override
	public void deleteModelById(long id) {
		for (Iterator<Model> iterator = modelRepository.findAll().iterator(); iterator.hasNext();) {
			Model model = iterator.next();
			if (model.getModelId() == id) {
				iterator.remove();
			}
		}
	}
}
