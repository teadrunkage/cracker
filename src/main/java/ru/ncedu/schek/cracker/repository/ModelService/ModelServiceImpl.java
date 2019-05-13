package ru.ncedu.schek.cracker.repository.ModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.PhoneRepository;

import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


/**
 * Created by Admin on 24.02.2019.
 */
@Service
public class ModelServiceImpl implements ModelService {
    @Autowired
    private ModelRepository modelRepository;
    @Autowired
    private PhoneRepository phoneRepository;

    @Autowired
    private EntityManagerFactory entityManagerFactory;
    @Autowired
    private EntityManager entityManager;
    private int pageSize = 5;

    //изменил порты
    static final String URL_MODEL1 = "http://localhost:8081/models";//Daria
    static final String URL_MODEL2 = "http://localhost:8080/models";//Maxim

    /*==============================================REST===============================================*/
    //отправляется запрос на получение моделей от магазинов
    @Override
    public void saveAllModels() {
        RestTemplate restTemplate = new RestTemplate();
        Set<String> urlSet = new HashSet<String>();
        
        urlSet.add(URL_MODEL1);
        urlSet.add(URL_MODEL2);

        for (String URL_MODEL : urlSet) {
            try {
                Model[] list = restTemplate.getForObject(URL_MODEL, Model[].class);
                if (list != null) {
                    for (Model e : list) {
                        if (isModelExist(e)){
                            System.out.println("Model with name " + e.getModelName() + " already exist"+ HttpStatus.CONFLICT.toString());
                            break;
                        }
                        comparisonOfModelsPrice(e);
                        modelRepository.save(e);
                        for (Phone p : e.getPhones()) {
                            p.setModel(e);
                            phoneRepository.save(p);
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("I am falling!");
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
        }
    }
    /*==============================================REST===============================================*/

    @Override
    public void comparisonOfModelsPrice(Model model) {
        List<Long> list = new ArrayList<>();
        for (Phone phone : model.getPhones()) {
            list.add(phone.getPrice());
        }
        model.setPriceMax(Collections.max(list));
        model.setPriceMin(Collections.min(list));
    }

    
    @Override
    public List<Model> listAllModels() {
        return modelRepository.findAll();
    }
    
    
    
    @Override
    public Long getNumberOfPages() {
    	CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
    	CriteriaQuery<Long> countQuery = builder.createQuery(Long.class);
    	countQuery.select(builder.count(countQuery.from(Model.class)));
    	Long count = entityManager.createQuery(countQuery).getSingleResult();
        return count/pageSize + 1;
    }

    @Override
    public List<Model> getPageList(Long page) {
    	CriteriaBuilder builder = entityManagerFactory.getCriteriaBuilder();
    	CriteriaQuery<Model> criteriaQuery = builder.createQuery(Model.class);
    	Root<Model> from = criteriaQuery.from(Model.class);
    	CriteriaQuery<Model> select = criteriaQuery.select(from); 
    	TypedQuery<Model> typedQuery = entityManager.createQuery(select);
    			typedQuery.setFirstResult((int) ((page-1)*pageSize));
    			typedQuery.setMaxResults((int) (pageSize));
    			List<Model> pageList = typedQuery.getResultList();
    	
        return pageList;
    }
    

    @Override
	public List<Model> searchFromString(String text, Long page) {
    	TypedQuery<Model> typedQuery = entityManager.createQuery(
    			"Select m from Model m where "
    			+ "lower(modelName) LIKE "
    			+ "'%"+text.toLowerCase()+"%'", Model.class);
    	typedQuery.setFirstResult((int) ((page-1)*pageSize));
		typedQuery.setMaxResults((int) (pageSize));
    	List<Model> searchResult = typedQuery.getResultList();
    	return searchResult;
	}
    
    

	@Override
	public Long getNumberOfSearchPages(String text) {
		TypedQuery<Long> typedQuery = entityManager.createQuery(
    			"Select count(m) from Model m where "
    			+ "lower(modelName) LIKE "
    			+ "'%"+text.toLowerCase()+"%'", Long.class);
		Long numberOfSearchPages = typedQuery.getResultList().get(0)/pageSize -1;
        return numberOfSearchPages;
	}

	@Override
    public Model findById(long id) {
        for (Model model : modelRepository.findAll()) {
            if (model.getModelId() == id) {
                return model;
            }
        }
        return null;
    }

    @Override
    public Model findModelByPhone(Phone phone){
        return phone.getModel();
    }

    @Override
    public boolean isModelExist(Model model) {
        return findByName(model.getModelName()) != null;
    }
    
    @Override
    public Model findByName(String name) {
        for (Model model : modelRepository.findAll()) {
            if (model.getModelName().equalsIgnoreCase(name)) {
                return model;
            }
        }
        return null;
    }

    @Override
    public void saveModel(Model model) {
        modelRepository.save(model);
    }

    @Override
    public void updateModel(Model currentModel) {
        int index = modelRepository.findAll().indexOf(currentModel);// находит индекс переданной модели
        modelRepository.findAll().set(index, currentModel);
    }

    @Override
    public void deleteModelById(long id) {
        for (Iterator<Model> iterator = modelRepository.findAll().iterator(); iterator.hasNext(); ) {
            Model model = iterator.next();
            if (model.getModelId() == id) {
                iterator.remove();
            }
        }
    }
}
