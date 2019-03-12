package ru.ncedu.schek.cracker.entities;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "COMMENT")
public class Comment {
    @Id
    @Column(name = "ID",nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long commentId;

    @ManyToOne(cascade = {CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "MODEL_ID")
    private Model model;

    @Column(name = "GRADE", nullable=true)
    private int grade;

    @Column(name = "USERNAME")
    private String username;
    
    @Lob
    @Column(name = "TEXT", nullable=true)
    private String text;
    
    public Comment() {
    	this.username = "anonimus";
    }
    
    
    public Comment(Model model, int grade, String text) {
    	this.username = "Anonymus";
    	this.model = model;
    	this.text = text;
    	this.grade = grade;
    }

	public Long getCommentId() {
		return commentId;
	}

	public void setCommentId(Long commentId) {
		this.commentId = commentId;
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    
    
    
}