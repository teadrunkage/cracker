package ru.ncedu.schek.cracker;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.ncedu.schek.cracker.repository.PhoneRepository;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

/**
 * Created by Admin on 24.02.2019.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PhoneServiceTest {
    @MockBean
    private PhoneRepository phoneRepository;
    @Autowired
    private PhoneService phoneService;
    //нужно забить в базу несколько значений

    @Test
    public void testGetById() {
        /*
        given(this.phoneRepository.getById(any()))
                .willReturn(new Phone( new Model("Xiaomi", 10000,15000),12000,"black"));
        Phone phone = phoneService.getById((long) 1);
        assertThat(phone.getPhoneId()).isEqualTo(1);
        // и какие-нибудь другие проверки
        */
    }
}
