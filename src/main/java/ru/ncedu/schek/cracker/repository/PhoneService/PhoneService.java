package ru.ncedu.schek.cracker.repository.PhoneService;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;

import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
public interface PhoneService {
    Phone getById(Long id);

    //запрос на получение списка моделей у Service
    List<Phone> listAllPhones();

    //запрос на получение телефона  у Service
    Phone getPhone();

    boolean isPhoneExist(Phone phone);

    Phone findByName(String modelName);

    void savePhone(Phone phone);

    void updatePhone(Phone currentPhone);

    void deletePhoneById(long id);

    void deleteById(long id);

    Phone findPhoneByPhone(Phone phone);

    void deleteAllPhones();

    Phone findById(long id);

    Phone findByModel(Model model);
}
