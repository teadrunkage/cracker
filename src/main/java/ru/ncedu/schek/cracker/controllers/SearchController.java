package ru.ncedu.schek.cracker.controllers;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.forms.SearchForm;
import ru.ncedu.schek.cracker.repository.ModelRepository;

@Controller
public class SearchController {
	@Autowired
	private ModelRepository modelRepository;
	
	/*@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String search(org.springframework.ui.Model model) {
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		return "search";
	} */
	
	@RequestMapping(value = { "/search" }, method = RequestMethod.POST)
	public String doSearch(org.springframework.ui.Model model, //
			RedirectAttributes redirectAttributes, 
			@ModelAttribute("searchForm") SearchForm searchForm) throws IOException, InterruptedException {

		String text = searchForm.getText();
		System.out.println(text);
		
		redirectAttributes.addFlashAttribute( "text", text);
		
	    return "redirect:/search";
	}
	
	@RequestMapping(value = { "/search" }, method = RequestMethod.GET)
	public String search(org.springframework.ui.Model model, //
			@ModelAttribute("text") String text) {
		
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		
		List <Model> models = modelRepository.findAll();
		List <Model> searchModels = new ArrayList<Model>();
		for (Model m: models) {
			if (m.getModelName().contains(text)) {
				searchModels.add(m);
			} 
		} 
		if (searchModels.isEmpty()) {
			model.addAttribute("searchEmpty", true);
		} else {
			model.addAttribute("searchEmpty", false);
			model.addAttribute("models", searchModels);
		}
		return "search";
	}
	
}
