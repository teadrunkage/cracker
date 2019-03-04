package ru.ncedu.schek.cracker.repository.ModelService;

import ru.ncedu.schek.cracker.entities.Model;

import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
public interface ModelService {
    List<Model> listAllModels();

    void saveAllModels();
    
    Model getModel();

    Model findById(long id);

    boolean isModelExist(Model model);

    Model findByName(String name);

    void saveModel(Model model);

    void updateModel(Model currentModel);

    void deleteModelById(long id);
}
