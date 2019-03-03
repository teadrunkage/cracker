package ru.ncedu.schek.cracker.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

@Component
public class DataInit implements ApplicationRunner {
	@Autowired
	ModelService modelService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		modelService.saveAllModels();
	}
	
}
