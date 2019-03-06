package ru.ncedu.schek.cracker.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	ModelService modelService;
	@Autowired
	PhoneService phoneService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		modelService.saveAllModels();
		//нужно получать /phones/id чтобы удалять
		//phoneService.saveAllPhones();
	}
}
