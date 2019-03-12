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


@Controller
public class AddCommentController {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	CommentRepository comments;
	
	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.GET)
	public String addcomment(org.springframework.ui.Model model, @RequestParam(name="modelId") Long modelId)throws StackOverflowError{
		CommentForm commentForm = new CommentForm();
		Optional<Model> model1= modelRepository.findById(modelId);
		Model mymodel= model1.get();
		commentForm.setModel(mymodel);
		model.addAttribute("commentForm", commentForm);
		return "addcomment";
	}

	@RequestMapping(value = { "/addcomment" }, method = RequestMethod.POST)
	public String saveComment(org.springframework.ui.Model model,
			@ModelAttribute("commentForm") CommentForm commentForm) throws IOException, InterruptedException, StackOverflowError {

		String username = commentForm.getUsername();
		int grade = commentForm.getGrade();
		String text = commentForm.getText();
		Model mymodel =commentForm.getModel();
		
		Comment comment = new Comment();
		comment.setModel(mymodel);
		comment.setUsername(username);
		comment.setText(text);
		comment.setGrade(grade);
		
		comments.save(comment);
		
		return "redirect://";
	}

}