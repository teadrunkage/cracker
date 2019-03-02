package ru.ncedu.schek.cracker.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

/**
 * Created by Admin on 01.03.2019.
 */
@RestController
public class RestPhone {
    @Autowired
    PhoneService phoneService;

    /*
    * Главный магазин будет принимать запросы на удаление/изменение/создание нового телефона от вспомогательных магазинов
    * */
    //-------------------Create a Phone--------------------------------------------------------
    @RequestMapping(value = "/phone/", method = RequestMethod.POST)
    public ResponseEntity<Void> createPhone(@RequestBody Phone phone, UriComponentsBuilder ucBuilder){
        System.out.println("Creating phone " + phone.getModel().getModelName());

        if (phoneService.isPhoneExist(phone)){
            System.out.println("Phone with name " + phone.getModel().getModelName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }
        phoneService.savePhone(phone);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/phone/{id}").buildAndExpand(phone.getPhoneId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }
    //------------------- Update a Phone --------------------------------------------------------

    @RequestMapping(value = "/phone/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Phone> updatePhone(@PathVariable("id") long id, @RequestBody Phone phone){
        System.out.println("Updating phone " + id);
        Phone currentPhone = phoneService.findById(id);
        if (currentPhone == null){
            System.out.println("Phone with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }
        //Model_Char model, Double price, String color,String comment,List<Pictures> pictures
        currentPhone.setModel(phone.getModel());
        currentPhone.setPictures(phone.getPictures());
        currentPhone.setColor(phone.getColor());
        currentPhone.setPrice(phone.getPrice());

        phoneService.updatePhone(currentPhone);
        return new ResponseEntity<Phone>(currentPhone, HttpStatus.OK);
    }
    //------------------- Delete a Phone --------------------------------------------------------
    //возможны ошибки
    @RequestMapping(value = "/phone/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Phone> deletePhone(@PathVariable("id") long id){
        System.out.println("Fetching & deleting phone with id " + id);

        Phone phone = phoneService.findById(id);

        if (phone == null){
            System.out.println("Unable to delete. Phone with id " + id + " not found");
            return new ResponseEntity<Phone>(HttpStatus.NOT_FOUND);
        }
        phoneService.deletePhoneById(id);
        return new ResponseEntity<Phone>(phone, HttpStatus.NO_CONTENT);
    }
}
