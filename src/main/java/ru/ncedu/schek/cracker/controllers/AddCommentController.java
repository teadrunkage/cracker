package ru.ncedu.schek.cracker.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.ncedu.schek.cracker.forms.CommentForm;

import java.io.IOException;


@Controller
public class AddCommentController {
	
	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.GET)
	public String addphone(Model model) {
		CommentForm commentForm = new CommentForm();
		model.addAttribute("commentForm", commentForm);
		return "addphone";
	}
	
	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.POST)
	public String savePhone(Model model, //
			@ModelAttribute("commentForm") CommentForm commentForm) throws IOException, InterruptedException {

		
		return "redirect:/phonerepo";
	}
}