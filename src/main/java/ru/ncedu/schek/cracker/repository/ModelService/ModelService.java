package ru.ncedu.schek.cracker.repository.ModelService;

import ru.ncedu.schek.cracker.entities.Model;

import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
public interface ModelService {
    List<Model> listAllModels();
    Model createModel();
    Model updateModel();

}
