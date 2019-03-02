package ru.ncedu.schek.cracker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelRepository;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

@Controller
public class MainController {
	@Autowired
	ModelRepository models;
}


	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(org.springframework.ui.Model model) {
		List <Model> allModels = models.findAll();
		model.addAttribute("models", allModels);
		return "index";
	}

}