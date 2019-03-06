package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.entities.Phone;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

import java.util.List;

@Controller
public class MainController {
	@Autowired
	ModelService modelService;
	@Autowired
	PhoneService phoneService;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(org.springframework.ui.Model model) {
		List <Model> models = modelService.listAllModels();
		List<Phone> phones= phoneService.listAllPhones();
		model.addAttribute("models", models);
		return "index";
	}
}