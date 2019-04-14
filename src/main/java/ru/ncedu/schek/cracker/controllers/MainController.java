package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.forms.SearchForm;
import ru.ncedu.schek.cracker.repository.ModelService.ModelService;
import ru.ncedu.schek.cracker.repository.PhoneService.PhoneService;

import java.util.List;
import java.util.Optional;

@Controller
public class MainController {
	@Autowired
	ModelService modelService;
	@Autowired
	PhoneService phoneService;
	
	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public String index(org.springframework.ui.Model model) {
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		
		return "index";
	}
	
	
	@RequestMapping(value = { "/shop" }, method = RequestMethod.GET)
	public String shop(org.springframework.ui.Model model, @RequestParam(name="page") Optional<Long> optpage) {
		Long page = optpage.orElse((long) 1);
		Long numOfPages = modelService.getNumberOfPages();
			if (page == 0) {page = numOfPages;}
			if (page == numOfPages+1) {page = (long) 1;}
		List <Model> models = modelService.getPageList(page);
		model.addAttribute("models", models);
		model.addAttribute("curPage", page);
		model.addAttribute("numOfPages", numOfPages);
		
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		
		return "shop";
	}
	
	
	@RequestMapping(value = { "/category" }, method = RequestMethod.GET)
	public String category(org.springframework.ui.Model model) {
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		return "category";
	}
	
	@RequestMapping(value = { "/refresh" }, method=RequestMethod.GET)
	public String refresh(org.springframework.ui.Model model) {
		//принудительное очищение репозитория
	    // modelRepository.deleteAll();
		//modelService.saveAllModels();
		return "redirect:/index";
	}
}