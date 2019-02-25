package ru.ncedu.schek.cracker.repository.PictureService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.schek.cracker.repository.PictureRepository;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class PictureServiceImpl implements PictureService{
    @Autowired
    private PictureRepository pictureRepository;
}
