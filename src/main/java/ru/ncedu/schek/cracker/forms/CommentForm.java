package ru.ncedu.schek.cracker.forms;

import ru.ncedu.schek.cracker.entities.Model;

public class CommentForm {
	
	private String username;
	
	private int grade;
	
	private String text;
	
	private Model model;
	
	public Model getModel() {
		return model;
	}
	public void setModel(Model model) {
		this.model = model;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
