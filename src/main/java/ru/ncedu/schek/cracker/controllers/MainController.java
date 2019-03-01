package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

import java.util.List;

@Controller
public class MainController {
	@Autowired
	ModelService modelService;
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(Model model) {
		List<Model> models = modelService.listAllModels();
		return "index";
	}

}