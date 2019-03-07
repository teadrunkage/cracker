package ru.ncedu.schek.cracker.repository.ModelService;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;

import java.util.List;

/**
 * Created by Admin on 24.02.2019.
 */
public interface ModelService {
    List<Model> listAllModels();

    public void comparisonOfModelsPrice(Model model);

    void saveAllModels();


    Model findById(long id);

    Model findModelByPhone(Phone phone);

    boolean isModelExist(Model model);

    Model findByName(String name);

    void saveModel(Model model);

    void updateModel(Model currentModel);

    void deleteModelById(long id);
}
