package ru.ncedu.schek.cracker.repository.PhoneService;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.PhoneRepository;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class PhoneServiceImpl implements PhoneService {
    @Autowired
    private PhoneRepository phoneRepository;

    static final String URL_PHONE = "http://localhost:8080/phones";
    static final String URL_PHONE_ID = "http://localhost:8080/phones/{id}";

    public static final String USER_NAME = "admin";
    public static final String PASSWORD = "admin123A";

    /*========================================REST==============================================*/
    @Override
    public Phone getPhone() {
        HttpHeaders headers = new HttpHeaders();
        String auth = USER_NAME + ":" + PASSWORD;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")));
        String authHeader = "Basic " + new String(encodedAuth);
        headers.set("Authorization", authHeader);

        headers.setAccept(Arrays.asList(new MediaType[]{MediaType.APPLICATION_JSON}));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("my_other_key", "my_other_value");

        HttpEntity<Phone> entity = new HttpEntity<Phone>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<Phone> response = restTemplate.exchange(URL_PHONE_ID, HttpMethod.GET, entity, Phone.class);
        HttpStatus statusCode = response.getStatusCode();
        System.out.println("Response Satus Code: " + statusCode);
        if (statusCode == HttpStatus.OK) {
            Phone phone = response.getBody();
            if (phone != null) {
                System.out.println("Phone: " + phone.getModel().getModelName() + " - " + phone.getPhoneId());
                return phone;
            }
        }
        return null;
    }
     /*========================================REST==============================================*/

    @Override
    public boolean isPhoneExist(Phone phone) {
        return findByName(phone.getModel().getModelName()) != null;
    }

    @Override
    public Phone findByName(String modelName) {
        for (Phone phone : phoneRepository.findAll()) {
            if (phone.getModel().getModelName().equalsIgnoreCase(modelName)) {
                return phone;
            }
        }
        return null;
    }

    @Override
    public void savePhone(Phone phone) {
        phoneRepository.save(phone);
    }

    @Override
    public void updatePhone(Phone currentPhone) {
        //замена телефона
        int index = phoneRepository.findAll().indexOf(currentPhone);
        phoneRepository.save(phoneRepository.findAll().set(index, currentPhone));
    }

    @Override
    public void deletePhoneById(long id) {
        for (Iterator<Phone> iterator = phoneRepository.findAll().iterator(); iterator.hasNext(); ) {
            Phone phone = iterator.next();
            if (phone.getPhoneId() == id) {
                iterator.remove();
            }
        }
    }
    @Override
    public void deleteById(long id){
        Phone phone= phoneRepository.getById(id);
        phoneRepository.delete(phone);
    }

    @Override
    public void deleteAllPhones() {
        phoneRepository.findAll().clear();
    }

    @Override
    public Phone findById(long id) {
        for (Phone phone : phoneRepository.findAll()) {
            if (phone.getPhoneId() == id) {
                return phone;
            }
        }
        return null;
    }
    @Override
    public Phone getById(Long id) {
        return phoneRepository.getById(id);
    }

    @Override
    public List<Phone> listAllPhones() {
        return phoneRepository.findAll();
    }

}
