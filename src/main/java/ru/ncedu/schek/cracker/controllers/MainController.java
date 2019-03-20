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

import javax.servlet.http.HttpServletRequest;
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
		model.addAttribute("models", models);
		
		SearchForm searchForm = new SearchForm();
		model.addAttribute("searchForm", searchForm);
		
		return "index";
	}

	
	@RequestMapping(value = { "/refresh" }, method=RequestMethod.GET)
	public String refresh(org.springframework.ui.Model model) {
		//принудительное очищение репозитория
	    // modelRepository.deleteAll();
		//modelService.saveAllModels();
		return "redirect:/index";
	}

	@RequestMapping("/chat")
	public String index(HttpServletRequest request, org.springframework.ui.Model model) {
		String username = (String) request.getSession().getAttribute("username");

		if (username == null || username.isEmpty()) {
			return "redirect:/login";
		}
		model.addAttribute("username", username);

		return "chat";
	}

	@RequestMapping(path = "/login", method = RequestMethod.GET)
	public String showLoginPage() {
		return "login";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String doLogin(HttpServletRequest request, @RequestParam(defaultValue = "") String username) {
		username = username.trim();

		if (username.isEmpty()) {
			return "login";
		}
		request.getSession().setAttribute("username", username);

		return "redirect:/chat";
	}

	@RequestMapping(path = "/logout")
	public String logout(HttpServletRequest request) {
		request.getSession(true).invalidate();

		return "redirect:/login";
	}
}