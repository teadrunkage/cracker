package ru.ncedu.schek.cracker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Admin on 02.03.2019.
 */
@RestController
public class RestModel {
    @Autowired
    ModelService modelService;
    @Autowired
    PhoneService phoneService;
    @Autowired
    ModelRepository modelRepository;
    //обработка запросов от стороннего магазина
    //-------------------Create a Model--------------------------------------------------------
    @RequestMapping(value = "", method = RequestMethod.POST)
    public ResponseEntity<Void> createModel(@RequestBody Phone phone, UriComponentsBuilder ucBuilder)throws StackOverflowError{
        System.out.println("Creating model " + phone.getModel().getModelName());
        if (modelService.isModelExist(phone.getModel())){
            Model model= modelService.findByName(phone.getModel().getModelName());
            phone.setModel(model);
            model.getPhones().add(phone);

            modelService.comparisonOfModelsPrice(model);
            phoneService.savePhone(phone);
            modelService.saveModel(model);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        else if(!modelService.isModelExist(phone.getModel())) {
            Set<Phone> phoneSet = new HashSet<>();
            phoneSet.add(phone);
            Model model = new Model(phone.getModel().getModelName(), phone.getPrice(), phone.getPrice());
            model.setModelId((long)modelRepository.findAll().size()+1);
            phone.setModel(model);
            model.setPhones(phoneSet);
            modelService.comparisonOfModelsPrice(model);
            modelService.saveModel(model);
            return new ResponseEntity<Void>(HttpStatus.CREATED);
        }
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }
}
