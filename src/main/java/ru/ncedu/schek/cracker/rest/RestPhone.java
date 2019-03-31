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

    //обработка запросов от стороннего магазина
    //-------------------Delete a Phone--------------------------------------------------------
    @RequestMapping(value = "/deletephone", method = RequestMethod.POST)
    public ResponseEntity<Void> deletePhone(@RequestBody Phone phone, UriComponentsBuilder ucBuilder){
        Model models= modelRepository.findByModelName(phone.getModel().getModelName());
        for(Phone currentPhone: models.getPhones())
        {
            //добавочное сравнение по ссылкам
            if(currentPhone.getLink().equals(phone.getLink())){
                if (currentPhone.getModel().getPhones().size()==1){
                    Model model = modelService.findByName(phone.getModel().getModelName());
                    phoneRepository.delete(currentPhone);
                    modelRepository.delete(model);
                }else if(currentPhone.getModel().getPhones().size()>1){
                    phoneRepository.delete(currentPhone);
                }if (phone == null){
                    return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
                }
                return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
    //------------------- Update a Phone --------------------------------------------------------
    @RequestMapping(value = "/modifyphone", method = RequestMethod.PUT)
    public ResponseEntity<Void> updatePhone(@RequestBody Phone phone, UriComponentsBuilder ucBuilder)throws StackOverflowError{
        Model models= modelRepository.findByModelName(phone.getModel().getModelName());
        for(Phone currentPhone: models.getPhones())
        {
            //добавочное сравнение по ссылкам
            if(currentPhone.getLink().equals(phone.getLink())){
                currentPhone.setColor(phone.getColor());
                currentPhone.setLink(phone.getLink());
                currentPhone.setPrice(phone.getPrice());
                phoneService.updatePhone(currentPhone);
            }
        }
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
