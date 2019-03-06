package ru.ncedu.schek.cracker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneRepository;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

/**
 * Created by Admin on 06.03.2019.
 */
@RestController
public class RestPhone {
    @Autowired
    ModelService modelService;
    @Autowired
    PhoneService phoneService;
    @Autowired
    PhoneRepository phoneRepository;
    @Autowired
    ModelRepository modelRepository;
    //-------------------Delete a Phone--------------------------------------------------------
    @RequestMapping(value = "/phones/{phoneId}", method = RequestMethod.DELETE)
    public ResponseEntity<Phone> deleteUser(@PathVariable("phoneId") long phoneId)throws StackOverflowError{
        //Model model= modelService.findById(modelId);
        Phone phone = phoneService.findById(phoneId);
        if (phone == null){
            System.out.println("Unable to delete  Phone with id "+phoneId+ " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }
        phoneService.deletePhoneById(phoneId);
        return new ResponseEntity<Phone>(phone, HttpStatus.NO_CONTENT);
    }
}
