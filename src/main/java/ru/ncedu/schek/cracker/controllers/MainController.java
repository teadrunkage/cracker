package ru.ncedu.schek.cracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

@Controller
public class MainController {
	@Autowired
	ModelService modelService;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(org.springframework.ui.Model model) {
	//	modelService.saveAllModels();
		List <Model> models = modelService.listAllModels();
		model.addAttribute("models", models);
		return "index";
	}
//	org.springframework.ui.Model model
}