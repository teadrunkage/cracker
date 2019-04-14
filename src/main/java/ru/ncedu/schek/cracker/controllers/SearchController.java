package ru.ncedu.schek.cracker.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.forms.SearchForm;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class SearchController {
	@Autowired
	private ModelService modelService;
	
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
			@ModelAttribute("text") String text, //
			@RequestParam(name="page") Optional<Long> optpage) {
		
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		
		Long page = optpage.orElse((long) 1);
		List <Model> searchModels = modelService.searchFromString(text, page);
		Long numOfPages = modelService.getNumberOfSearchPages(text);
		
		if (searchModels.isEmpty()) {
			model.addAttribute("searchEmpty", true);
		} else {
			model.addAttribute("searchEmpty", false);
			model.addAttribute("models", searchModels);
		}
			if (page == 0) {page = numOfPages;}
			if (page == numOfPages+1) {page = (long) 1;}
		model.addAttribute("curPage", page);
		model.addAttribute("numOfPages", numOfPages);
		
		return "search";
	}
	
}
