package ru.ncedu.schek.cracker.repository.ModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.ncedu.schek.cracker.repository.ModelRepository;

/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class ModelServiceImpl implements ModelService{
    @Autowired
    private ModelRepository modelRepository;
}
