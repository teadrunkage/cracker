package ru.ncedu.schek.cracker.repository.ModelService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelRepository;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class ModelServiceImpl implements ModelService{
    @Autowired
    private ModelRepository modelRepository;

    static final String URL_MODEL = "http://localhost:8080/models/";
    static final String URL_MODEL_ID = "http://localhost:8080/model/{id}";

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin123A";

    //ответ на запрос получения списка моделей у Service
    //возможны ошибки из-за List<Model> -> Model[] и наоборот
    //или из-за верификации пользователя
    @Override
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

    //запрос на получение  модели у Service
    @Override
    public Model getModel() {
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
        HttpEntity<Model> entity = new HttpEntity<Model>(headers);
        RestTemplate restTemplate = new RestTemplate();

        //запрос на получение списка моделей
        ResponseEntity<Model> response = restTemplate.exchange(URL_MODEL_ID,
                HttpMethod.GET, entity, Model.class);
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
        for (Model model :modelRepository.findAll()) {
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
        int index = modelRepository.findAll().indexOf(currentModel);//находит индекс переданной модели
        modelRepository.findAll().set(index, currentModel);
    }

    @Override
    public void deleteModelById(long id) {
        for (Iterator<Model> iterator = modelRepository.findAll().iterator(); iterator.hasNext(); ) {
            Model model = iterator.next();
            if (model.getModelId() == id) {
                iterator.remove();
            }
        }
    }
}
