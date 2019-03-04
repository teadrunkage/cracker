package ru.ncedu.schek.cracker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

/**
 * Created by Admin on 02.03.2019.
 */
@RestController
public class RestModel {
    @Autowired
    ModelService modelService;

    /*сторонний магазин будет просить создать/апдейт/удалить продукт, мы должны обработать его запрос*/
    //-------------------Create a Model--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createModel(@RequestBody Model model, UriComponentsBuilder ucBuilder){
        System.out.println("Creating model " + model.getModelName());
        if (modelService.isModelExist(model)){
            System.out.println("Model with name " + model.getModelName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        //modelService.comparisonOfModelsPrice(model);
        modelService.saveModel(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/model/{id}").buildAndExpand(model.getModelId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
}
