package ru.ncedu.schek.cracker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    @RequestMapping(value = "/model/", method = RequestMethod.POST)
    public ResponseEntity<Void> createModel(@RequestBody Model model, UriComponentsBuilder ucBuilder){
        System.out.println("Creating model " + model.getModelName());
        if (modelService.isModelExist(model)){
            System.out.println("Model with name " + model.getModelName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        modelService.saveModel(model);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/model/{id}").buildAndExpand(model.getModelId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Model --------------------------------------------------------

    @RequestMapping(value = "/model/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Model> updateModel(@PathVariable("id") long id, @RequestBody Model model){
        System.out.println("Updating model " + id);
        Model currentModel = modelService.findById(id);
        if (currentModel == null){
            System.out.println("Model with id " + id + " not found");
            return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
        }
        //Model_Char model, Double price, String color,String comment,List<Pictures> pictures
        currentModel.setModelName(model.getModelName());
        /*надо решить проблему с мин и макс ценой*/
        currentModel.setPriceMax(1000000);
        currentModel.setPriceMin(0);
        currentModel.setPhones(model.getPhones());

        modelService.updateModel(currentModel);
        return new ResponseEntity<Model>(currentModel, HttpStatus.OK);
    }
    //------------------- Delete a Model --------------------------------------------------------
    // возможны ошибки
    @RequestMapping(value = "/model/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Model> deleteModel(@PathVariable("id") long id){
        System.out.println("Fetching & deleting model with id " + id);
        Model model = modelService.findById(id);
        if (model == null){
            System.out.println("Unable to delete. Model with id " + id + " not found");
            return new ResponseEntity<Model>(HttpStatus.NOT_FOUND);
        }
        modelService.deleteModelById(id);
        return new ResponseEntity<Model>(model, HttpStatus.NO_CONTENT);
    }
}
