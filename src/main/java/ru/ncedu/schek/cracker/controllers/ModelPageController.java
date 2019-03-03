package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.repository.ModelRepository;



@Controller
@RequestMapping("/modelpage")
public class ModelPageController {
	@Autowired
	private ModelRepository modelRepository;
	
	@GetMapping
    public String modelpage(org.springframework.ui.Model model, @RequestParam(name="modelId")long modelId) {		
		Model mymodel = modelRepository.getOne(modelId);
		System.out.println("!!!!!!!");
		System.out.println(modelRepository.count());
		model.addAttribute("model", mymodel);
        return "modelpage";
    }
}
