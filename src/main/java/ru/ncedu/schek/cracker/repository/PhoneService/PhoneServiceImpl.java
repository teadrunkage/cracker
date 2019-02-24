package ru.ncedu.schek.cracker.repository.PhoneService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.schek.cracker.repository.PhoneRepository;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class PhoneServiceImpl implements PhoneService{
    @Autowired
    private PhoneRepository phoneRepository;
}
