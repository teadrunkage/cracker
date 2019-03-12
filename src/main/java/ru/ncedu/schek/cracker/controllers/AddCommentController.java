package ru.ncedu.schek.cracker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ncedu.schek.cracker.entities.Comment;
import ru.ncedu.schek.cracker.entities.Model;
import ru.ncedu.schek.cracker.forms.CommentForm;
import ru.ncedu.schek.cracker.repository.CommentRepository;
import ru.ncedu.schek.cracker.repository.ModelRepository;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;


@Controller
public class AddCommentController {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private CommentRepository comments;
	
	private Model mymodel;
	private String referer;

	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.GET)
	public String addcomment(org.springframework.ui.Model model, //
							 HttpServletRequest request, //
							 @RequestParam(name="modelId") Long modelId) {
		CommentForm commentForm = new CommentForm();
		mymodel = modelRepository.getOne(modelId);
		System.out.println("!!!!!!");
		System.out.println(modelId);
		System.out.println(mymodel.getModelName());
		model.addAttribute("commentForm", commentForm);

		referer = request.getHeader("Referer");
		return "addcomment";
	}

	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.POST)
	public String saveComment(org.springframework.ui.Model model, //
							  @ModelAttribute("commentForm") CommentForm commentForm) throws IOException, InterruptedException {

		String username = commentForm.getUsername();
		int grade = commentForm.getGrade();
		String text = commentForm.getText();

		System.out.println(mymodel.getModelName());

		Comment comment = new Comment();
		comment.setModel(mymodel);
		comment.setUsername(username);
		comment.setText(text);
		comment.setGrade(grade);

		comments.save(comment);

		return "redirect:"+ referer;
	}

}