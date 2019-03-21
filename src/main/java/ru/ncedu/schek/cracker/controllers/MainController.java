package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.schek.cracker.entities.Model;
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
		return "index";
	}

	@RequestMapping("/chatA")
	public String chat(HttpServletRequest request, org.springframework.ui.Model model) {
		String username = (String) request.getSession().getAttribute("username");

		if (username == null || username.isEmpty()) {
			return "redirect:/login";
		}
		model.addAttribute("username", username);

		return "chat";
	}

	@RequestMapping("/chatAdmin")
	public String chatForAdmin(HttpServletRequest request, org.springframework.ui.Model model) {
		//String username = (String) request.getSession().getAttribute("username");
		String username= "AdminAdmin";
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